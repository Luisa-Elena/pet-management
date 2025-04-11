package sd.project.demo.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import sd.project.demo.exception.model.ExceptionBody;
import sd.project.demo.exception.model.ExceptionCode;
import sd.project.demo.security.util.SecurityConstants;
import sd.project.demo.security.util.SecurityProperties;
import sd.project.demo.security.util.SecurityUtil;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {

    private final SecurityProperties securityProperties;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException, SignatureException {
        Optional<String> jwtToken = SecurityUtil.getTokenFromRequest(request);
        if (jwtToken.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            Claims claims = (Claims) Jwts.parser()
                    .verifyWith((SecretKey) SecurityUtil.getSigningKey(securityProperties.getSecretKey()))
                    .build()
                    .parse(jwtToken.get())
                    .getPayload();
            String email = claims.getSubject();
            Collection<SimpleGrantedAuthority> authorities = Arrays.stream(
                            ((String) claims.get(SecurityUtil.ROLE_CLAIM)).split(SecurityUtil.ROLE_DELIMITER)
                    )
                    .map(SimpleGrantedAuthority::new)
                    .toList();
            Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            this.onUnsuccessfulAuthorization(response, exception.getMessage());
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return SecurityConstants.AUTH_PATHS_TO_SKIP.contains(request.getRequestURI());
    }

    private void onUnsuccessfulAuthorization(HttpServletResponse response, String message) {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());

        try {
            objectMapper.writeValue(response.getWriter(), getExceptionBody(message));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private ExceptionBody getExceptionBody(String message) {
        return ExceptionBody.builder()
                .timestamp(ZonedDateTime.now())
                .message(message)
                .code(ExceptionCode.FORBIDDEN_ACCESS.getCode())
                .build();
    }
}

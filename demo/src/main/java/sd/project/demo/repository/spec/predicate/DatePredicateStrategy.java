package sd.project.demo.repository.spec.predicate;

import java.time.ZonedDateTime;
import java.util.Optional;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

@Component
public class DatePredicateStrategy implements PredicateStrategy<ZonedDateTime> {

    @Override
    public Optional<Predicate> createPredicate(
            String field,
            ZonedDateTime value,
            Root<?> root,
            CriteriaBuilder criteriaBuilder
    ) {
        return Optional.of(criteriaBuilder.equal(root.get(field), value));
    }
}
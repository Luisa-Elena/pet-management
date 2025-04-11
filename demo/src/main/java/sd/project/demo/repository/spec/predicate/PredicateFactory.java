package sd.project.demo.repository.spec.predicate;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;
import sd.project.demo.repository.spec.util.ReflectionUtil;

@Component
public class PredicateFactory {

    private final Map<Class<?>, PredicateStrategy<?>> strategies;

    public PredicateFactory(List<PredicateStrategy<?>> strategyList) {
        this.strategies = strategyList.stream().collect(Collectors.toMap(
                ReflectionUtil::getGenericType,
                strategy -> strategy
        ));
    }

    public Optional<Predicate> createPredicate(
            String field,
            Object value,
            Root<?> root,
            CriteriaBuilder criteriaBuilder
    ) {
        return strategies.entrySet().stream()
                .filter(strategyTpe -> strategyTpe.getKey().isAssignableFrom(value.getClass()))
                .map(strategyTpe -> createPredicate(strategyTpe.getValue(), field, value, root, criteriaBuilder))
                .findFirst()
                .orElse(Optional.empty());
    }

    @SuppressWarnings("unchecked")
    private <Type> Optional<Predicate> createPredicate(
            PredicateStrategy<?> strategy,
            String field,
            Object value,
            Root<?> root,
            CriteriaBuilder criteriaBuilder
    ) {
        return ((PredicateStrategy<Type>) strategy).createPredicate(field, (Type) value, root, criteriaBuilder);
    }
}

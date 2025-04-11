package sd.project.demo.repository.spec.predicate;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BooleanPredicateStrategy implements PredicateStrategy<Boolean>{
    @Override
    public Optional<Predicate> createPredicate(
            String field,
            Boolean value,
            Root<?> root,
            CriteriaBuilder criteriaBuilder) {
        return Optional.of(criteriaBuilder.equal(root.get(field), value));
    }
}

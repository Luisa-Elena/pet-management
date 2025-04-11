package sd.project.demo.repository.pet;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import sd.project.demo.model.dto.pet.PetFilterDTO;
import sd.project.demo.model.entity.PetEntity;
import sd.project.demo.model.entity.SpeciesEntity;
import sd.project.demo.repository.spec.EntitySpec;
import sd.project.demo.repository.spec.predicate.PredicateFactory;

import java.util.ArrayList;
import java.util.List;

@Component
public class PetSpec extends EntitySpec<PetEntity, PetFilterDTO> {

    public PetSpec(PredicateFactory predicateFactory) {
        super(predicateFactory);
    }

    @Override
    protected List<String> getFilterableFields() {
        return List.of("name", "isAdopted");
    }

    @Override
    public Specification<PetEntity> createSpecification(PetFilterDTO filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Delegate non-join fields (like "name") to the parent class
            Specification<PetEntity> parentSpecification = super.createSpecification(filter);
            Predicate parentPredicate = parentSpecification.toPredicate(root, query, criteriaBuilder);

            predicates.add(parentPredicate);

            // Handle filtering by species - needs join
            if (filter.species() != null && !filter.species().isEmpty()) {
                Join<PetEntity, SpeciesEntity> speciesJoin = root.join("species", JoinType.LEFT);
                predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(speciesJoin.get("name")), filter.species().toLowerCase()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}

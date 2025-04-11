package sd.project.demo.repository.adoption;

import org.springframework.stereotype.Component;
import sd.project.demo.model.dto.adoption.AdoptionFilterDTO;
import sd.project.demo.model.entity.AdoptionEntity;
import sd.project.demo.repository.spec.EntitySpec;
import sd.project.demo.repository.spec.predicate.PredicateFactory;

import java.util.List;

@Component
public class AdoptionSpec extends EntitySpec<AdoptionEntity, AdoptionFilterDTO> {

    public AdoptionSpec(PredicateFactory predicateFactory) {
        super(predicateFactory);
    }

    @Override
    protected List<String> getFilterableFields() {
        return List.of("status");
    }
}

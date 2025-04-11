package sd.project.demo.repository.donation;

import org.springframework.stereotype.Component;
import sd.project.demo.model.dto.donation.DonationFilterDTO;
import sd.project.demo.model.entity.DonationEntity;
import sd.project.demo.repository.spec.EntitySpec;
import sd.project.demo.repository.spec.predicate.PredicateFactory;

import java.util.List;

@Component
public class DonationSpec extends EntitySpec<DonationEntity, DonationFilterDTO> {

    public DonationSpec(PredicateFactory predicateFactory) {
        super(predicateFactory);
    }

    @Override
    protected List<String> getFilterableFields() {
        return List.of("userEmail", "amount", "status");
    }
}

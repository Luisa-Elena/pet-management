package sd.project.demo.repository.donation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import sd.project.demo.model.entity.DonationEntity;

import java.util.UUID;

@Repository
public interface DonationRepository extends JpaRepository<DonationEntity, UUID>, JpaSpecificationExecutor<DonationEntity> {

}

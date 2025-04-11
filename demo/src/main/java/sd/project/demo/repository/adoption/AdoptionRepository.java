package sd.project.demo.repository.adoption;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import sd.project.demo.model.entity.AdoptionEntity;
import sd.project.demo.model.entity.PetEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface AdoptionRepository extends JpaRepository<AdoptionEntity, UUID>, JpaSpecificationExecutor<AdoptionEntity> {

    boolean existsByPet(PetEntity pet);
    List<AdoptionEntity> findByAdoptionTimestampBefore(LocalDateTime timestamp);
}

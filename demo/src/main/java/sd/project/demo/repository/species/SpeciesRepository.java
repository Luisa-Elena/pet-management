package sd.project.demo.repository.species;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sd.project.demo.model.entity.SpeciesEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpeciesRepository extends JpaRepository<SpeciesEntity, UUID> {

    List<SpeciesEntity> findAllByNameContaining(String name);

    Optional<SpeciesEntity> findByName(String name);

    boolean existsByName(String name);
}
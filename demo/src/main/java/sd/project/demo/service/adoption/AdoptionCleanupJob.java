package sd.project.demo.service.adoption;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sd.project.demo.model.entity.AdoptionEntity;
import sd.project.demo.model.entity.Status;
import sd.project.demo.repository.adoption.AdoptionRepository;
import sd.project.demo.repository.pet.PetRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdoptionCleanupJob {

    private final AdoptionRepository adoptionRepository;
    private final PetRepository petRepository;

    @Scheduled(fixedRate = 10000)
    @Transactional
    public void deleteOldAdoptions() {
        LocalDateTime threshold = LocalDateTime.now().minusWeeks(1); // Delete adoptions which are more than one week old
        List<AdoptionEntity> oldAdoptions = adoptionRepository.findByAdoptionTimestampBefore(threshold);

        if (!oldAdoptions.isEmpty()) {
            log.info("[CLEANUP] Deleted old adoptions: {}", oldAdoptions.size());
        }

        for (AdoptionEntity adoption : oldAdoptions) {
            if(adoption.getStatus() == Status.ACCEPTED) {
                adoptionRepository.delete(adoption);
                petRepository.delete(adoption.getPet());
            }
        }
    }
}

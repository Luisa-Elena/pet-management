package sd.project.demo.service.adoption;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import sd.project.demo.exception.model.DataNotFoundException;
import sd.project.demo.exception.model.ExceptionCode;
import sd.project.demo.exception.model.PetAlreadyAdoptedException;
import sd.project.demo.model.dto.CollectionResponseDTO;
import sd.project.demo.model.dto.adoption.AdoptionFilterDTO;
import sd.project.demo.model.dto.adoption.AdoptionRequestDTO;
import sd.project.demo.model.dto.adoption.AdoptionResponseDTO;
import sd.project.demo.model.entity.*;
import sd.project.demo.model.entity.AdoptionEntity;
import sd.project.demo.model.mapper.AdoptionMapper;
import sd.project.demo.repository.adoption.AdoptionRepository;
import sd.project.demo.repository.adoption.AdoptionSpec;
import sd.project.demo.repository.pet.PetRepository;
import sd.project.demo.repository.user.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdoptionServiceBean implements AdoptionService{

    private final AdoptionRepository adoptionRepository;
    private final AdoptionMapper adoptionMapper;
    private final AdoptionSpec adoptionSpec;
    private final UserRepository userRepository;
    private final PetRepository petRepository;

    @Override
    public CollectionResponseDTO<AdoptionResponseDTO> findAll(AdoptionFilterDTO filter) {
        Specification<AdoptionEntity> spec = adoptionSpec.createSpecification(filter);
        PageRequest page = PageRequest.of(filter.pageNumber(), filter.pageSize());
        Page<AdoptionEntity> adoptions = adoptionRepository.findAll(spec, page);

        return CollectionResponseDTO.<AdoptionResponseDTO>builder()
                .pageNumber(page.getPageNumber())
                .pageSize(page.getPageSize())
                .totalPages(adoptions.getTotalPages())
                .totalElements(adoptions.getTotalElements())
                .elements(adoptionMapper.convertEntitiesToResponseDtos(adoptions.getContent()))
                .build();
    }

    @Override
    public AdoptionResponseDTO findById(UUID id) {
        AdoptionEntity adoptionEntity = adoptionRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(ExceptionCode.ADOPTION_NOT_FOUND, id));

        return adoptionMapper.convertEntityToResponseDto(adoptionEntity);
    }

    @Override
    @Transactional
    public AdoptionResponseDTO save(AdoptionRequestDTO adoptionRequestDTO) {
        UserEntity user = userRepository.findByEmail(adoptionRequestDTO.userEmail())
                .orElseThrow(() -> new DataNotFoundException(ExceptionCode.USER_NOT_FOUND, adoptionRequestDTO.userEmail()));
        PetEntity pet = petRepository.findByName(adoptionRequestDTO.petName())
                .orElseThrow(() -> new DataNotFoundException(ExceptionCode.PET_NOT_FOUND, adoptionRequestDTO.petName()));

        boolean isPetAlreadyAdopted = adoptionRepository.existsByPet(pet);
        if (isPetAlreadyAdopted) {
            throw new PetAlreadyAdoptedException(ExceptionCode.PET_ALREADY_ADOPTED, adoptionRequestDTO.petName());
        }

        pet.setIsAdopted(true);
        petRepository.save(pet);

        AdoptionEntity adoption = new AdoptionEntity();
        adoption.setUser(user);
        adoption.setPet(pet);
        adoption.setStatus(Status.PENDING);

        adoptionRepository.save(adoption);

        return adoptionMapper.convertEntityToResponseDto(adoption);
    }

    @Override
    public AdoptionResponseDTO update(UUID id, AdoptionRequestDTO adoptionRequestDTO) {
        AdoptionEntity adoptionEntityToBeUpdated = adoptionRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(ExceptionCode.ADOPTION_NOT_FOUND, id));

        adoptionEntityToBeUpdated.setStatus(Status.ACCEPTED);
        AdoptionEntity adoptionEntitySaved = adoptionRepository.save(adoptionEntityToBeUpdated);
        return adoptionMapper.convertEntityToResponseDto(adoptionEntitySaved);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        AdoptionEntity adoption = adoptionRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(ExceptionCode.ADOPTION_NOT_FOUND, id));

        PetEntity pet = adoption.getPet();
        adoptionRepository.deleteById(id);
        petRepository.delete(pet);
    }
}

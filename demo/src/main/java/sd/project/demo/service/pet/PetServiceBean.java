package sd.project.demo.service.pet;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import sd.project.demo.exception.model.DataNotFoundException;
import sd.project.demo.exception.model.DuplicateDataException;
import sd.project.demo.exception.model.ExceptionCode;
import sd.project.demo.model.dto.CollectionResponseDTO;
import sd.project.demo.model.dto.pet.PetFilterDTO;
import sd.project.demo.model.dto.pet.PetRequestDTO;
import sd.project.demo.model.dto.pet.PetResponseDTO;
import sd.project.demo.model.mapper.PetMapper;
import sd.project.demo.model.entity.PetEntity;
import sd.project.demo.model.entity.SpeciesEntity;
import sd.project.demo.repository.pet.PetRepository;
import sd.project.demo.repository.pet.PetSpec;
import sd.project.demo.repository.species.SpeciesRepository;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PetServiceBean implements PetService {

    private final PetRepository petRepository;
    private final PetMapper petMapper;
    private final SpeciesRepository speciesRepository;
    private final PetSpec petSpec;

    @Override
    public CollectionResponseDTO<PetResponseDTO> findAll(PetFilterDTO filter) {
        Specification<PetEntity> spec = petSpec.createSpecification(filter);
        PageRequest page = PageRequest.of(filter.pageNumber(), filter.pageSize());
        Page<PetEntity> pets = petRepository.findAll(spec, page);

        return CollectionResponseDTO.<PetResponseDTO>builder()
                .pageNumber(page.getPageNumber())
                .pageSize(page.getPageSize())
                .totalPages(pets.getTotalPages())
                .totalElements(pets.getTotalElements())
                .elements(petMapper.convertEntitiesToResponseDtos(pets.getContent()))
                .build();
    }

    @Override
    public PetResponseDTO findById(UUID id) {
        PetEntity petEntity = petRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(ExceptionCode.PET_NOT_FOUND, id));

        return petMapper.convertEntityToResponseDto(petEntity);
    }

    @Override
    public PetResponseDTO save(PetRequestDTO petRequestDTO) {

        if (petRepository.existsByName(petRequestDTO.name())) {
            throw new DuplicateDataException(ExceptionCode.PET_NAME_TAKEN, petRequestDTO.name());
        }
        SpeciesEntity species = speciesRepository.findByName(petRequestDTO.speciesName())
                .orElseThrow(() -> new DataNotFoundException(ExceptionCode.SPECIES_NOT_FOUND, petRequestDTO.speciesName()));

        PetEntity pet = new PetEntity();
        pet.setName(petRequestDTO.name());
        pet.setDescription(petRequestDTO.description());
        pet.setSpecies(species);
        pet.setIsAdopted(false);
        pet.setImageUrl(petRequestDTO.imageUrl());

        petRepository.save(pet);

        return petMapper.convertEntityToResponseDto(pet);
    }

    @Override
    public PetResponseDTO update(UUID id, PetRequestDTO petRequestDTO) {

        PetEntity petEntityToBeUpdated = petRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(ExceptionCode.PET_NOT_FOUND, id));

        if (petRequestDTO.speciesName() != null && !petRequestDTO.speciesName().isEmpty()) {
            SpeciesEntity species = speciesRepository.findByName(petRequestDTO.speciesName())
                    .orElseThrow(() -> new DataNotFoundException(ExceptionCode.SPECIES_NOT_FOUND, petRequestDTO.speciesName()));
            petEntityToBeUpdated.setSpecies(species);
        }

        petMapper.updatePetEntity(petEntityToBeUpdated, petRequestDTO);
        PetEntity petEntitySaved = petRepository.save(petEntityToBeUpdated);

        return petMapper.convertEntityToResponseDto(petEntitySaved);
    }

    @Override
    public void delete(UUID id) {
        PetEntity petEntity = petRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(ExceptionCode.PET_NOT_FOUND, id));

        petRepository.deleteById(petEntity.getId());
    }
}

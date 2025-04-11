package sd.project.demo.service.species;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sd.project.demo.exception.model.DataNotFoundException;
import sd.project.demo.exception.model.DuplicateDataException;
import sd.project.demo.exception.model.ExceptionCode;
import sd.project.demo.model.dto.species.SpeciesRequestDTO;
import sd.project.demo.model.dto.species.SpeciesResponseDTO;
import sd.project.demo.model.mapper.SpeciesMapper;
import sd.project.demo.model.entity.SpeciesEntity;
import sd.project.demo.repository.species.SpeciesRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpeciesServiceBean implements SpeciesService {

    private final SpeciesRepository speciesRepository;
    private final SpeciesMapper speciesMapper;

    @Override
    public List<SpeciesResponseDTO> findAll() {
        List<SpeciesEntity> speciesEntityList = speciesRepository.findAll();

        return speciesMapper.convertEntitiesToResponseDtos(speciesEntityList);
    }

    @Override
    public SpeciesResponseDTO findByName(String name) {
        SpeciesEntity speciesEntity = speciesRepository.findByName(name)
                .orElseThrow(() -> new DataNotFoundException(ExceptionCode.SPECIES_NOT_FOUND, name));

        return speciesMapper.convertEntityToResponseDto(speciesEntity);
    }

    @Override
    public SpeciesResponseDTO save(SpeciesRequestDTO speciesRequestDTO) {
        if (speciesRepository.existsByName(speciesRequestDTO.name())) {
            throw new DuplicateDataException(ExceptionCode.SPECIES_NAME_TAKEN, speciesRequestDTO.name());
        }
        SpeciesEntity speciesToBeAdded = speciesMapper.convertRequestDtoToEntity(speciesRequestDTO);
        SpeciesEntity speciesAdded = speciesRepository.save(speciesToBeAdded);

        return speciesMapper.convertEntityToResponseDto(speciesAdded);
    }

    @Override
    public SpeciesResponseDTO update(UUID id, SpeciesRequestDTO speciesRequestDTO) {
        if (speciesRepository.existsByName(speciesRequestDTO.name())) {
            throw new DuplicateDataException(ExceptionCode.SPECIES_NAME_TAKEN, speciesRequestDTO.name());
        }
        SpeciesEntity speciesEntityToBeUpdated = speciesRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(ExceptionCode.SPECIES_NOT_FOUND, id));
        speciesMapper.updateSpeciesEntity(speciesEntityToBeUpdated, speciesRequestDTO);
        SpeciesEntity speciesEntitySaved = speciesRepository.save(speciesEntityToBeUpdated);

        return speciesMapper.convertEntityToResponseDto(speciesEntitySaved);
    }

    @Override
    public void delete(UUID id) {
        SpeciesEntity speciesEntity = speciesRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(ExceptionCode.SPECIES_NOT_FOUND, id));

        speciesRepository.deleteById(speciesEntity.getId());
    }
}

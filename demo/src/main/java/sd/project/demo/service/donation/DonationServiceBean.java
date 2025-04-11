package sd.project.demo.service.donation;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import sd.project.demo.exception.model.DataNotFoundException;
import sd.project.demo.exception.model.ExceptionCode;
import sd.project.demo.model.dto.CollectionResponseDTO;
import sd.project.demo.model.dto.donation.DonationFilterDTO;
import sd.project.demo.model.dto.donation.DonationRequestDTO;
import sd.project.demo.model.dto.donation.DonationResponseDTO;
import sd.project.demo.model.entity.*;
import sd.project.demo.model.entity.DonationEntity;
import sd.project.demo.model.mapper.DonationMapper;
import sd.project.demo.repository.donation.DonationRepository;
import sd.project.demo.repository.donation.DonationSpec;
import sd.project.demo.repository.user.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DonationServiceBean implements DonationService {

    private final DonationRepository donationRepository;
    private final DonationMapper donationMapper;
    private final DonationSpec donationSpec;
    private final UserRepository userRepository;

    @Override
    public CollectionResponseDTO<DonationResponseDTO> findAll(DonationFilterDTO filter) {
        Specification<DonationEntity> spec = donationSpec.createSpecification(filter);
        PageRequest page = PageRequest.of(filter.pageNumber(), filter.pageSize());
        Page<DonationEntity> donations = donationRepository.findAll(spec, page);

        return CollectionResponseDTO.<DonationResponseDTO>builder()
                .pageNumber(page.getPageNumber())
                .pageSize(page.getPageSize())
                .totalPages(donations.getTotalPages())
                .totalElements(donations.getTotalElements())
                .elements(donationMapper.convertEntitiesToResponseDtos(donations.getContent()))
                .build();
    }

    @Override
    public DonationResponseDTO findById(UUID id) {
        DonationEntity donationEntity = donationRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(ExceptionCode.DONATION_NOT_FOUND, id));

        return donationMapper.convertEntityToResponseDto(donationEntity);
    }

    @Override
    public DonationResponseDTO save(DonationRequestDTO donationRequestDTO) {

        UserEntity user = userRepository.findByEmail(donationRequestDTO.userEmail())
                .orElseThrow(() -> new DataNotFoundException(ExceptionCode.USER_NOT_FOUND, donationRequestDTO.userEmail()));

        DonationEntity donation = new DonationEntity();
        donation.setUser(user);
        donation.setAmount(donationRequestDTO.amount());
        donation.setStatus(Status.PENDING);

        donationRepository.save(donation);

        return donationMapper.convertEntityToResponseDto(donation);
    }

    @Override
    public DonationResponseDTO update(UUID id, DonationRequestDTO donationRequestDTO) {

        DonationEntity donationEntityToBeUpdated = donationRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(ExceptionCode.DONATION_NOT_FOUND, id));

        donationEntityToBeUpdated.setStatus(Status.ACCEPTED);
        DonationEntity donationEntitySaved = donationRepository.save(donationEntityToBeUpdated);

        return donationMapper.convertEntityToResponseDto(donationEntitySaved);
    }

    @Override
    public void delete(UUID id) {
        DonationEntity donationEntity = donationRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(ExceptionCode.DONATION_NOT_FOUND, id));

        donationRepository.deleteById(donationEntity.getId());
    }
}

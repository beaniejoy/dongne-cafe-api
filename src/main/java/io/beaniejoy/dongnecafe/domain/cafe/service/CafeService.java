package io.beaniejoy.dongnecafe.domain.cafe.service;

import io.beaniejoy.dongnecafe.domain.cafe.domain.Cafe;
import io.beaniejoy.dongnecafe.domain.cafe.dto.cafe.CafeInfoResponseDto;
import io.beaniejoy.dongnecafe.domain.cafe.dto.cafe.CafeSearchResponseDto;
import io.beaniejoy.dongnecafe.domain.cafe.dto.cafe.CafeUpdateRequestDto;
import io.beaniejoy.dongnecafe.domain.cafe.error.CafeNotFoundException;
import io.beaniejoy.dongnecafe.domain.cafe.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CafeService {

    private final CafeRepository cafeRepository;

    @Transactional(readOnly = true)
    public List<CafeSearchResponseDto> getCafeList(Pageable pageable) {

        Page<Cafe> cafeListWithPagination = cafeRepository.findAll(pageable);

        return cafeListWithPagination.stream()
                .map(Cafe::toSearchResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CafeInfoResponseDto getCafeInfoByCafeId(UUID cafeId) {
        Cafe cafe = cafeRepository.findById(cafeId)
                .orElseThrow(() -> new CafeNotFoundException(cafeId));

        return cafe.toInfoResponseDto();
    }

    public void updateCafe(UUID cafeId, CafeUpdateRequestDto resource) {
        Cafe cafe = cafeRepository.findById(cafeId)
                .orElseThrow(() -> new CafeNotFoundException(cafeId));

        cafe.updateInfo(
                resource.getName(),
                resource.getAddress(),
                resource.getPhoneNumber(),
                resource.getDescription());
    }
}

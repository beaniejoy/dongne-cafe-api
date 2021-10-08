package io.beaniejoy.dongnecafe.cafe.service;

import io.beaniejoy.dongnecafe.cafe.domain.Cafe;
import io.beaniejoy.dongnecafe.cafe.dto.CafeResponseDto;
import io.beaniejoy.dongnecafe.cafe.error.CafeNotFoundException;
import io.beaniejoy.dongnecafe.cafe.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CafeService {

    private final CafeRepository cafeRepository;

    public List<CafeResponseDto> getCafeList(Pageable pageable) {

        Page<Cafe> cafeListWithPagination = cafeRepository.findAll(pageable);

        return cafeListWithPagination.stream()
                .map(Cafe::toResponseDto)
                .collect(Collectors.toList());
    }

    public CafeResponseDto getCafeByCafeId(UUID cafeId) {
        Cafe cafe = cafeRepository.findById(cafeId)
                .orElseThrow(() -> new CafeNotFoundException(cafeId));

        return cafe.toResponseDto();
    }
}

package io.beaniejoy.dongnecafe.cafe.controller;

import io.beaniejoy.dongnecafe.cafe.dto.CafeResponseDto;
import io.beaniejoy.dongnecafe.cafe.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cafes")
public class CafeController {

    private final CafeService cafeService;

    @GetMapping("/")
    public ResponseEntity<List<CafeResponseDto>> getCafeList(
            @PageableDefault(sort = "name", direction = Sort.Direction.ASC, size = 10) Pageable pageable) {
        List<CafeResponseDto> cafeResponseList = cafeService.getCafeList(pageable);

        return ResponseEntity.ok(cafeResponseList);
    }

    @GetMapping("/{cafeId}")
    public ResponseEntity<CafeResponseDto> getCafeInfo(@PathVariable("cafeId") UUID cafeId) {
        CafeResponseDto cafeResponse = cafeService.getCafeByCafeId(cafeId);

        return ResponseEntity.ok(cafeResponse);
    }
}

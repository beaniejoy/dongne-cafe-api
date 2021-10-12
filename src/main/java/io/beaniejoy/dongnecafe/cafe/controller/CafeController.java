package io.beaniejoy.dongnecafe.cafe.controller;

import io.beaniejoy.dongnecafe.cafe.dto.cafe.CafeInfoResponseDto;
import io.beaniejoy.dongnecafe.cafe.dto.cafe.CafeSearchResponseDto;
import io.beaniejoy.dongnecafe.cafe.dto.cafe.CafeUpdateRequestDto;
import io.beaniejoy.dongnecafe.cafe.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cafes")
public class CafeController {

    private final CafeService cafeService;

    @GetMapping(value = "")
    public ResponseEntity<List<CafeSearchResponseDto>> searchCafeList(
            @PageableDefault(sort = "name", direction = Sort.Direction.ASC, size = 10) Pageable pageable) {
        List<CafeSearchResponseDto> cafeResponseList = cafeService.getCafeList(pageable);

        return ResponseEntity.ok(cafeResponseList);
    }

    @GetMapping("/{cafeId}")
    public ResponseEntity<CafeInfoResponseDto> getCafeDetailedInfo(@PathVariable("cafeId") UUID cafeId) {
        CafeInfoResponseDto cafeResponse = cafeService.getCafeInfoByCafeId(cafeId);

        return ResponseEntity.ok(cafeResponse);
    }

    @PutMapping("/{cafeId}")
    public ResponseEntity<String> updateCafeInfo(
            @PathVariable("cafeId") UUID cafeId,
            @RequestBody CafeUpdateRequestDto resource) {

        cafeService.updateCafe(cafeId, resource);

        return ResponseEntity.ok("Successfully Cafe[" + cafeId + "] Info Updated");
    }
}

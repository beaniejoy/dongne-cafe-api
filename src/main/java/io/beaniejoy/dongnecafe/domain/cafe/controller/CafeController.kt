package io.beaniejoy.dongnecafe.domain.cafe.controller

import io.beaniejoy.dongnecafe.domain.cafe.dto.cafe.CafeInfoResponseDto
import io.beaniejoy.dongnecafe.domain.cafe.dto.cafe.CafeSearchResponseDto
import io.beaniejoy.dongnecafe.domain.cafe.dto.cafe.CafeUpdateRequestDto
import io.beaniejoy.dongnecafe.domain.cafe.service.CafeService
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequiredArgsConstructor
@RequestMapping("/cafes")
class CafeController(
    private val cafeService: CafeService
) {
    @GetMapping
    fun searchCafeList(
        @PageableDefault(sort = ["name"], direction = Sort.Direction.ASC, size = 10) pageable: Pageable
    ): ResponseEntity<List<CafeSearchResponseDto>> {
        val cafeResponseList = cafeService.getCafeList(pageable)
        return ResponseEntity.ok(cafeResponseList)
    }

    @GetMapping("/{cafeId}")
    fun getCafeDetailedInfo(@PathVariable("cafeId") cafeId: UUID?): ResponseEntity<CafeInfoResponseDto> {
        val cafeResponse = cafeService.getCafeInfoByCafeId(cafeId)
        return ResponseEntity.ok(cafeResponse)
    }

    @PutMapping("/{cafeId}")
    fun updateCafeInfo(
        @PathVariable("cafeId") cafeId: UUID,
        @RequestBody resource: CafeUpdateRequestDto?
    ): ResponseEntity<String> {
        cafeService.updateCafe(cafeId, resource)
        return ResponseEntity.ok("Successfully Cafe[$cafeId] Info Updated")
    }
}
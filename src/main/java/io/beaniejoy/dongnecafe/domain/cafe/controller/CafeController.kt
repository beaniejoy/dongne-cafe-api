package io.beaniejoy.dongnecafe.domain.cafe.controller

import io.beaniejoy.dongnecafe.domain.cafe.dto.cafe.CafeInfoResponseDto
import io.beaniejoy.dongnecafe.domain.cafe.dto.cafe.CafeSearchResponseDto
import io.beaniejoy.dongnecafe.domain.cafe.dto.cafe.CafeUpdateRequestDto
import io.beaniejoy.dongnecafe.domain.cafe.service.CafeService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/cafes")
class CafeController(
    private val cafeService: CafeService
) {
    @GetMapping
    fun searchCafeList(
        @PageableDefault(sort = ["name"], direction = Sort.Direction.ASC, size = 10) pageable: Pageable
    ): Page<CafeSearchResponseDto> {
        return cafeService.getCafeList(pageable)
    }

    @GetMapping("/{cafeId}")
    fun getCafeDetailedInfo(@PathVariable("cafeId") cafeId: UUID): ResponseEntity<CafeInfoResponseDto> {
        val cafeResponse = cafeService.getCafeInfoByCafeId(cafeId)
        return ResponseEntity.ok(cafeResponse)
    }

    // TODO spring boot validation 적용 필요
    @PutMapping("/{id}")
    fun updateCafeInfo(
        @PathVariable("id") id: UUID,
        @RequestBody resource: CafeUpdateRequestDto
    ): ResponseEntity<String> {
        cafeService.updateCafe(
            id = id,
            name = resource.name!!,
            address = resource.address!!,
            phoneNumber = resource.phoneNumber!!,
            description = resource.description!!
        )

        return ResponseEntity.ok("Successfully Cafe[$id] Info Updated")
    }
}
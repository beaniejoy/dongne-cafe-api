package io.beaniejoy.dongnecafe.cafe.controller

import io.beaniejoy.dongnecafe.cafe.model.CafeInputDto
import io.beaniejoy.dongnecafe.cafe.model.CafeInputDtoMapper
import io.beaniejoy.dongnecafe.cafe.model.CafeOutputDto
import io.beaniejoy.dongnecafe.cafe.model.CafeOutputDtoMapper
import io.beaniejoy.dongnecafe.cafe.service.CafeService
import io.beaniejoy.dongnecafe.common.response.ApplicationResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/cafes")
class CafeController(
    private val cafeService: CafeService,
    private val cafeInputDtoMapper: CafeInputDtoMapper,
    private val cafeOutputDtoMapper: CafeOutputDtoMapper
) {
    /**
     * 신규 카페 생성
     */
    @PostMapping
    fun registerNewCafe(@RequestBody resource: CafeInputDto.RegisterCafeRequest): ApplicationResponse<CafeOutputDto.RegisteredCafe> {
        val registerCafeCommand = cafeInputDtoMapper.of(resource)

        val newCafe = cafeService.registerCafe(registerCafeCommand)

        val response = cafeOutputDtoMapper.of(newCafe)

        return ApplicationResponse
            .success("OK")
            .data(response)
    }

    /**
     * 카페 리스트 조회(검색 기능)
     */
    @GetMapping
    fun searchCafes(
        @ModelAttribute param: CafeInputDto.SearchCafesParam,
        @PageableDefault(sort = ["name"], direction = Sort.Direction.ASC, page = 0, size = 10) pageable: Pageable
    ): ApplicationResponse<Page<CafeOutputDto.CafeSearchResponse>> {
        val searchCafesQuery = cafeInputDtoMapper.of(param)

        val searchCafes = cafeService.searchCafes(
            param = searchCafesQuery,
            pageable = pageable
        )

        val responses = searchCafes.map { cafeOutputDtoMapper.of(it) }

        return ApplicationResponse
            .success()
            .data(responses)
    }

    /**
     * 단일 카페 상세 조회
     */
    @GetMapping("/{id}")
    fun getDetailedInfo(@PathVariable("id") id: Long): ApplicationResponse<CafeOutputDto.CafeDetailedResponse> {
        val cafeDetailedInfo = cafeService.getDetailedCafe(id)

        val response = cafeOutputDtoMapper.of(cafeDetailedInfo)

        return ApplicationResponse
            .success()
            .data(response)
    }

    /**
     * 단일 카페에 대한 정보 변경
     * - Cafe 기본 정보 데이터 변경
     */
    @PutMapping("/{id}")
    fun updateInfo(
        @PathVariable("id") id: Long,
        @RequestBody resource: CafeInputDto.UpdateCafeRequest
    ): ApplicationResponse<Nothing> {
        val updateCafeCommand = cafeInputDtoMapper.of(resource)

        cafeService.updateCafe(
            id = id,
            command = updateCafeCommand
        )

        return ApplicationResponse
            .success("Successfully Cafe[$id] Info Updated")
            .build()
    }
}
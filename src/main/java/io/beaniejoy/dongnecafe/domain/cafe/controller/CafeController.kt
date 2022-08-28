package io.beaniejoy.dongnecafe.domain.cafe.controller

import io.beaniejoy.dongnecafe.domain.cafe.model.request.CafeRegisterRequest
import io.beaniejoy.dongnecafe.domain.cafe.model.request.CafeUpdateRequest
import io.beaniejoy.dongnecafe.domain.cafe.model.response.CafeDetailedInfo
import io.beaniejoy.dongnecafe.domain.cafe.model.response.CafeSearchInfo
import io.beaniejoy.dongnecafe.domain.cafe.service.CafeService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cafes")
class CafeController(
    private val cafeService: CafeService
) {
    /**
     * 신규 카페 생성
     */
    @PostMapping
    fun createNewCafe(@RequestBody resource: CafeRegisterRequest): Long {
        return cafeService.createNew(
            name = resource.name!!,
            address = resource.address!!,
            phoneNumber = resource.phoneNumber!!,
            description = resource.description!!,
            cafeMenuRequestList = resource.cafeMenuList
        )
    }

    /**
     * 카페 리스트 조회(검색 기능)
     */
    @GetMapping
    fun searchCafeList(
        @PageableDefault(sort = ["name"], direction = Sort.Direction.ASC, page = 0, size = 10) pageable: Pageable
    ): Page<CafeSearchInfo> {
        return cafeService.searchCafeList(pageable)
    }

    /**
     * 단일 카페 상세 조회
     */
    @GetMapping("/{id}")
    fun getDetailedInfo(@PathVariable("id") id: Long): CafeDetailedInfo {
        return cafeService.getDetailedInfoByCafeId(id)
    }

    /**
     * 단일 카페에 대한 정보 변경
     * - Cafe 기본 정보 데이터 변경
     */
    @PatchMapping("/{id}")
    fun updateInfo(
        @PathVariable("id") id: Long,
        @RequestBody resource: CafeUpdateRequest
    ): String {
        cafeService.updateInfo(
            id = id,
            name = resource.name!!,
            address = resource.address!!,
            phoneNumber = resource.phoneNumber!!,
            description = resource.description!!
        )

        return "Successfully Cafe[$id] Info Updated"
    }
}
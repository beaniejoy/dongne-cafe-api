package io.beaniejoy.dongnecafe.domain.cafe.controller

import io.beaniejoy.dongnecafe.domain.cafe.model.response.CafeDetailedInfo
import io.beaniejoy.dongnecafe.domain.cafe.model.response.CafeSearchInfo
import io.beaniejoy.dongnecafe.domain.cafe.model.request.CafeRegisterRequest
import io.beaniejoy.dongnecafe.domain.cafe.model.request.CafeUpdateRequest
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
    @PostMapping
    fun createCafe(@RequestBody resource: CafeRegisterRequest): Long {
        return cafeService.createCafe(
            name = resource.name!!,
            address = resource.address!!,
            phoneNumber = resource.phoneNumber!!,
            description = resource.description!!,
            cafeMenuRequestList = resource.cafeMenuList
        )
    }

    @GetMapping
    fun searchCafe(
        @PageableDefault(sort = ["name"], direction = Sort.Direction.ASC, page = 0, size = 10) pageable: Pageable
    ): Page<CafeSearchInfo> {
        return cafeService.getCafeList(pageable)
    }

    @GetMapping("/{id}")
    fun getCafeDetailedInfo(@PathVariable("id") id: Long): CafeDetailedInfo {
        return cafeService.getCafeInfoByCafeId(id)
    }

    // TODO spring boot validation 적용 필요
    @PutMapping("/{id}")
    fun updateCafeInfo(
        @PathVariable("id") id: Long,
        @RequestBody resource: CafeUpdateRequest
    ): String {
        cafeService.updateCafe(
            id = id,
            name = resource.name!!,
            address = resource.address!!,
            phoneNumber = resource.phoneNumber!!,
            description = resource.description!!
        )

        return "Successfully Cafe[$id] Info Updated"
    }
}
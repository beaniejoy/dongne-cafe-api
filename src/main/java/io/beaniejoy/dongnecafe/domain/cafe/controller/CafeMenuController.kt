package io.beaniejoy.dongnecafe.domain.cafe.controller

import io.beaniejoy.dongnecafe.domain.cafe.model.response.CafeMenuDetailedInfo
import io.beaniejoy.dongnecafe.domain.cafe.service.CafeMenuService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CafeMenuController(
    private val cafeMenuService: CafeMenuService
) {
    @GetMapping("/cafes/{cafeId}/menus/{menuId}")
    fun getCafeMenuDetailedInfo(
        @PathVariable("cafeId") cafeId: Long,
        @PathVariable("menuId") menuId: Long
    ): CafeMenuDetailedInfo {
        return cafeMenuService.getCafeMenuInfoByCafeIdAndMenuId(
            menuId = menuId,
            cafeId = cafeId
        )
    }
}
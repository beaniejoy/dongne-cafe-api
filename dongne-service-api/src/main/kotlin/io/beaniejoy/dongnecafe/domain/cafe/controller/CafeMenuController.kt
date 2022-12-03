package io.beaniejoy.dongnecafe.domain.cafe.controller

import io.beaniejoy.dongnecafe.common.response.ApplicationResponse
import io.beaniejoy.dongnecafe.domain.cafe.model.request.CafeMenuBulkDeleteRequest
import io.beaniejoy.dongnecafe.domain.cafe.model.request.CafeMenuUpdateRequest
import io.beaniejoy.dongnecafe.domain.cafe.service.CafeMenuService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/cafes/{cafeId}/menus")
class CafeMenuController(
    private val cafeMenuService: CafeMenuService
) {
    /**
     * 단일 카페 메뉴 상세 조회
     */
    @GetMapping("/{menuId}")
    fun getDetailedInfo(
        @PathVariable("cafeId") cafeId: Long,
        @PathVariable("menuId") menuId: Long
    ): ApplicationResponse {
        val cafeMenuDetailedInfo = cafeMenuService.getDetailedInfoByMenuId(
            menuId = menuId,
            cafeId = cafeId
        )

        return ApplicationResponse
            .success()
            .data(cafeMenuDetailedInfo)
    }

    /**
     * 단일 카페 메뉴에 대한 정보 변경
     * - CafeMenu 기본 정보 데이터 변경
     * - MenuOption List bulkUpdate (삭제, 변경)
     * - OptionDetail List bulkUpdate (삭제, 변경)
     */
    @PatchMapping("/{menuId}")
    fun updateInfoAndBulkUpdateOptions(
        @PathVariable("cafeId") cafeId: Long,
        @PathVariable("menuId") menuId: Long,
        @RequestBody cafeMenuUpdateRequest: CafeMenuUpdateRequest
    ): ApplicationResponse {
        cafeMenuService.updateInfoAndBulkUpdate(
            menuId = menuId,
            cafeId = cafeId,
            resource = cafeMenuUpdateRequest
        )

        return ApplicationResponse.success("Success Update Cafe[$cafeId]'s CafeMenu[$menuId]")
    }

    /**
     * 단일 카페 메뉴 삭제
     */
    @DeleteMapping("/{menuId}")
    fun delete(
        @PathVariable("cafeId") cafeId: Long,
        @PathVariable("menuId") menuId: Long
    ): ApplicationResponse {
        cafeMenuService.deleteByCafeMenuId(
            menuId = menuId,
            cafeId = cafeId
        )

        return ApplicationResponse.success("Success Delete Cafe[$cafeId]'s CafeMenu[$menuId]")
    }

    /**
     * 여러 카페 메뉴에 대한 bulk 삭제
     */
    @DeleteMapping("")
    fun bulkDelete(
        @PathVariable("cafeId") cafeId: Long,
        @RequestBody resource: CafeMenuBulkDeleteRequest
    ): ApplicationResponse {
        cafeMenuService.bulkDelete(cafeId, resource.cafeMenuIdList)

        return ApplicationResponse.success("Success Delete Cafe[$cafeId]'s CafeMenu List")
    }
}
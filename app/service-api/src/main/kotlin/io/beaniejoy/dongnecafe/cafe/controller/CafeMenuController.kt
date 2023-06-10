//package io.beaniejoy.dongnecafe.cafe.controller
//
//import io.beaniejoy.dongnecafe.cafe.model.response.CafeMenuDetailedInfo
//import io.beaniejoy.dongnecafe.cafe.service.CafeMenuService
//import io.beaniejoy.dongnecafe.common.response.ApplicationResponse
//import org.springframework.web.bind.annotation.*
//
//@RestController
//@RequestMapping("/api/cafes/{cafeId}/menus")
//class CafeMenuController(
//    private val cafeMenuService: CafeMenuService
//) {
//    /**
//     * 단일 카페 메뉴 상세 조회
//     */
//    @GetMapping("/{menuId}")
//    fun getDetailedInfo(
//        @PathVariable("cafeId") cafeId: Long,
//        @PathVariable("menuId") menuId: Long
//    ): ApplicationResponse<CafeMenuDetailedInfo> {
//        val cafeMenuDetailedInfo = cafeMenuService.getDetailedInfoByMenuId(
//            menuId = menuId,
//            cafeId = cafeId
//        )
//
//        return ApplicationResponse
//            .success()
//            .data(cafeMenuDetailedInfo)
//    }
//
//    /**
//     * 단일 카페 메뉴에 대한 정보 변경
//     * - CafeMenu 기본 정보 데이터 변경
//     * - MenuOption List bulkUpdate (삭제, 변경)
//     * - OptionDetail List bulkUpdate (삭제, 변경)
//     */
//    @PatchMapping("/{menuId}")
//    fun updateInfoAndBulkUpdateOptions(
//        @PathVariable("cafeId") cafeId: Long,
//        @PathVariable("menuId") menuId: Long,
//        @RequestBody cafeMenuUpdateRequest: io.beaniejoy.dongnecafe.cafe.model.request.CafeMenuUpdateRequest
//    ): ApplicationResponse<Nothing> {
//        cafeMenuService.updateInfoAndBulkUpdate(
//            menuId = menuId,
//            cafeId = cafeId,
//            resource = cafeMenuUpdateRequest
//        )
//
//        return ApplicationResponse
//            .success("Success Update Cafe[$cafeId]'s CafeMenu[$menuId]")
//            .build()
//    }
//
//    /**
//     * 단일 카페 메뉴 삭제
//     */
//    @DeleteMapping("/{menuId}")
//    fun delete(
//        @PathVariable("cafeId") cafeId: Long,
//        @PathVariable("menuId") menuId: Long
//    ): ApplicationResponse<Nothing> {
//        cafeMenuService.deleteByCafeMenuId(
//            menuId = menuId,
//            cafeId = cafeId
//        )
//
//        return ApplicationResponse
//            .success("Success Delete Cafe[$cafeId]'s CafeMenu[$menuId]")
//            .build()
//    }
//
//    /**
//     * 여러 카페 메뉴에 대한 bulk 삭제
//     */
//    @DeleteMapping("")
//    fun bulkDelete(
//        @PathVariable("cafeId") cafeId: Long,
//        @RequestBody resource: io.beaniejoy.dongnecafe.cafe.model.request.CafeMenuBulkDeleteRequest
//    ): ApplicationResponse<Nothing> {
//        cafeMenuService.bulkDelete(cafeId, resource.cafeMenuIds)
//
//        return ApplicationResponse
//            .success("Success Delete Cafe[$cafeId]'s CafeMenu List")
//            .build()
//    }
//}
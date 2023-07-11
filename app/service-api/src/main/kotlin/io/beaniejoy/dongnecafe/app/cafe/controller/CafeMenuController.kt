package io.beaniejoy.dongnecafe.app.cafe.controller

import io.beaniejoy.dongnecafe.app.cafe.model.request.CafeInputDto
import io.beaniejoy.dongnecafe.app.cafe.model.request.CafeInputDtoMapper
import io.beaniejoy.dongnecafe.app.cafe.model.response.CafeOutputDto
import io.beaniejoy.dongnecafe.app.cafe.model.response.CafeOutputDtoMapper
import io.beaniejoy.dongnecafe.common.response.ApplicationResponse
import io.beaniejoy.dongnecafe.domain.cafe.service.CafeMenuService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/cafes/{cafeId}/categories/{menuCategoryId}/menus")
class CafeMenuController(
    private val cafeMenuService: CafeMenuService,
    private val cafeInputDtoMapper: CafeInputDtoMapper,
    private val cafeOutputDtoMapper: CafeOutputDtoMapper
) {
    /**
     * 특정 카페에 대한 신규 카페메뉴 생성(단일)
     */
    @PostMapping
    fun register(
        @PathVariable("cafeId") cafeId: Long,
        @PathVariable("menuCategoryId") menuCategoryId: Long,
        @RequestBody resource: CafeInputDto.RegisterCafeMenuRequest
    ): ApplicationResponse<CafeOutputDto.RegisteredCafeMenuResponse> {
        val registerCommand = cafeInputDtoMapper.of(resource)

        val newCafeMenu = cafeMenuService.registerCafeMenu(
            cafeId = cafeId,
            menuCategoryId = menuCategoryId,
            command = registerCommand
        )

        val response = cafeOutputDtoMapper.of(newCafeMenu)

        return ApplicationResponse.created().data(response)
    }

    /**
     * 단일 카페 메뉴에 대한 정보 변경
     * - CafeMenu 기본 정보 데이터 변경
     * - MenuOption List bulkUpdate (삭제, 변경)
     * - OptionDetail List bulkUpdate (삭제, 변경)
     */
    @PutMapping("/{menuId}")
    fun updateInfoAndBulkUpdateSeries(
        @PathVariable("cafeId") cafeId: Long,
        @PathVariable("menuCategoryId") menuCategoryId: Long,
        @PathVariable("menuId") menuId: Long,
        @RequestBody resource: CafeInputDto.UpdateCafeMenuRequest
    ): ApplicationResponse<Nothing> {
        val updateCommand = cafeInputDtoMapper.of(resource)

        cafeMenuService.updateCafeMenuWithSeries(
            cafeId = cafeId,
            menuCategoryId = menuCategoryId,
            menuId = menuId,
            command = updateCommand
        )

        return ApplicationResponse
            .success("Success Update Cafe[$cafeId]'s CafeMenu[$menuId]")
            .build()
    }

    /**
     * 여러 카페 메뉴에 대한 bulk 삭제
     */
    @DeleteMapping
    fun bulkDelete(
        @PathVariable("cafeId") cafeId: Long,
        @PathVariable("menuCategoryId") menuCategoryId: Long,
        @RequestBody resource: CafeInputDto.BulkDeleteCafeMenusRequest
    ): ApplicationResponse<Nothing> {
        cafeMenuService.bulkDeleteCafeMenus(
            cafeId = cafeId,
            menuCategoryId = menuCategoryId,
            deleteMenuIds = resource.cafeMenuIds
        )

        return ApplicationResponse
            .success("Success Delete Cafe[$cafeId]'s CafeMenu List")
            .build()
    }
}
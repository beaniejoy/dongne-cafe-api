package io.beaniejoy.dongnecafe.cafe.controller

import io.beaniejoy.dongnecafe.cafe.model.request.CafeInputDto
import io.beaniejoy.dongnecafe.cafe.model.request.CafeInputDtoMapper
import io.beaniejoy.dongnecafe.cafe.model.response.CafeOutputDto
import io.beaniejoy.dongnecafe.cafe.model.response.CafeOutputDtoMapper
import io.beaniejoy.dongnecafe.cafe.service.CafeMenuCategoryService
import io.beaniejoy.dongnecafe.common.response.ApplicationResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/cafes/{cafeId}/categories")
class CafeMenuCategoryController(
    private val cafeMenuCategoryService: CafeMenuCategoryService,
    private val cafeInputDtoMapper: CafeInputDtoMapper,
    private val cafeOutputDtoMapper: CafeOutputDtoMapper
) {
    @PostMapping
    fun register(
        @PathVariable("cafeId") cafeId: Long,
        @RequestBody resource: CafeInputDto.RegisterCafeMenuCategoryRequest
    ): ApplicationResponse<CafeOutputDto.RegisteredCafeMenuCategoryResponse> {
        val registerCommand = cafeInputDtoMapper.of(resource)

        val newCategory = cafeMenuCategoryService.registerMenuCategory(
            cafeId = cafeId,
            command = registerCommand
        )

        val response = cafeOutputDtoMapper.of(newCategory)

        return ApplicationResponse.created().data(response)
    }

    @PutMapping("/{menuCategoryId}")
    fun updateInfo(
        @PathVariable("cafeId") cafeId: Long,
        @PathVariable("menuCategoryId") menuCategoryId: Long,
        @RequestBody resource: CafeInputDto.UpdateCafeMenuCategoryRequest
    ): ApplicationResponse<Nothing> {
        val updateCommand = cafeInputDtoMapper.of(resource)

        cafeMenuCategoryService.updateMenuCategory(
            cafeId = cafeId,
            menuCategoryId = menuCategoryId,
            command = updateCommand
        )

        return ApplicationResponse.updated().build()
    }

    @DeleteMapping("/{menuCategoryId}")
    fun delete(
        @PathVariable("cafeId") cafeId: Long,
        @PathVariable("menuCategoryId") menuCategoryId: Long
    ) {
        cafeMenuCategoryService.deleteMenuCategory(cafeId, menuCategoryId)
    }
}
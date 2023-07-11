package io.beaniejoy.dongnecafe.app.cafe.model.request

import io.beaniejoy.dongnecafe.domain.cafe.model.CafeCommand
import io.beaniejoy.dongnecafe.domain.cafe.model.CafeQuery
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
interface CafeInputDtoMapper {
    // from Dto to Command
    fun of(request: CafeInputDto.RegisterCafeRequest): CafeCommand.RegisterCafe
    fun of(request: CafeInputDto.RegisterCafeMenuCategoryRequest): CafeCommand.RegisterCafeMenuCategory
    fun of(request: CafeInputDto.RegisterCafeMenuRequest): CafeCommand.RegisterCafeMenu
    fun of(request: CafeInputDto.UpdateCafeRequest): CafeCommand.UpdateCafe
    fun of(request: CafeInputDto.UpdateCafeMenuCategoryRequest): CafeCommand.UpdateCafeMenuCategory
    fun of(request: CafeInputDto.UpdateCafeMenuRequest): CafeCommand.UpdateCafeMenu

    // from Dto to Query
    fun of(param: CafeInputDto.SearchCafesParam): CafeQuery.SearchCafesParam

}
package io.beaniejoy.dongnecafe.cafe.model.response

import io.beaniejoy.dongnecafe.cafe.model.CafeInfo
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
interface CafeOutputDtoMapper {
    fun of(registeredInfo: CafeInfo.RegisteredCafe): CafeOutputDto.RegisteredCafeResponse
    fun of(registeredInfo: CafeInfo.RegisteredCafeMenuCategory): CafeOutputDto.RegisteredCafeMenuCategoryResponse
    fun of(registeredInfo: CafeInfo.RegisteredCafeMenu): CafeOutputDto.RegisteredCafeMenuResponse

    fun of(cafeSearchInfo: CafeInfo.CafeSearchInfo): CafeOutputDto.CafeSearchResponse

    fun of(cafeDetailedInfo: CafeInfo.CafeDetailedInfo): CafeOutputDto.CafeDetailedResponse
}
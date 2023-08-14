package io.beaniejoy.dongnecafe.app.cafe.model.response

import io.beaniejoy.dongnecafe.domain.cafe.model.CafeInfo
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
    // #### [START] response of registered info ####
    fun of(registeredInfo: CafeInfo.RegisteredCafe): CafeOutputDto.RegisteredCafeResponse
    fun of(registeredInfo: CafeInfo.RegisteredCafeMenuCategory): CafeOutputDto.RegisteredCafeMenuCategoryResponse
    fun of(registeredInfo: CafeInfo.RegisteredCafeMenu): CafeOutputDto.RegisteredCafeMenuResponse
    // #### [END] response of registered info ####

    fun of(cafeSearchInfo: CafeInfo.CafeSearchInfo): CafeOutputDto.CafeSearchResponse

    fun of(cafeDetailedInfo: CafeInfo.CafeDetailedInfo): CafeOutputDto.CafeDetailedResponse
}

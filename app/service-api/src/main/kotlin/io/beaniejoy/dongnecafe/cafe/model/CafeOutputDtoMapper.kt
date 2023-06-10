package io.beaniejoy.dongnecafe.cafe.model

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
    fun of(registeredCafe: CafeInfo.RegisteredCafe): CafeOutputDto.RegisteredCafe

    fun of(cafeSearchInfo: CafeInfo.CafeSearchInfo): CafeOutputDto.CafeSearchResponse

    fun of(cafeDetailedInfo: CafeInfo.CafeDetailedInfo): CafeOutputDto.CafeDetailedResponse
}
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
interface CafeInputDtoMapper {
    // from Dto to Command
    fun of(request: CafeInputDto.RegisterCafeRequest): CafeCommand.RegisterCafe

    // from Dto to Query
    fun of(param: CafeInputDto.SearchCafesParam): CafeQuery.SearchCafesParam

    fun of(request: CafeInputDto.UpdateCafeRequest): CafeCommand.UpdateCafe
}
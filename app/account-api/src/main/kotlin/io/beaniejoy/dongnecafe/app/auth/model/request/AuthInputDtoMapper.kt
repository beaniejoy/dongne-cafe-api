package io.beaniejoy.dongnecafe.app.auth.model.request

import io.beaniejoy.dongnecafe.domain.auth.model.AuthCommand
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
interface AuthInputDtoMapper {
    fun of(request: AuthInputDto.RefreshAuthTokenRequest): AuthCommand.RefreshAuthToken
}

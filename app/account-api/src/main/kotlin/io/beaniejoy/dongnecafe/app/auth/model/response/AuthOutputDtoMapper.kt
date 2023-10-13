package io.beaniejoy.dongnecafe.app.auth.model.response

import io.beaniejoy.dongnecafe.domain.auth.model.AuthInfo
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
interface AuthOutputDtoMapper {
    fun of(registeredAuthToken: AuthInfo.RegisteredAuthToken): AuthOutputDto.RegisteredAuthTokenResponse
}

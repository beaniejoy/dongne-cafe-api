package io.beaniejoy.dongnecafe.domain.auth.model

import io.beaniejoy.dongnecafe.domain.auth.entity.AuthToken
import org.mapstruct.*

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
interface AuthInfoMapper {
    @Mapping(source = "authToken.id", target = "memberId")
    fun of(authToken: AuthToken): AuthInfo.RegisteredAuthToken
}

package io.beaniejoy.dongnecafe.domain.auth.model

import io.beaniejoy.dongnecafe.domain.auth.entity.AuthToken
import org.mapstruct.*

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
interface AuthInfoMapper {
    @Mapping(source = "authToken.member.id", target = "memberId")
    @Mapping(source = "authToken.member.email", target = "email")
    fun of(authToken: AuthToken): AuthInfo.RegisteredAuthToken
}

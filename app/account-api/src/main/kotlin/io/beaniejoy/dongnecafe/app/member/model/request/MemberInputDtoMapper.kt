package io.beaniejoy.dongnecafe.app.member.model.request

import io.beaniejoy.dongnecafe.domain.member.model.MemberCommand
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
interface MemberInputDtoMapper {
    fun of(request: MemberInputDto.RegisterMemberRequest): MemberCommand.RegisterMember
}

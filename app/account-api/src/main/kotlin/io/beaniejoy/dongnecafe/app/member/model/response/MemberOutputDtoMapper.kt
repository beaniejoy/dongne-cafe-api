package io.beaniejoy.dongnecafe.app.member.model.response

import io.beaniejoy.dongnecafe.domain.member.model.MemberInfo
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
interface MemberOutputDtoMapper {
    // #### [START] response of registered info ####
    fun of(registeredInfo: MemberInfo.RegisteredMember): MemberOutputDto.RegisteredMemberResponse
    // #### [END] response of registered info ####
}

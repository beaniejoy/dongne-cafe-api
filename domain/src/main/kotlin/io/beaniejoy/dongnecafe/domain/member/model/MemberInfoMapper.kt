package io.beaniejoy.dongnecafe.domain.member.model

import io.beaniejoy.dongnecafe.domain.member.entity.Member
import org.mapstruct.*

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
interface MemberInfoMapper {
    // #### [START] response of registered entity ####
    @Mapping(source = "member.id", target = "memberId")
    fun of(member: Member): MemberInfo.RegisteredMember
    // #### [END] response of registered entity ####
}

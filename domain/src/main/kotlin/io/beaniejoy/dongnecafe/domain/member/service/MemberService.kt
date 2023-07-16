package io.beaniejoy.dongnecafe.domain.member.service

import io.beaniejoy.dongnecafe.domain.member.model.MemberCommand
import io.beaniejoy.dongnecafe.domain.member.model.MemberInfo

interface MemberService {
    fun registerMember(command: MemberCommand.RegisterMember): MemberInfo.RegisteredMember
}
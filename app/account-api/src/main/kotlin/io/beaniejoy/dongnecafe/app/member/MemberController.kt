package io.beaniejoy.dongnecafe.app.member

import io.beaniejoy.dongnecafe.app.member.model.request.MemberInputDto
import io.beaniejoy.dongnecafe.app.member.model.request.MemberInputDtoMapper
import io.beaniejoy.dongnecafe.app.member.model.response.MemberOutputDto
import io.beaniejoy.dongnecafe.app.member.model.response.MemberOutputDtoMapper
import io.beaniejoy.dongnecafe.domain.common.response.ApplicationResponse
import io.beaniejoy.dongnecafe.domain.member.service.MemberService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth/members")
class MemberController(
    private val memberService: MemberService,
    private val memberInputDtoMapper: MemberInputDtoMapper,
    private val memberOutputDtoMapper: MemberOutputDtoMapper
) {
    @PostMapping("/join")
    fun joinMember(
        @RequestBody resource: MemberInputDto.RegisterMemberRequest
    ): ApplicationResponse<MemberOutputDto.RegisteredMemberResponse> {
        val registerCommand = memberInputDtoMapper.of(resource)

        val joinedMember = memberService.registerMember(registerCommand)

        val response = memberOutputDtoMapper.of(joinedMember)

        return ApplicationResponse.created().data(response)
    }
}

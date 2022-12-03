package io.beaniejoy.dongnecafe.controller

import io.beaniejoy.dongnecafe.common.response.ApplicationResponse
import io.beaniejoy.dongnecafe.domain.member.model.request.MemberRegisterRequest
import io.beaniejoy.dongnecafe.service.MemberService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth/members")
class MemberController(
    private val memberService: MemberService
) {
    @PostMapping("/sign-up")
    fun signUp(@RequestBody resource: MemberRegisterRequest): ApplicationResponse {
        val registerMemberId = memberService.registerMember(resource)

        return ApplicationResponse
            .success("success sign up")
            .data(registerMemberId)
    }
}
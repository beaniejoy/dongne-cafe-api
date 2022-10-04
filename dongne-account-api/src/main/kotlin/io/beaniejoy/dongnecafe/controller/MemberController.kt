package io.beaniejoy.dongnecafe.controller

import io.beaniejoy.dongnecafe.domain.member.model.request.MemberRegisterRequest
import io.beaniejoy.dongnecafe.service.MemberService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/members")
class MemberController(
    private val memberService: MemberService
) {
    @PostMapping("/signup")
    fun signUp(@RequestBody resource: MemberRegisterRequest): Long {
        return memberService.registerMember(resource)
    }
}
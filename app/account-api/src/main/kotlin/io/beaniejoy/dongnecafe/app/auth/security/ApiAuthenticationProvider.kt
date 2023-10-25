package io.beaniejoy.dongnecafe.app.auth.security

import io.beaniejoy.dongnecafe.domain.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.domain.member.model.SecurityUser
import mu.KLogging
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

/**
 * 실제 인증 절차 수행
 * @property userDetailsService email로 계정 찾기
 */
@Component
class ApiAuthenticationProvider(
    private val userDetailsService: UserDetailsService,
    private val passwordEncoder: PasswordEncoder
) : AuthenticationProvider {
    companion object : KLogging()

    override fun authenticate(authentication: Authentication): Authentication {
        logger.info { "start authentication" }

        val email = authentication.name
        val password = authentication.credentials as String?

        val user = userDetailsService.loadUserByUsername(email) as SecurityUser
        check(passwordEncoder.matches(password, user.password)) {
            throw BadCredentialsException(ErrorCode.AUTH_PASSWORD_NOT_VALID.name)
        }

        return UsernamePasswordAuthenticationToken(user.member.id, null, user.authorities)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}

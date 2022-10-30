package io.beaniejoy.dongnecafe.common.security

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.beaniejoy.dongnecafe.domain.member.model.request.SignInRequest
import mu.KotlinLogging
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.util.StringUtils
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ApiAuthenticationFilter(requestMatcher: AntPathRequestMatcher) :
    AbstractAuthenticationProcessingFilter(requestMatcher) {

    private val log = KotlinLogging.logger {}

    private val objectMapper = jacksonObjectMapper()

    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
    ): Authentication {
        log.info { "[API Filter] attempt to authenticate" }
        if (isPostMethod(request).not()) {
            val errorMsg = "Authentication is not supported (only support for POST method)"
            log.error { errorMsg }
            throw IllegalStateException(errorMsg)
        }

        val signInRequest = objectMapper.readValue(request.reader, SignInRequest::class.java)
        request.setAttribute("email", signInRequest.email)

        val token = signInRequest.let {
            if (StringUtils.hasText(it.email).not() || StringUtils.hasText(it.password).not()) {
                log.error { "Email(${it.email}) & Password are not empty" }
                throw IllegalArgumentException("Email & Password are not empty!!")
            }

            UsernamePasswordAuthenticationToken(it.email, it.password)
        }

        val authenticate = authenticationManager.authenticate(token)
        logger.info("attempt authentication ${authenticate.principal}")
        return authenticate
    }

    private fun isPostMethod(request: HttpServletRequest): Boolean {
        if (request.method != HttpMethod.POST.name) {
            return false
        }

        return true
    }
}
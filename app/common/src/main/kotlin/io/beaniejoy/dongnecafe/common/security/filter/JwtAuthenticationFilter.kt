package io.beaniejoy.dongnecafe.common.security.filter

import io.beaniejoy.dongnecafe.common.security.helper.SecurityHelper
import io.beaniejoy.dongnecafe.domain.common.utils.security.AuthTokenType
import io.beaniejoy.dongnecafe.domain.common.utils.security.JwtTokenUtils
import mu.KotlinLogging
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class JwtAuthenticationFilter(
    private val jwtTokenUtils: JwtTokenUtils,
) : GenericFilterBean() {
    private val log = KotlinLogging.logger {}

    /**
     * JWT access token 인가 처리
     */
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val httpRequest = request as HttpServletRequest
        log.info { "[JwtAuthenticationFilter][${request.dispatcherType}] uri: ${request.requestURI}" }

        // 인증 헤더에 토큰값 없는 경우 pass
        SecurityHelper.getAuthTokenFromRequest(httpRequest)?.let {
            jwtTokenUtils.getAuthentication(
                authToken = it,
                tokenType = AuthTokenType.ACCESS
            )
        }?.also {
            // 유효한 인증 토큰 존재하는 경우 SecurityContext 토큰값 저장
            SecurityContextHolder.getContext().authentication = it
            log.info { "Valid Access Token [${it.name}]" }
        }

        chain.doFilter(request, response)
    }
}

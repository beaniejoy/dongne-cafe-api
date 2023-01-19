package io.beaniejoy.dongnecafe.security.filter

import io.beaniejoy.dongnecafe.security.JwtTokenUtils
import io.beaniejoy.dongnecafe.security.constant.SecurityConstant.BEARER
import io.beaniejoy.dongnecafe.security.constant.SecurityConstant.WHITESPACE
import mu.KotlinLogging
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class JwtAuthenticationFilter(
    private val jwtTokenUtils: JwtTokenUtils
) : GenericFilterBean() {
    private val log = KotlinLogging.logger {}

    /**
     * JWT access token 인증 처리
     */
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val httpRequest = request as HttpServletRequest
        log.info { "[JwtAuthenticationFilter][${request.dispatcherType}] uri: ${request.requestURI}" }

        getAccessToken(httpRequest)?.let {
            jwtTokenUtils.getAuthentication(it)
        }?.also {
            SecurityContextHolder.getContext().authentication = it
            log.info { "Valid Access Token [${it.name}]" }
        }

        chain.doFilter(request, response)
    }

    private fun getAccessToken(request: HttpServletRequest): String? {
        val bearer = request.getHeader(HttpHeaders.AUTHORIZATION)
            ?: return null

        val splitBearer = bearer.split(WHITESPACE)
        if (splitBearer.first() != BEARER) {
            return null
        }

        if (splitBearer.size != 2 || splitBearer.last().isBlank()) {
            return null
        }

        return splitBearer.last()
    }
}
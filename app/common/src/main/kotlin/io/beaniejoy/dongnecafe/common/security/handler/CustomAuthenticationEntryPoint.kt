package io.beaniejoy.dongnecafe.common.security.handler

import io.beaniejoy.dongnecafe.common.security.helper.SecurityFilterHelper
import io.beaniejoy.dongnecafe.domain.common.error.exception.TokenExpiredException
import io.beaniejoy.dongnecafe.domain.common.utils.security.JwtTokenUtils
import mu.KLogging
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerExceptionResolver
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomAuthenticationEntryPoint(
    private val handlerExceptionResolver: HandlerExceptionResolver,
    private val jwtTokenUtils: JwtTokenUtils
) : AuthenticationEntryPoint {
    companion object : KLogging()

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        logger.info { "Unauthorized Error!! ${SecurityFilterHelper.getAccessToken(request)}" }

        val convertedAuthException = getValidAuthException(
            request = request,
            originException = authException
        )

        handlerExceptionResolver.resolveException(request, response, null, convertedAuthException)
    }

    private fun getValidAuthException(
        request: HttpServletRequest,
        originException: AuthenticationException
    ): AuthenticationException {
        val accessToken = SecurityFilterHelper.getAccessToken(request) ?: run {
            return originException
        }

        // 인증 오류시 토큰 만료 케이스인지 체크
        if (jwtTokenUtils.checkTokenExpired(accessToken)) {
            return TokenExpiredException()
        }

        return originException
    }
}

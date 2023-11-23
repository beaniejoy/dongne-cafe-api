package io.beaniejoy.dongnecafe.app.common.interceptor

import io.beaniejoy.dongnecafe.app.auth.model.request.AuthInputDto
import io.beaniejoy.dongnecafe.app.common.annotation.RefreshTokenCookie
import io.beaniejoy.dongnecafe.common.security.utils.SecurityHelper
import io.beaniejoy.dongnecafe.domain.common.error.exception.auth.UnauthorizedMemberException
import io.beaniejoy.dongnecafe.domain.common.utils.security.AuthTokenType
import io.beaniejoy.dongnecafe.domain.common.utils.security.JwtTokenUtils
import io.beaniejoy.dongnecafe.domain.common.utils.security.getMemberId
import jakarta.servlet.http.HttpServletRequest
import mu.KLogging
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class RefreshTokenCookieResolver(
    private val jwtTokenUtils: JwtTokenUtils
) : HandlerMethodArgumentResolver {

    companion object : KLogging()

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.getParameterAnnotation(RefreshTokenCookie::class.java) != null
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): AuthInputDto.SearchAuthTokenRequest {
        logger.info { "###### extract refresh token from cookie ######" }

        val refreshToken = SecurityHelper.getAuthTokenFromRequest(
            request = webRequest.nativeRequest as HttpServletRequest,
            tokenType = AuthTokenType.REFRESH,
            isOnlyCookie = true
        ) ?: throw UnauthorizedMemberException("no refresh token")

        val memberId = jwtTokenUtils.getAuthentication(
            authToken = refreshToken,
            tokenType = AuthTokenType.REFRESH
        )?.getMemberId()
            ?: throw UnauthorizedMemberException("invalid refresh token")

        return AuthInputDto.SearchAuthTokenRequest(
            memberId = memberId,
            refreshToken = refreshToken
        )
    }
}

package io.beaniejoy.dongnecafe.common.security.handler

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.beaniejoy.dongnecafe.common.security.model.AuthenticationResult
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class ApiAuthenticationSuccessHandler :AuthenticationSuccessHandler {
    private val objectMapper = jacksonObjectMapper()

    companion object: KLogging()

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication,
    ) {
        val user = authentication.principal as User
        logger.info { "[AUTH SUCCESS] email: ${user.username}, authorities: ${user.authorities}" }

        response.apply {
            this.status = HttpStatus.OK.value()
            this.contentType = MediaType.APPLICATION_JSON_VALUE
        }

        objectMapper.writeValue(
            response.writer,
            AuthenticationResult(
                email = user.username,
                authorities = user.authorities,
                msg = "authentication success"
            )
        )
    }
}
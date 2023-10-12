package io.beaniejoy.dongnecafe.app.config

import io.beaniejoy.dongnecafe.common.security.config.JwtAuthenticationConfigurer
import io.beaniejoy.dongnecafe.common.security.handler.CustomAccessDeniedHandler
import io.beaniejoy.dongnecafe.common.security.handler.CustomAuthenticationEntryPoint
import io.beaniejoy.dongnecafe.domain.common.utils.security.JwtTokenUtils
import io.beaniejoy.dongnecafe.domain.member.constant.RoleType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties
import org.springframework.boot.autoconfigure.security.StaticResourceLocation
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Autowired
    lateinit var jwtTokenUtils: JwtTokenUtils

    @Autowired
    lateinit var customAccessDeniedHandler: CustomAccessDeniedHandler

    @Autowired
    lateinit var customAuthenticationEntryPoint: CustomAuthenticationEntryPoint

    @Autowired
    lateinit var actuatorProperties: WebEndpointProperties

    companion object {
        val permittedUrls = arrayOf(
            "/error",
            "/auth/members/join",
            "/auth/authenticate",
            "/auth/token/refresh"
        )

        // resource urls
        val resourceUrls = StaticResourceLocation
            .values()
            .flatMap {
                it.patterns.toList()
            }
            .toTypedArray()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf().disable()

            .authorizeRequests()
            .antMatchers("${actuatorProperties.basePath}/**").hasRole(RoleType.ROLE_MONITORING.securityRoleName())
            .antMatchers(*permittedUrls, *resourceUrls).permitAll()
            .anyRequest().authenticated()

            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰 방식(세션 불필요)

            .and()
            .also { jwtAuthenticationConfigurer(it) }
            .exceptionHandling()
            .authenticationEntryPoint(customAuthenticationEntryPoint)   // 인증 예외 entryPoint 적용
            .accessDeniedHandler(customAccessDeniedHandler)             // 인가 예외 handler 적용

            .and()
            .build()
    }

    private fun jwtAuthenticationConfigurer(http: HttpSecurity) {
        http
            .apply(JwtAuthenticationConfigurer())
            .jwtTokenUtils(jwtTokenUtils)
    }
}

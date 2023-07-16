package io.beaniejoy.dongnecafe.common.config

import io.beaniejoy.dongnecafe.security.JwtTokenUtils
import io.beaniejoy.dongnecafe.security.config.JwtAuthenticationConfigurer
import io.beaniejoy.dongnecafe.security.handler.CustomAccessDeniedHandler
import io.beaniejoy.dongnecafe.security.handler.CustomAuthenticationEntryPoint
import org.springframework.beans.factory.annotation.Autowired
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

    companion object {
        val permittedUrls = arrayOf(
            "/error",
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
            // only api 방식 인증 & 인가 적용 위해 csrf & formLogin 비활성화
            .csrf().disable()
            .formLogin().disable()

            .authorizeRequests()
            .antMatchers(*permittedUrls, *resourceUrls).permitAll()
            // TODO 임시 적용(추후에 모든 api에 대해서 인증 여부 통한 authenticated 필요)
//            .anyRequest().authenticated()
            .anyRequest().permitAll()

            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰 방식(세션 불필요)

            .and()
            .also { jwtAuthenticationConfigurer(it) }
            .exceptionHandling()
            .authenticationEntryPoint(customAuthenticationEntryPoint)   // 인증 예외 entryPoint 적용
            .accessDeniedHandler(customAccessDeniedHandler) // 인가 예외 handler 적용

            .and()
            .build()
    }

    private fun jwtAuthenticationConfigurer(http: HttpSecurity) {
        http
            .apply(JwtAuthenticationConfigurer())
            .jwtTokenUtils(jwtTokenUtils)
    }
}
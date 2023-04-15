package io.beaniejoy.dongnecafe.common.config

import io.beaniejoy.dongnecafe.utils.security.JwtTokenUtils
import io.beaniejoy.dongnecafe.infra.security.config.JwtAuthenticationConfigurer
import io.beaniejoy.dongnecafe.infra.security.handler.CustomAccessDeniedHandler
import io.beaniejoy.dongnecafe.infra.security.handler.CustomAuthenticationEntryPoint
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
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

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf().disable()

            .authorizeRequests()
            .antMatchers("/auth/members/sign-up").permitAll()
            .antMatchers("/auth/authenticate").permitAll()
            .anyRequest().authenticated()

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

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web ->
            web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations())
            web.ignoring().antMatchers("/error")
        }
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }
}
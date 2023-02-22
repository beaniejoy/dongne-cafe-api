package io.beaniejoy.dongnecafe.common.config

import io.beaniejoy.dongnecafe.security.JwtAuthenticationConfigurer
import io.beaniejoy.dongnecafe.security.JwtTokenUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Autowired
    lateinit var jwtTokenUtils: JwtTokenUtils

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            // only api 방식 인증 & 인가 적용 위해 csrf & formLogin 비활성화
            .csrf().disable()
            .formLogin().disable()

            .authorizeRequests()
            .anyRequest().authenticated()

            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰 방식(세션 불필요)

            .and()
            .also { jwtAuthenticationConfigurer(it) }
            .build()
    }

    private fun jwtAuthenticationConfigurer(http: HttpSecurity) {
        http
            .apply(JwtAuthenticationConfigurer())
            .jwtTokenUtils(jwtTokenUtils)
    }

    // Security Filter 미적용 자원 설정
    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web ->
            web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations())
            web.ignoring().antMatchers("/error")
        }
    }
}
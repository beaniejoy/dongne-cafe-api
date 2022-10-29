package io.beaniejoy.dongnecafe.common.config

import io.beaniejoy.dongnecafe.common.security.ApiAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf().disable()
            .formLogin().disable()

            .authorizeRequests()
            .antMatchers("/auth/members/sign-up").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(apiAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)

            .build()
    }

    @Bean
    fun apiAuthenticationFilter(): ApiAuthenticationFilter {
        return ApiAuthenticationFilter(
            AntPathRequestMatcher("/auth/authenticate", HttpMethod.POST.name)
        )
    }
}
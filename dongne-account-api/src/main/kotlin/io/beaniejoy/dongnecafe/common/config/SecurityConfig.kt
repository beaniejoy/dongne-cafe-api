package io.beaniejoy.dongnecafe.common.config

import io.beaniejoy.dongnecafe.common.security.ApiAuthenticationFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Autowired
    lateinit var authenticationConfiguration: AuthenticationConfiguration

    @Autowired
    lateinit var apiAuthenticationSuccessHandler: AuthenticationSuccessHandler

    @Autowired
    lateinit var apiAuthenticationFailureHandler: AuthenticationFailureHandler

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf().disable()
            .formLogin().disable()

            .authorizeRequests()
            .antMatchers("/auth/members/sign-up").permitAll()
            .antMatchers("/test").hasRole("USER")   // 임시 인가 테스트용
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(apiAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)

            .build()
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

    @Bean
    fun apiAuthenticationFilter(): ApiAuthenticationFilter {
        return ApiAuthenticationFilter(
            AntPathRequestMatcher("/auth/authenticate", HttpMethod.POST.name)
        ).apply {
            this.setAuthenticationManager(authenticationConfiguration.authenticationManager)
            this.setAuthenticationSuccessHandler(apiAuthenticationSuccessHandler)
            this.setAuthenticationFailureHandler(apiAuthenticationFailureHandler)
        }
    }
}
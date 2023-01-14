package io.beaniejoy.dongnecafe.security

import io.beaniejoy.dongnecafe.security.filter.JwtAuthenticationFilter
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JwtAuthenticationConfigurer :
    SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
    private var jwtTokenUtils: JwtTokenUtils? = null

    override fun configure(http: HttpSecurity) {
        http
            .addFilterBefore(
                JwtAuthenticationFilter(this.jwtTokenUtils!!),
                UsernamePasswordAuthenticationFilter::class.java
            )
    }

    fun jwtTokenUtils(jwtTokenUtils: JwtTokenUtils): JwtAuthenticationConfigurer {
        this.jwtTokenUtils = jwtTokenUtils
        return this
    }
}
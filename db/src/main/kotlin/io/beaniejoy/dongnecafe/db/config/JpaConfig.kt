package io.beaniejoy.dongnecafe.db.config

import io.beaniejoy.dongnecafe.domain.common.utils.security.getAuthPrincipal
import mu.KLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = ["io.beaniejoy.dongnecafe.db.**.repository"])
class JpaConfig {
    companion object : KLogging() {
        const val SYSTEM = "system"
    }

    @Bean
    fun auditorProvider(): AuditorAware<String> {
        return AuditorAware<String> {
            Optional.of(SecurityContextHolder.getContext().authentication?.getAuthPrincipal() ?: SYSTEM)
        }
    }
}

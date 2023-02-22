package io.beaniejoy.dongnecafe.common.config

import io.beaniejoy.dongnecafe.security.getAuthPrincipal
import mu.KLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*

@Configuration
@EnableJpaAuditing
class AuditingConfig {
    companion object: KLogging() {
        const val SYSTEM = "system"
    }

    @Bean
    fun auditorProvider(): AuditorAware<String> {
        return AuditorAware<String> {
            Optional.of(SecurityContextHolder.getContext().authentication?.getAuthPrincipal() ?: SYSTEM)
        }
    }
}
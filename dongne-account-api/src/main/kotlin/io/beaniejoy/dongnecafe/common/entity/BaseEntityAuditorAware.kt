package io.beaniejoy.dongnecafe.common.entity

import org.springframework.data.domain.AuditorAware
import org.springframework.stereotype.Component
import java.util.*

@Component
class BaseEntityAuditorAware: AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {
        return Optional.of("system")
    }
}
package io.beaniejoy.dongnecafe.common.entity

import org.springframework.data.domain.AuditorAware
import org.springframework.stereotype.Component
import java.util.*

@Component
class BaseEntityAuditorAware: AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {
        // TODO 추후 사용자 로그인 기능 추가되면 실제 등록한 사용자를 DB에 저장하는 방향으로 수정
        return Optional.of("system")
    }
}
package io.beaniejoy.dongnecafe.cafe.service.validator

import io.beaniejoy.dongnecafe.cafe.persistence.CafeReaderPort
import io.beaniejoy.dongnecafe.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.common.error.exception.BusinessException
import org.springframework.stereotype.Component

@Component
class CafeCrudValidator(
    private val cafeReaderPort: CafeReaderPort
) : CafeValidator {
    override fun validateNotExisted(name: String) {
        if (cafeReaderPort.existsCafeByName(name)) {
            throw BusinessException(ErrorCode.CAFE_EXISTED)
        }
    }
}
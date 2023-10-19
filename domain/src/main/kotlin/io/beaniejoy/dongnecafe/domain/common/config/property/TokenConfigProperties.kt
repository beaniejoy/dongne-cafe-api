package io.beaniejoy.dongnecafe.domain.common.config.property

import io.jsonwebtoken.security.Keys
import java.security.Key

/**
 * HS512 알고리즘을 사용할 것이기 때문에 512bit, 즉 64byte 이상의 secret key를 사용해야 한다.
 * 최소 32글자 이상
 * echo '.....' | base64
 */
data class TokenConfigProperties(
    private val secretKey: String,
    private val validityTimeInSec: Long
) {
    private val generatedKey = Keys.hmacShaKeyFor(secretKey.toByteArray())

    companion object {
        const val MILLI_SEC_UNIT = 1_000
    }

    fun getGeneratedKey(): Key {
        return generatedKey
    }

    fun getValidityTimeWithMilliSec(): Long {
        return validityTimeInSec * MILLI_SEC_UNIT
    }

    fun getValidityTimeWithSec(): Long {
        return validityTimeInSec
    }
}

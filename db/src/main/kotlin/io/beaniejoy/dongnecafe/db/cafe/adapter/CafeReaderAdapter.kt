package io.beaniejoy.dongnecafe.db.cafe.adapter

import io.beaniejoy.dongnecafe.db.cafe.repository.CafeRepository
import io.beaniejoy.dongnecafe.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.common.error.exception.BusinessException
import io.beaniejoy.dongnecafe.domain.cafe.entity.Cafe
import io.beaniejoy.dongnecafe.domain.cafe.persistence.CafeReaderPort
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class CafeReaderAdapter(
    private val cafeRepository: CafeRepository
) : CafeReaderPort {
    override fun existsCafeByName(name: String): Boolean {
        return cafeRepository.findByName(name) != null
    }

    override fun getCafesPageByParams(name: String?, pageable: Pageable): Page<Cafe> {
        return cafeRepository.findByNameContainingIgnoreCase(
            name = name,
            pageable = pageable
        )
    }

    override fun getCafe(id: Long): Cafe? {
        return cafeRepository.findByIdOrNull(id)
    }

    override fun getCafeNotNull(id: Long): Cafe {
        return cafeRepository.findByIdOrNull(id)
            ?: throw BusinessException(ErrorCode.CAFE_NOT_FOUND)
    }
}
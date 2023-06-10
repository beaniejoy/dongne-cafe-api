package io.beaniejoy.dongnecafe.cafe.adapter

import io.beaniejoy.dongnecafe.cafe.entity.Cafe
import io.beaniejoy.dongnecafe.cafe.persistence.CafeReaderPort
import io.beaniejoy.dongnecafe.cafe.repository.CafeRepository
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
}
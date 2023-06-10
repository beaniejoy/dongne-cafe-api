package io.beaniejoy.dongnecafe.cafe.persistence

import io.beaniejoy.dongnecafe.cafe.entity.Cafe
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CafeReaderPort {
    fun existsCafeByName(name: String): Boolean

    fun getCafesPageByParams(name: String?, pageable: Pageable): Page<Cafe>

    fun getCafe(id: Long): Cafe?
}
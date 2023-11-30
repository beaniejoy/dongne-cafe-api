package io.beaniejoy.dongnecafe.domain.cafe.persistence

import io.beaniejoy.dongnecafe.domain.cafe.entity.Cafe
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CafeReaderPort {
    fun existsCafeByName(name: String): Boolean

    fun getCafesPageByParams(name: String?, pageable: Pageable): Page<Cafe>

    fun getCafe(id: Long): Cafe?

    fun getCafeNotNull(id: Long): Cafe

    fun getCafeNotNullWithCategoryFetch(name: String): Cafe
}

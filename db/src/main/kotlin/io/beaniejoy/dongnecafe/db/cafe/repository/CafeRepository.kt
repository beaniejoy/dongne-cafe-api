package io.beaniejoy.dongnecafe.db.cafe.repository

import io.beaniejoy.dongnecafe.db.cafe.repository.custom.CafeQueryRepository
import io.beaniejoy.dongnecafe.domain.cafe.entity.Cafe
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface CafeRepository : JpaRepository<Cafe, Long>, CafeQueryRepository {
    fun findByName(name: String): Cafe?

    fun findByNameContainingIgnoreCase(name: String?, pageable: Pageable): Page<Cafe>
}

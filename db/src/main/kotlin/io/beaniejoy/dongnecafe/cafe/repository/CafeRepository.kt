package io.beaniejoy.dongnecafe.cafe.repository

import io.beaniejoy.dongnecafe.cafe.entity.Cafe
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface CafeRepository : JpaRepository<Cafe, Long> {
    fun findByName(name: String): Cafe?

    fun findByNameContainingIgnoreCase(name: String?, pageable: Pageable): Page<Cafe>
}
package io.beaniejoy.dongnecafe.domain.cafe.repository

import io.beaniejoy.dongnecafe.domain.cafe.entity.Cafe
import org.springframework.data.jpa.repository.JpaRepository

interface CafeRepository : JpaRepository<Cafe, Long> {
    fun findByName(name: String): Cafe?
}
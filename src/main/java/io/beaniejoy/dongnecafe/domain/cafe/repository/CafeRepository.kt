package io.beaniejoy.dongnecafe.domain.cafe.repository

import io.beaniejoy.dongnecafe.domain.cafe.domain.Cafe
import org.springframework.data.jpa.repository.JpaRepository

interface CafeRepository : JpaRepository<Cafe, Long> {
}
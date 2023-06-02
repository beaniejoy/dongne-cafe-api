package io.beaniejoy.dongnecafe.domain.cafe.repository

import io.beaniejoy.dongnecafe.domain.cafe.entity.CafeMenuCategory
import org.springframework.data.jpa.repository.JpaRepository

interface CafeMenuCategoryRepository : JpaRepository<CafeMenuCategory, Long> {
}
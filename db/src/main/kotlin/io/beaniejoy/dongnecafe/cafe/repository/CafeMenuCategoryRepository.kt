package io.beaniejoy.dongnecafe.cafe.repository

import io.beaniejoy.dongnecafe.cafe.entity.CafeMenuCategory
import org.springframework.data.jpa.repository.JpaRepository

interface CafeMenuCategoryRepository : JpaRepository<CafeMenuCategory, Long> {
}
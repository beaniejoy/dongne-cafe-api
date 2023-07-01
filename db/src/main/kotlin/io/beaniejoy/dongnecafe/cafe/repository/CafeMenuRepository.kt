package io.beaniejoy.dongnecafe.cafe.repository

import io.beaniejoy.dongnecafe.cafe.entity.CafeMenu
import org.springframework.data.jpa.repository.JpaRepository

interface CafeMenuRepository : JpaRepository<CafeMenu, Long> {
    fun findByNameAndCafeMenuCategory_Id(name: String, menuCategoryId: Long) : CafeMenu?
}
package io.beaniejoy.dongnecafe.db.cafe.repository

import io.beaniejoy.dongnecafe.domain.cafe.entity.CafeMenu
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CafeMenuRepository : JpaRepository<CafeMenu, Long> {
    @Query("select cm from CafeMenu cm where cm.name = :name and cm.cafeMenuCategory.id in (:menuCategoryIds)")
    fun findByNameAndCafeMenuCategoryId(name: String, menuCategoryIds: List<Long>): List<CafeMenu>
}

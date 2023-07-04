package io.beaniejoy.dongnecafe.cafe.repository

import io.beaniejoy.dongnecafe.cafe.entity.CafeMenuCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CafeMenuCategoryRepository : JpaRepository<CafeMenuCategory, Long> {
    @Query("select cmc from CafeMenuCategory cmc where cmc.name = :name and cmc.cafe.id = :cafeId")
    fun findByNameAndCafeId(name: String, cafeId: Long): CafeMenuCategory?

    @Query("select cmc from CafeMenuCategory cmc where cmc.cafe.id = :cafeId")
    fun findAllByCafeId(cafeId: Long): List<CafeMenuCategory>
}
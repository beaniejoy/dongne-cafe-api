package io.beaniejoy.dongnecafe.cafe.entity

import io.beaniejoy.dongnecafe.cafe.model.CafeCommand
import io.beaniejoy.dongnecafe.common.entity.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "cafe_menu_categories")
class CafeMenuCategory(
    name: String,
    description: String
): BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_category_id", nullable = false)
    val id: Long = 0L

    @Column(name = "name", nullable = false)
    var name: String = name
        protected set

    @Column(name = "description", nullable = false)
    var description: String = description
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id", nullable = false)
    var cafe: Cafe? = null
        protected set

    @OneToMany(mappedBy = "cafeMenuCategory", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val cafeMenus: MutableList<CafeMenu> = arrayListOf()

    companion object {
        fun createEntity(
            registerCafeMenuCategory: CafeCommand.RegisterCafeMenuCategory
        ): CafeMenuCategory {
            return CafeMenuCategory(
                name = registerCafeMenuCategory.name,
                description = registerCafeMenuCategory.description
            )
        }
    }

    // 편의 메소드
    fun updateCafe(cafe: Cafe) {
        this.cafe?.run {
            this@run.cafeMenuCategories.remove(this@CafeMenuCategory)
        }

        this.cafe = cafe
        cafe.cafeMenuCategories.add(this)
    }
}
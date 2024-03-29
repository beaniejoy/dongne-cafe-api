package io.beaniejoy.dongnecafe.domain.cafe.entity

import io.beaniejoy.dongnecafe.domain.cafe.entity.image.CafeMenuCategoryImage
import io.beaniejoy.dongnecafe.domain.cafe.model.CafeCommand
import io.beaniejoy.dongnecafe.domain.common.entity.BaseEntity
import io.beaniejoy.dongnecafe.domain.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.domain.common.error.exception.BusinessException
import jakarta.persistence.*

@Entity
@Table(name = "cafe_menu_categories")
class CafeMenuCategory protected constructor(
    name: String,
    description: String?
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_category_id", nullable = false)
    val id: Long = 0L

    @Column(name = "name", nullable = false)
    var name: String = name
        protected set

    @Column(name = "description", length = 255)
    var description: String? = description
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id", nullable = false)
    var cafe: Cafe? = null
        protected set

    @OneToMany(mappedBy = "cafeMenuCategory", fetch = FetchType.LAZY)
    val cafeMenus: MutableList<CafeMenu> = arrayListOf()

    @OneToMany(mappedBy = "cafeMenuCategory", fetch = FetchType.LAZY)
    val cafeMenuCategoryImages: MutableList<CafeMenuCategoryImage> = arrayListOf()

    companion object {
        fun createEntity(command: CafeCommand.RegisterCafeMenuCategory): CafeMenuCategory {
            return CafeMenuCategory(
                name = command.name,
                description = command.description
            )
        }
    }

    // 편의 메소드
    fun updateCafe(cafe: Cafe?) {
        this.cafe?.run {
            this@run.cafeMenuCategories.remove(this@CafeMenuCategory)
        }

        this.cafe = cafe
        cafe?.apply {
            this@apply.cafeMenuCategories.add(this@CafeMenuCategory)
        }
    }

    fun update(updateCommand: CafeCommand.UpdateCafeMenuCategory) {
        this.name = updateCommand.name
        this.description = updateCommand.description
    }

    fun checkTheSameAs(other: CafeMenuCategory?) {
        if (other == null || this !== other) {
            throw BusinessException(ErrorCode.CAFE_MENU_CATEGORY_INVALID_REQUEST)
        }
    }
}

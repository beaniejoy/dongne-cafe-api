package io.beaniejoy.dongnecafe.domain.cafe.entity

import io.beaniejoy.dongnecafe.common.entity.BaseEntity
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "cafe_menus")
class CafeMenu protected constructor(
    name: String,
    price: BigDecimal
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cafe_menu_id", nullable = false)
    val id: Long = 0L

    @Column(name = "name", nullable = false)
    var name: String = name
        protected set

    @Column(name = "price", nullable = false)
    var price: BigDecimal = price
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_category_id", nullable = false)
    var cafeMenuCategory: CafeMenuCategory? = null
        protected set

    @OneToMany(mappedBy = "cafeMenu", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val menuOptions: MutableList<MenuOption> = arrayListOf()

    companion object {
        fun createEntity(
            name: String,
            price: BigDecimal
        ): CafeMenu {
            return CafeMenu(
                name = name,
                price = price
            )
        }
    }

    fun updateCafeMenuCategory(cafeMenuCategory: CafeMenuCategory) {
        this.cafeMenuCategory?.run {
            this@run.cafeMenus.remove(this@CafeMenu)
        }

        this.cafeMenuCategory = cafeMenuCategory
        cafeMenuCategory.cafeMenus.add(this)
    }

    fun updateInfo(
        name: String,
        price: BigDecimal
    ) {
        this.name = name
        this.price = price
    }
}
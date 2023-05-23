package io.beaniejoy.dongnecafe.domain.cafe.entity

import io.beaniejoy.dongnecafe.common.entity.BaseEntity
import io.beaniejoy.dongnecafe.domain.cafe.model.request.MenuOptionRegisterRequest
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "cafe_menu")
class CafeMenu protected constructor(
    name: String,
    price: BigDecimal,
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
    @JoinColumn(name = "cafe_id", nullable = false)
    var cafe: Cafe? = null
        protected set

    @OneToMany(mappedBy = "cafeMenu", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val menuOptions: MutableList<MenuOption> = arrayListOf()

    companion object {
        fun createCafeMenu(
            name: String,
            price: BigDecimal,
            menuOptionRequests: List<MenuOptionRegisterRequest>
        ): CafeMenu {
            val menuOptionEntities = menuOptionRequests.map { menuOptionRequestDto ->
                MenuOption.createMenuOption(
                    title = menuOptionRequestDto.title,
                    optionDetailRequests = menuOptionRequestDto.optionDetails
                )
            }

            return CafeMenu(
                name = name,
                price = price
            ).apply {
                menuOptionEntities.forEach { this.addMenuOption(it) }
            }
        }
    }

    fun updateCafe(cafe: Cafe) {
        this.cafe = cafe
    }

    fun addMenuOption(menuOption: MenuOption) {
        this.menuOptions.add(menuOption)
        menuOption.updateCafeMenu(this)
    }

    fun updateInfo(
        name: String,
        price: BigDecimal
    ) {
        this.name = name
        this.price = price
    }
}
package io.beaniejoy.dongnecafe.domain.cafe.entity

import io.beaniejoy.dongnecafe.common.BaseTimeEntity
import io.beaniejoy.dongnecafe.domain.cafe.model.request.MenuOptionRegisterRequest
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "cafe_menu")
class CafeMenu protected constructor(
    name: String,
    price: BigDecimal,
) : BaseTimeEntity() {
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
    val menuOptionList: MutableList<MenuOption> = arrayListOf()

    companion object {
        fun createCafeMenu(name: String, price: BigDecimal, menuOptionRequestList: List<MenuOptionRegisterRequest>): CafeMenu {
            val menuOptionEntityList = menuOptionRequestList.map { menuOptionRequestDto ->
                MenuOption.createMenuOption(
                    title = menuOptionRequestDto.title,
                    optionDetailRequestList = menuOptionRequestDto.optionDetailList
                )
            }

            return CafeMenu(
                name = name,
                price = price
            ).apply {
                menuOptionEntityList.forEach { this.addMenuOption(it) }
            }
        }
    }

    fun updateCafe(cafe: Cafe) {
        this.cafe = cafe
    }

    fun addMenuOption(menuOption: MenuOption) {
        this.menuOptionList.add(menuOption)
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
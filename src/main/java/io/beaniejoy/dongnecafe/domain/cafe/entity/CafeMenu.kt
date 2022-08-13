package io.beaniejoy.dongnecafe.domain.cafe.entity

import io.beaniejoy.dongnecafe.common.entity.BaseTimeEntity
import io.beaniejoy.dongnecafe.domain.cafe.dto.request.MenuOptionInfoRequestDto
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
    val name: String = name

    @Column(name = "price", nullable = false)
    val price: BigDecimal = price

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id", nullable = false)
    var cafe: Cafe? = null
        protected set

    @OneToMany(mappedBy = "cafeMenu", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val menuOptionList: MutableList<MenuOption> = arrayListOf()

    companion object {
        fun createCafeMenu(name: String, price: BigDecimal, menuOptionRequestList: List<MenuOptionInfoRequestDto>): CafeMenu {
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
}
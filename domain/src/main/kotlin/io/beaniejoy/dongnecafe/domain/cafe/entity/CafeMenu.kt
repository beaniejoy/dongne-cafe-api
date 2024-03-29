package io.beaniejoy.dongnecafe.domain.cafe.entity

import io.beaniejoy.dongnecafe.domain.cafe.entity.image.CafeMenuImage
import io.beaniejoy.dongnecafe.domain.cafe.model.CafeCommand
import io.beaniejoy.dongnecafe.domain.common.entity.BaseEntity
import io.beaniejoy.dongnecafe.domain.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.domain.common.error.exception.BusinessException
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "cafe_menus")
class CafeMenu protected constructor(
    name: String,
    price: BigDecimal,
    description: String?
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

    @Column(name = "description", length = 255)
    var description: String? = description
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_category_id", nullable = false)
    var cafeMenuCategory: CafeMenuCategory? = null
        protected set

    @OneToMany(mappedBy = "cafeMenu", fetch = FetchType.LAZY)
    val menuOptions: MutableList<MenuOption> = arrayListOf()

    @OneToMany(mappedBy = "cafeMenu", fetch = FetchType.LAZY)
    val cafeMenuImages: MutableList<CafeMenuImage> = arrayListOf()

    companion object {
        fun createEntity(command: CafeCommand.RegisterCafeMenu): CafeMenu {
            return CafeMenu(
                name = command.name,
                price = command.price,
                description = command.description
            )
        }
    }

    fun updateCafeMenuCategory(cafeMenuCategory: CafeMenuCategory?) {
        this.cafeMenuCategory?.run {
            this@run.cafeMenus.remove(this@CafeMenu)
        }

        this.cafeMenuCategory = cafeMenuCategory
        cafeMenuCategory?.apply {
            this@apply.cafeMenus.add(this@CafeMenu)
        }
    }

    fun updateWithSeries(command: CafeCommand.UpdateCafeMenu) {
        this.name = command.name
        this.price = command.price
        this.description = command.description

        this.updateSeries(command.menuOptions)
    }

    private fun updateSeries(commands: List<CafeCommand.UpdateMenuOption>) {
        commands
            .filter { it.delete.not() }
            .forEach { command ->
                val menuOption = this.menuOptions.find { it.id == command.menuOptionId }
                menuOption?.updateWithSeries(command)
            }
    }

    fun updateInfo(
        name: String?,
        price: BigDecimal?
    ) {
        require(name != null && price != null) {
            throw BusinessException(ErrorCode.CAFE_MENU_INVALID_REQUEST)
        }

        this.name = name
        this.price = price
    }
}

package io.beaniejoy.dongnecafe.domain.cafe.entity

import io.beaniejoy.dongnecafe.common.entity.BaseEntity
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "option_details")
class OptionDetail protected constructor(
    name: String,
    extraPrice: BigDecimal
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_detail_id", nullable = false)
    val id: Long = 0L

    @Column(name = "name", nullable = false)
    var name: String = name
        protected set

    @Column(name = "extra_price", nullable = false)
    var extraPrice: BigDecimal = extraPrice
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_option_id", nullable = false)
    var menuOption: MenuOption? = null
        protected set

    companion object {
        fun createEntity(name: String, extraPrice: BigDecimal): OptionDetail {
            return OptionDetail(
                name = name,
                extraPrice = extraPrice
            )
        }
    }

    fun updateMenuOption(menuOption: MenuOption) {
        this.menuOption?.run {
            this@run.optionDetails.remove(this@OptionDetail)
        }

        this.menuOption = menuOption
        menuOption.optionDetails.add(this)
    }

    fun updateInfo(name: String, extraPrice: BigDecimal) {
        this.name = name
        this.extraPrice = extraPrice
    }
}
package io.beaniejoy.dongnecafe.domain.cafe.entity

import io.beaniejoy.dongnecafe.common.entity.BaseTimeEntity
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "option_detail")
class OptionDetail protected constructor(
    name: String,
    extraPrice: BigDecimal
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_detail_id", nullable = false)
    val id: Long = 0L

    @Column(name = "name", nullable = false)
    val name: String = name

    @Column(name = "extra_price", nullable = false)
    val extraPrice: BigDecimal = extraPrice

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_option_id", nullable = false)
    var menuOption: MenuOption? = null
        protected set

    companion object {
        fun createOptionDetail(name: String, extraPrice: BigDecimal): OptionDetail {
            return OptionDetail(
                name = name,
                extraPrice = extraPrice
            )
        }
    }

    fun updateMenuOption(menuOption: MenuOption) {
        this.menuOption = menuOption
    }
}
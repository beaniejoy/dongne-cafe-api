package io.beaniejoy.dongnecafe.domain.cafe.entity

import io.beaniejoy.dongnecafe.domain.cafe.model.CafeCommand
import io.beaniejoy.dongnecafe.domain.common.entity.BaseEntity
import io.beaniejoy.dongnecafe.domain.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.domain.common.error.exception.BusinessException
import jakarta.persistence.*
import java.math.BigDecimal

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
        fun createEntity(command: CafeCommand.RegisterOptionDetail): OptionDetail {
            return OptionDetail(
                name = command.name,
                extraPrice = command.extraPrice
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

    fun update(command: CafeCommand.UpdateOptionDetail) {
        if (command.delete) {
            throw BusinessException(ErrorCode.OPTION_DETAIL_INVALID_REQUEST)
        }

        this.name = command.name
        this.extraPrice = command.extraPrice
    }
}

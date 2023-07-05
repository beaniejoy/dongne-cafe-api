package io.beaniejoy.dongnecafe.cafe.entity

import io.beaniejoy.dongnecafe.cafe.model.CafeCommand
import io.beaniejoy.dongnecafe.common.entity.BaseEntity
import io.beaniejoy.dongnecafe.common.error.constant.ErrorCode
import io.beaniejoy.dongnecafe.common.error.exception.BusinessException
import javax.persistence.*

@Entity
@Table(name = "menu_options")
class MenuOption protected constructor(
    title: String
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_option_id", nullable = false)
    val id: Long = 0L

    @Column(name = "title", nullable = false)
    var title: String = title
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_menu_id", nullable = false)
    var cafeMenu: CafeMenu? = null
        protected set

    @OneToMany(mappedBy = "menuOption", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val optionDetails: MutableList<OptionDetail> = arrayListOf()

    companion object {
        fun createEntity(command: CafeCommand.RegisterMenuOption): MenuOption {
            return MenuOption(
                title = command.title
            )
        }
    }

    fun updateCafeMenu(cafeMenu: CafeMenu) {
        this.cafeMenu?.run {
            this@run.menuOptions.remove(this@MenuOption)
        }

        this.cafeMenu = cafeMenu
        cafeMenu.menuOptions.add(this)
    }

    fun updateInfo(title: String) {
        this.title = title
    }

    fun updateWithSeries(command: CafeCommand.UpdateMenuOption) {
        if (command.delete) {
            throw BusinessException(ErrorCode.MENU_OPTION_INVALID_REQUEST)
        }

        this.title = command.title

        this.updateSeries(command.optionDetails)
    }

    private fun updateSeries(commands: List<CafeCommand.UpdateOptionDetail>) {
        commands
            .filter { it.delete.not() }
            .forEach { command ->
                val optionDetail = this.optionDetails.find { it.id == command.optionDetailId }
                optionDetail?.update(command)
            }
    }
}
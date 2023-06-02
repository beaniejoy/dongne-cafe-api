package io.beaniejoy.dongnecafe.domain.cafe.entity

import io.beaniejoy.dongnecafe.common.entity.BaseEntity
import io.beaniejoy.dongnecafe.domain.cafe.model.request.OptionDetailRegisterRequest
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
        fun createEntity(title: String): MenuOption {
            return MenuOption(
                title = title
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
}
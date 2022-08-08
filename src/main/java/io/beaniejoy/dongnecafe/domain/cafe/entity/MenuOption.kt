package io.beaniejoy.dongnecafe.domain.cafe.entity

import io.beaniejoy.dongnecafe.common.entity.BaseTimeEntity
import io.beaniejoy.dongnecafe.domain.cafe.dto.request.OptionDetailInfoRequestDto
import javax.persistence.*

@Entity
@Table(name = "menu_option")
class MenuOption protected constructor(
    title: String
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(name = "title", nullable = false)
    val title: String = title

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    var cafeMenu: CafeMenu? = null
        protected set

    @OneToMany(mappedBy = "menuOption", fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    val optionDetailList: MutableList<OptionDetail> = arrayListOf()

    companion object {
        fun createMenuOption(title: String, optionDetailRequestList: List<OptionDetailInfoRequestDto>): MenuOption {
            val optionDetailEntityList = optionDetailRequestList.map { optionDetailRequestDto ->
                OptionDetail.createOptionDetail(
                    name = optionDetailRequestDto.name,
                    extraPrice = optionDetailRequestDto.extraPrice
                )
            }

            return MenuOption(
                title = title
            ).apply {
                optionDetailEntityList.forEach { this.addOptionDetail(it) }
            }
        }
    }

    fun updateCafeMenu(cafeMenu: CafeMenu) {
        this.cafeMenu = cafeMenu
    }

    fun addOptionDetail(optionDetail: OptionDetail) {
        this.optionDetailList.add(optionDetail)
        optionDetail.updateMenuOption(this)
    }
}
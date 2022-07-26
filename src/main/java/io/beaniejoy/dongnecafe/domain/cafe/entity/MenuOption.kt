package io.beaniejoy.dongnecafe.domain.cafe.entity

import io.beaniejoy.dongnecafe.common.entity.BaseTimeEntity
import javax.persistence.*

@Entity
@Table(name = "menu_option")
class MenuOption(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "title", nullable = false)
    val title: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    val cafeMenu: CafeMenu,

    @OneToMany(mappedBy = "menuOption", fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    val optionDetailList: MutableList<OptionDetail>
) : BaseTimeEntity()
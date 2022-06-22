package io.beaniejoy.dongnecafe.domain.cafe.domain

import io.beaniejoy.dongnecafe.common.domain.BaseTimeEntity
import javax.persistence.*

@Entity
@Table(name = "menu_option")
class MenuOption(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "title", nullable = false)
    val title: String,

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    val cafeMenu: CafeMenu,

    @OneToMany(mappedBy = "menuOption", fetch = FetchType.EAGER)
    val optionDetailList: MutableList<OptionDetail>
) : BaseTimeEntity()
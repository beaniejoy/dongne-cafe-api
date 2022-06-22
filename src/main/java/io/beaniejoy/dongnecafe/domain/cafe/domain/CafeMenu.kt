package io.beaniejoy.dongnecafe.domain.cafe.domain

import io.beaniejoy.dongnecafe.common.domain.BaseTimeEntity
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "cafe_menu")
class CafeMenu(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "price", nullable = false)
    val price: BigDecimal = BigDecimal.ZERO,

    @ManyToOne
    @JoinColumn(name = "cafe_id", nullable = false)
    val cafe: Cafe,

    @OneToMany(mappedBy = "cafeMenu", fetch = FetchType.LAZY)
    val menuOptionList: MutableList<MenuOption>
) : BaseTimeEntity()
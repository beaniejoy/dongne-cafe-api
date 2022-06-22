package io.beaniejoy.dongnecafe.domain.cafe.entity

import io.beaniejoy.dongnecafe.common.domain.BaseTimeEntity
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "option_detail")
class OptionDetail(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "extra", nullable = false)
    val extra: BigDecimal,

    @ManyToOne
    @JoinColumn(name = "option_id", nullable = false)
    val menuOption: MenuOption
): BaseTimeEntity() {
}
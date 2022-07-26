package io.beaniejoy.dongnecafe.domain.cafe.entity

import io.beaniejoy.dongnecafe.common.entity.BaseTimeEntity
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "cafe_menu")
class CafeMenu(
    name: String,
    price: BigDecimal,
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(name = "name", nullable = false)
    val name: String = name

    @Column(name = "price", nullable = false)
    val price: BigDecimal = price

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id", nullable = false)
    var cafe: Cafe? = null
        protected set

    @OneToMany(mappedBy = "cafeMenu", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val menuOptionList: MutableList<MenuOption> = arrayListOf()

    companion object {
        fun createCafeMenu(name: String, price: BigDecimal): CafeMenu {
            return CafeMenu(
                name = name,
                price = price
            )
        }
    }

    fun updateCafe(cafe: Cafe) {
        this.cafe = cafe
    }
}
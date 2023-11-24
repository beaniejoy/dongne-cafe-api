package io.beaniejoy.dongnecafe.domain.cafe.entity.image

import io.beaniejoy.dongnecafe.domain.cafe.entity.CafeMenu
import jakarta.persistence.*

@Entity
@Table(name = "cafe_menu_images")
class CafeMenuImage protected constructor(imgUrl: String) : Image(imgUrl) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cafe_menu_image_id", nullable = false)
    val id: Long = 0L

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_menu_id", nullable = false)
    var cafeMenu: CafeMenu? = null
        protected set
}

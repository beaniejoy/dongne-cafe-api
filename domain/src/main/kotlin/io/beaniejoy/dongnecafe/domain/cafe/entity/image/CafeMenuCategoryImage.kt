package io.beaniejoy.dongnecafe.domain.cafe.entity.image

import io.beaniejoy.dongnecafe.domain.cafe.entity.CafeMenuCategory
import javax.persistence.*

@Entity
@Table(name = "cafe_menu_category_images")
class CafeMenuCategoryImage protected constructor(imgUrl: String) : Image(imgUrl) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_category_image_id", nullable = false)
    val id: Long = 0L

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_category_id", nullable = false)
    var cafeMenuCategory: CafeMenuCategory? = null
        protected set
}

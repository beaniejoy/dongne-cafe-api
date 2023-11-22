package io.beaniejoy.dongnecafe.domain.cafe.entity.image

import javax.persistence.*

@Entity
@Table(name = "cafe_menu_category_images")
class CafeMenuCategoryImage protected constructor(imgUrl: String) : Image(imgUrl) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_category_image_id", nullable = false)
    val id: Long = 0L
}

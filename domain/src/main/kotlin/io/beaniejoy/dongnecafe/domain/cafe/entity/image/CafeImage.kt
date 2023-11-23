package io.beaniejoy.dongnecafe.domain.cafe.entity.image

import io.beaniejoy.dongnecafe.domain.cafe.entity.Cafe
import jakarta.persistence.*

@Entity
@Table(name = "cafe_images")
class CafeImage protected constructor(imgUrl: String) : Image(imgUrl) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cafe_image_id", nullable = false)
    val id: Long = 0L

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id", nullable = false)
    var cafe: Cafe? = null
        protected set
}

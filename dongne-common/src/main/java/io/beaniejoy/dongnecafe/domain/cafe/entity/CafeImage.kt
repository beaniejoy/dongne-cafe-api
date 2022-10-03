package io.beaniejoy.dongnecafe.domain.cafe.entity

import io.beaniejoy.dongnecafe.common.BaseTimeEntity
import javax.persistence.*

@Entity
@Table(name = "cafe_image")
class CafeImage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cafe_image_id", nullable = false)
    val id: Long,

    @Column(name = "img_url", nullable = false)
    val imgUrl: String,

    @ManyToOne
    @JoinColumn(name = "cafe_id", nullable = false)
    val cafe: Cafe
) : BaseTimeEntity()
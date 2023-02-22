package io.beaniejoy.dongnecafe.domain.cafe.entity

import io.beaniejoy.dongnecafe.common.entity.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "cafe_image")
class CafeImage protected constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cafe_image_id", nullable = false)
    val id: Long,

    @Column(name = "img_url", nullable = false)
    val imgUrl: String,

    @ManyToOne
    @JoinColumn(name = "cafe_id", nullable = false)
    val cafe: Cafe
) : BaseEntity()
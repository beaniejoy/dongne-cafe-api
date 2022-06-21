package io.beaniejoy.dongnecafe.domain.cafe.domain

import io.beaniejoy.dongnecafe.common.domain.BaseTimeEntity
import javax.persistence.*

@Entity
class CafeImage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "img_url")
    val imgUrl: String,

    @ManyToOne
    @JoinColumn(name = "cafe_id")
    val cafe: Cafe,
) : BaseTimeEntity()
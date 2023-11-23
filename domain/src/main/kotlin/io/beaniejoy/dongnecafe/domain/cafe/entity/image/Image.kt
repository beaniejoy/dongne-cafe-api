package io.beaniejoy.dongnecafe.domain.cafe.entity.image

import io.beaniejoy.dongnecafe.domain.common.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass

@MappedSuperclass
abstract class Image protected constructor(imgUrl: String) : BaseEntity() {
    @Column(name = "img_url", nullable = false)
    var imgUrl: String = imgUrl
        protected set
}

package io.beaniejoy.dongnecafe.common.entity

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTimeEntity protected constructor() {
    @CreatedDate
    var createdAt: LocalDateTime = LocalDateTime.now()
        protected set

    @CreatedBy
    var createdBy: String = ""
        protected set

    @LastModifiedDate
    var updatedAt: LocalDateTime? = null
        protected set

    @LastModifiedBy
    var updatedBy: String? = null
        protected set
}
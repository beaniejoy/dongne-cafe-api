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
class BaseTimeEntity(
    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @CreatedBy
    val createdBy: String = "",

    @LastModifiedDate
    val updatedAt: LocalDateTime? = null,

    @LastModifiedBy
    val updatedBy: String? = null
)
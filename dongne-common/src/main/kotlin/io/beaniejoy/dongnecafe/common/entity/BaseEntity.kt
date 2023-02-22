package io.beaniejoy.dongnecafe.common.entity

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity protected constructor() : BaseTimeEntity() {
    @CreatedBy
    @Column(updatable = false)
    lateinit var createdBy: String
        protected set

    @LastModifiedBy
    lateinit var updatedBy: String
        protected set
}
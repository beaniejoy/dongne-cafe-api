package io.beaniejoy.dongnecafe.db.cafe.repository

import io.beaniejoy.dongnecafe.domain.cafe.entity.OptionDetail
import org.springframework.data.jpa.repository.JpaRepository

interface OptionDetailRepository : JpaRepository<OptionDetail, Long>

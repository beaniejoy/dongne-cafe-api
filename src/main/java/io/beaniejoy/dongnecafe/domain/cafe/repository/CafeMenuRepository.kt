package io.beaniejoy.dongnecafe.domain.cafe.repository

import io.beaniejoy.dongnecafe.domain.cafe.entity.CafeMenu
import org.springframework.data.jpa.repository.JpaRepository

interface CafeMenuRepository : JpaRepository<CafeMenu, Long>
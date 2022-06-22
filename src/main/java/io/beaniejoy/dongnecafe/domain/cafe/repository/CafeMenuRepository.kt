package io.beaniejoy.dongnecafe.domain.cafe.repository

import io.beaniejoy.dongnecafe.domain.cafe.domain.CafeMenu
import org.springframework.data.jpa.repository.JpaRepository

interface CafeMenuRepository : JpaRepository<CafeMenu, Long>
package io.beaniejoy.dongnecafe.db.cafe.repository

import io.beaniejoy.dongnecafe.domain.cafe.entity.MenuOption
import org.springframework.data.jpa.repository.JpaRepository

interface MenuOptionRepository : JpaRepository<MenuOption, Long>
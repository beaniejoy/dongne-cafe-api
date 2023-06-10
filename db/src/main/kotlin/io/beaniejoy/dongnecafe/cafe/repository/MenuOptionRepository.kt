package io.beaniejoy.dongnecafe.cafe.repository

import io.beaniejoy.dongnecafe.cafe.entity.MenuOption
import org.springframework.data.jpa.repository.JpaRepository

interface MenuOptionRepository : JpaRepository<MenuOption, Long>
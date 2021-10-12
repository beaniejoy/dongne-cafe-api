package io.beaniejoy.dongnecafe.cafe.repository;

import io.beaniejoy.dongnecafe.cafe.domain.CafeMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CafeMenuRepository extends JpaRepository<CafeMenu, UUID> {
}

package io.beaniejoy.dongnecafe.domain.cafe.repository;

import io.beaniejoy.dongnecafe.domain.cafe.domain.Cafe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CafeRepository extends JpaRepository<Cafe, UUID> {

    Page<Cafe> findAll(Pageable pageable);
}

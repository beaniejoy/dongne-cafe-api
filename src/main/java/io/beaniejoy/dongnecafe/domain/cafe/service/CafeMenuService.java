package io.beaniejoy.dongnecafe.domain.cafe.service;

import io.beaniejoy.dongnecafe.domain.cafe.domain.CafeMenu;
import io.beaniejoy.dongnecafe.domain.cafe.dto.menu.CafeMenuDetailResponseDto;
import io.beaniejoy.dongnecafe.domain.cafe.error.CafeMenuNotFoundException;
import io.beaniejoy.dongnecafe.domain.cafe.repository.CafeMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CafeMenuService {

    private final CafeMenuRepository cafeMenuRepository;

    @Transactional(readOnly = true)
    public CafeMenuDetailResponseDto getCafeMenuInfoByCafeIdAndMenuId(UUID menuId, UUID cafeId) {

        CafeMenu cafeMenu = cafeMenuRepository.findById(menuId)
                .orElseThrow(() -> new CafeMenuNotFoundException(menuId, cafeId));

        return cafeMenu.toDetailResponseDto();
    }
}

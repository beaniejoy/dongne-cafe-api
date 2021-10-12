package io.beaniejoy.dongnecafe.cafe.controller;

import io.beaniejoy.dongnecafe.cafe.dto.menu.CafeMenuDetailResponseDto;
import io.beaniejoy.dongnecafe.cafe.service.CafeMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CafeMenuController {

    private final CafeMenuService cafeMenuService;

    @GetMapping("/cafes/{cafeId}/menus/{menuId}")
    public ResponseEntity<CafeMenuDetailResponseDto> getCafeMenuDetailedInfo(
            @PathVariable("cafeId") UUID cafeId,
            @PathVariable("menuId") UUID menuId
    ) {

        CafeMenuDetailResponseDto menuDetail = cafeMenuService.getCafeMenuInfoByCafeIdAndMenuId(menuId, cafeId);
        return ResponseEntity.ok(menuDetail);
    }
}

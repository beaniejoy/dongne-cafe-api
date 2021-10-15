package io.beaniejoy.dongnecafe.cafe.dto.cafe;

import io.beaniejoy.dongnecafe.cafe.dto.menu.CafeMenuListResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CafeInfoResponseDto {
    private UUID cafeId;

    private String name;

    private String address;

    private String phoneNumber;

    private Double totalRate;

    private String description;

    private List<CafeMenuListResponseDto> menuList;

    private List<CafeImageResponseDto> imageList;
}

package io.beaniejoy.dongnecafe.cafe.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CafeMenuListResponseDto {

    private UUID menuId;

    private String name;

    private Integer price;
}

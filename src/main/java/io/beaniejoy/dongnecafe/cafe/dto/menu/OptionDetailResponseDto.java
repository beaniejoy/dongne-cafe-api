package io.beaniejoy.dongnecafe.cafe.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionDetailResponseDto {

    private Long optionDetailId;

    private String name;

    private Integer extra;
}

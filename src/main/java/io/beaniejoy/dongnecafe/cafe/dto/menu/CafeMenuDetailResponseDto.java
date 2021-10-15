package io.beaniejoy.dongnecafe.cafe.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CafeMenuDetailResponseDto {

    private String name;

    private Integer price;

    private List<MenuOptionResponseDto> optionList;
}

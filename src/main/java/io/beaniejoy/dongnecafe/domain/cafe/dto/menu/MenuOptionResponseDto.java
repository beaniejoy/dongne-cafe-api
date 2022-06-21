package io.beaniejoy.dongnecafe.domain.cafe.dto.menu;

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
public class MenuOptionResponseDto {

    private UUID optionId;

    private String title;

    private List<OptionDetailResponseDto> optionDetailList;
}

package io.beaniejoy.dongnecafe.domain.cafe.dto.cafe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CafeImageResponseDto {

    private String imgUrl;
}

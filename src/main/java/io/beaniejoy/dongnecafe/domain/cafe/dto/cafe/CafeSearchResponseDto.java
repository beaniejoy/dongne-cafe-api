package io.beaniejoy.dongnecafe.domain.cafe.dto.cafe;

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
public class CafeSearchResponseDto {

    private UUID cafeId;

    private String name;

    private String address;

    private Double totalRate;

    private List<CafeImageResponseDto> imageList;
}

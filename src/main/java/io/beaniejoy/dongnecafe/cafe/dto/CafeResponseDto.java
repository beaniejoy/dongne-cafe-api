package io.beaniejoy.dongnecafe.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CafeResponseDto {

    private UUID cafeId;

    private String name;

    private String address;

    private String phoneNumber;

    private Double totalRate;

    private String description;
}

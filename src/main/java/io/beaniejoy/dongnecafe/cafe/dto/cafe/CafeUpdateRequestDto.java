package io.beaniejoy.dongnecafe.cafe.dto.cafe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CafeUpdateRequestDto {

    private String name;

    private String address;

    private String phoneNumber;

    private String description;
}

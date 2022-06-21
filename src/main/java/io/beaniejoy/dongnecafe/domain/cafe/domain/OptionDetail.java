package io.beaniejoy.dongnecafe.domain.cafe.domain;

import io.beaniejoy.dongnecafe.domain.cafe.dto.menu.OptionDetailResponseDto;
import io.beaniejoy.dongnecafe.common.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OptionDetail extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionDetailId;

    private String name;

    private Integer extra;

    @ManyToOne
    @JoinColumn(name = "option_id")
    private MenuOption menuOption;

    public OptionDetailResponseDto toResponseDto() {
        return OptionDetailResponseDto.builder()
                .optionDetailId(optionDetailId)
                .name(name)
                .extra(extra)
                .build();
    }
}

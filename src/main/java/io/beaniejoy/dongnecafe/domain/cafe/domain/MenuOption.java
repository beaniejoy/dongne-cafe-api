package io.beaniejoy.dongnecafe.domain.cafe.domain;

import io.beaniejoy.dongnecafe.domain.cafe.dto.menu.MenuOptionResponseDto;
import io.beaniejoy.dongnecafe.common.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MenuOption extends BaseTimeEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "option_id", columnDefinition = "BINARY(16)")
    private UUID optionId;

    private String title;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private CafeMenu cafeMenu;

    @OneToMany(mappedBy = "menuOption", fetch = FetchType.EAGER)
    private List<OptionDetail> optionDetailList;

    public MenuOptionResponseDto toResponseDto() {
        return MenuOptionResponseDto.builder()
                .optionId(optionId)
                .title(title)
                .optionDetailList(optionDetailList
                        .stream()
                        .map(OptionDetail::toResponseDto)
                        .collect(Collectors.toList()))
                .build();
    }
}

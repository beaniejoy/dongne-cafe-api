package io.beaniejoy.dongnecafe.domain.cafe.domain;

import io.beaniejoy.dongnecafe.domain.cafe.dto.menu.CafeMenuDetailResponseDto;
import io.beaniejoy.dongnecafe.domain.cafe.dto.menu.CafeMenuListResponseDto;
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
public class CafeMenu extends BaseTimeEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "menu_id", columnDefinition = "BINARY(16)")
    private UUID menuId;

    private String name;

    private Integer price;

    @ManyToOne
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

    @OneToMany(mappedBy = "cafeMenu", fetch = FetchType.LAZY)
    private List<MenuOption> menuOptionList;

    public CafeMenuListResponseDto toListResponseDto() {
        return CafeMenuListResponseDto.builder()
                .menuId(menuId)
                .name(name)
                .price(price)
                .build();
    }

    public CafeMenuDetailResponseDto toDetailResponseDto() {
        return CafeMenuDetailResponseDto.builder()
                .name(name)
                .price(price)
                .optionList(menuOptionList
                        .stream()
                        .map(MenuOption::toResponseDto)
                        .collect(Collectors.toList()))
                .build();
    }
}

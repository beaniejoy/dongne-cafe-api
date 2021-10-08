package io.beaniejoy.dongnecafe.cafe.domain;

import io.beaniejoy.dongnecafe.cafe.dto.CafeResponseDto;
import io.beaniejoy.dongnecafe.common.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cafe extends BaseTimeEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "cafe_id", columnDefinition = "BINARY(16)")
    private UUID cafeId;

    private String name;

    private String address;

    private String phoneNumber;

    private Double totalRate;

    private String description;

    @OneToMany(mappedBy = "cafe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CafeMenu> cafeMenuList;

    @OneToMany(mappedBy = "cafe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CafeImage> cafeImageList;

    public CafeResponseDto toResponseDto() {
        return CafeResponseDto.builder()
                .cafeId(cafeId)
                .name(name)
                .address(address)
                .phoneNumber(phoneNumber)
                .totalRate(totalRate)
                .description(description)
                .build();
    }
}

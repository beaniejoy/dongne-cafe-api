package io.beaniejoy.dongnecafe.domain.cafe.domain;

import io.beaniejoy.dongnecafe.domain.cafe.dto.cafe.CafeInfoResponseDto;
import io.beaniejoy.dongnecafe.domain.cafe.dto.cafe.CafeSearchResponseDto;
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

    @OneToMany(mappedBy = "cafe", fetch = FetchType.LAZY)
    private List<CafeMenu> cafeMenuList;

    @OneToMany(mappedBy = "cafe", fetch = FetchType.LAZY)
    private List<CafeImage> cafeImageList;

    public void updateInfo(String name, String address, String phoneNumber, String description) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.description = description;
    }

    public CafeSearchResponseDto toSearchResponseDto() {
        return CafeSearchResponseDto.builder()
                .cafeId(cafeId)
                .name(name)
                .address(address)
                .totalRate(totalRate)
                .imageList(cafeImageList
                        .stream()
                        .map(CafeImage::toResponseDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public CafeInfoResponseDto toInfoResponseDto() {
        return CafeInfoResponseDto.builder()
                .cafeId(cafeId)
                .name(name)
                .address(address)
                .phoneNumber(phoneNumber)
                .totalRate(totalRate)
                .description(description)
                .menuList(cafeMenuList
                        .stream()
                        .map(CafeMenu::toListResponseDto)
                        .collect(Collectors.toList()))
                .imageList(cafeImageList
                        .stream()
                        .map(CafeImage::toResponseDto)
                        .collect(Collectors.toList()))
                .build();
    }
}

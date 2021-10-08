package io.beaniejoy.dongnecafe.cafe.domain;

import io.beaniejoy.dongnecafe.common.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CafeImage extends BaseTimeEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "cafe_img_id", columnDefinition = "BINARY(16)")
    private UUID cafeImgId;

    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;
}

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
}

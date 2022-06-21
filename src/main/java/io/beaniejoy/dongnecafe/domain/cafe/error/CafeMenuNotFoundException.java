package io.beaniejoy.dongnecafe.domain.cafe.error;

import java.util.UUID;

public class CafeMenuNotFoundException extends RuntimeException{
    public CafeMenuNotFoundException(UUID menuId, UUID cafeId) {
        super("Cafe[" + cafeId + "]의 Menu[" + menuId + "]는 존재하지 않는 메뉴입니다.");
    }
}

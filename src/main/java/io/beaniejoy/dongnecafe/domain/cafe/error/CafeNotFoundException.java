package io.beaniejoy.dongnecafe.domain.cafe.error;

import java.util.UUID;

public class CafeNotFoundException extends RuntimeException {
    public CafeNotFoundException(UUID cafeId) {
        super("Cafe[" + cafeId + "] is not found");
    }
}

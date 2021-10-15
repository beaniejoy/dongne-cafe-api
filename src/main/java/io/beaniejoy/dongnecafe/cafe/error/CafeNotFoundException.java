package io.beaniejoy.dongnecafe.cafe.error;

import java.util.UUID;

public class CafeNotFoundException extends RuntimeException {
    public CafeNotFoundException(UUID cafeId) {
        super("Cafe[" + cafeId + "] is not found");
    }
}

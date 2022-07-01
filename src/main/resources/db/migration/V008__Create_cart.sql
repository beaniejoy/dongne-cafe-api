CREATE TABLE `cart` (
    `cart_id` binary(16) NOT NULL COMMENT '장바구니 식별번호',
    `cafe_name` varchar(20) NOT NULL COMMENT '장바구니에 담긴 카페 이름',
    `check_ordered` tinyint NOT NULL COMMENT '장바구니의 주문 여부',
    `total_price` int NOT NULL COMMENT '장바구니에 담긴 총 금액',
    `created_date` datetime NOT NULL COMMENT '장바구니 등록날짜',
    `updated_date` datetime COMMENT '장바구니 수정날짜',
    `cafe_id` binary(16) NOT NULL COMMENT '장바구니에 담긴 카페 식별번호',
    `user_id` binary(16) NOT NULL COMMENT '장바구니 주인 사용자 식별번호',
    PRIMARY KEY (`cart_id`),
    KEY `user_id` (`user_id`),
    FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
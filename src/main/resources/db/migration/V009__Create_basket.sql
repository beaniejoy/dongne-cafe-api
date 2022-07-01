CREATE TABLE `basket` (
    `basket_id` binary(16) NOT NULL COMMENT '장바구니 품목 식별번호',
    `menu_name` varchar(50) NOT NULL COMMENT '장바구니 품목 이름',
    `menu_count` int NOT NULL COMMENT '장바구니 품목 개수',
    `menu_price` int NOT NULL COMMENT '장바구니 품목 가격',
    `created_date` datetime NOT NULL COMMENT '장바구니 품목 등록날짜',
    `updated_date` datetime COMMENT '장바구니 품목 수정날짜',
    `cart_id` binary(16) NOT NULL COMMENT '해당 품목이 담긴 장바구니 식별번호',
    PRIMARY KEY (`basket_id`),
    KEY `cart_id` (`cart_id`),
    FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
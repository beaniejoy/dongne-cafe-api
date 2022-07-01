CREATE TABLE `menu_order` (
    `order_id` binary(16) NOT NULL COMMENT '주문 식별번호',
    `order_status` varchar(20) NOT NULL COMMENT '주문 상태',
    `request_message` varchar(255) NOT NULL COMMENT '주문 요청사항',
    `created_date` datetime NOT NULL COMMENT '주문 등록날짜',
    `updated_date` datetime COMMENT '주문 수정날짜',
    `cart_id` binary(16) NOT NULL COMMENT '주문된 카트 식별번호',
    `user_id` binary(16) NOT NULL COMMENT '주문한 사용자 식별번호',
    PRIMARY KEY (`order_id`),
    KEY `cart_id` (`cart_id`),
    FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`),
    KEY `user_id` (`user_id`),
    FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
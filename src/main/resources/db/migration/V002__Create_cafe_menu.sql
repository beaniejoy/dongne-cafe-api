CREATE TABLE `cafe_menu` (
    `menu_id` binary(16) NOT NULL COMMENT '카페 메뉴 식별번호',
    `name` varchar(50) NOT NULL COMMENT '카페 메뉴명',
    `price` int NOT NULL COMMENT '메뉴 가격',
    `created_date` datetime NOT NULL COMMENT '메뉴 등록날짜',
    `updated_date` datetime COMMENT '메뉴 수정날짜',
    `cafe_id` binary(16) NOT NULL COMMENT '연관된 카페식별번호',
    PRIMARY KEY (`menu_id`),
    KEY `cafe_id` (`cafe_id`),
    FOREIGN KEY (`cafe_id`) REFERENCES `cafe` (`cafe_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
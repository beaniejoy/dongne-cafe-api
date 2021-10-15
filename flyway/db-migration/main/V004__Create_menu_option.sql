CREATE TABLE `menu_option`(
    `option_id` binary(16) NOT NULL COMMENT '옵션 식별번호',
    `title`varchar(50) NOT NULL COMMENT '메뉴 옵션 이름',
    `created_date` datetime NOT NULL COMMENT '옵션 등록날짜',
    `updated_date` datetime COMMENT '옵션 수정날짜',
    `menu_id` binary(16) NOT NULL COMMENT '연관된 카페 메뉴 식별번호',
    PRIMARY KEY (`option_id`),
    KEY `menu_id` (`menu_id`),
    FOREIGN KEY (`menu_id`) REFERENCES `cafe_menu` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
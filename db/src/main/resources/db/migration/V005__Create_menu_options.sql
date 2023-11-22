CREATE TABLE `menu_options`(
    `menu_option_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '옵션 ID',
    `title` varchar(50) NOT NULL COMMENT '메뉴 옵션 이름',
    `created_at` datetime NOT NULL COMMENT '옵션 등록날짜',
    `created_by` varchar(320) NOT NULL COMMENT '옵션 등록자',
    `updated_at` datetime COMMENT '옵션 변경날짜',
    `updated_by` varchar(320) NULL COMMENT '옵션 변경자',
    `cafe_menu_id` bigint unsigned NOT NULL COMMENT '연관된 카페 메뉴 ID',
    PRIMARY KEY (`menu_option_id`),
    KEY `fk_cafemenuid` (`cafe_menu_id`),
    FOREIGN KEY (`cafe_menu_id`) REFERENCES `cafe_menus` (`cafe_menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

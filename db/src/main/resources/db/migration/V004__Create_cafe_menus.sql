CREATE TABLE `cafe_menus` (
    `cafe_menu_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '카페 메뉴 ID',
    `name` varchar(50) NOT NULL COMMENT '카페 메뉴명',
    `price` decimal(10, 2) NOT NULL COMMENT '메뉴 가격',
    `description` varchar(255) NULL COMMENT '카페 상세설명',
    `created_at` datetime NOT NULL COMMENT '메뉴 등록날짜',
    `created_by` varchar(320) NOT NULL COMMENT '메뉴 등록자',
    `updated_at` datetime COMMENT '메뉴 변경날짜',
    `updated_by` varchar(320) NULL COMMENT '메뉴 변경자',
    `menu_category_id` bigint unsigned NOT NULL COMMENT '연관된 카페 카테고리 ID',
    PRIMARY KEY (`cafe_menu_id`),
    KEY `fk_menucategoryid` (`menu_category_id`),
    FOREIGN KEY (`menu_category_id`) REFERENCES `cafe_menu_categories` (`menu_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

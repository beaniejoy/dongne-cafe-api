DROP TABLE IF EXISTS `cafe_menu`;

CREATE TABLE `cafe_menu` (
    `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '카페 메뉴 ID',
    `name` varchar(50) NOT NULL COMMENT '카페 메뉴명',
    `price` decimal(10, 2) NOT NULL COMMENT '메뉴 가격',
    `created_at` datetime NOT NULL COMMENT '메뉴 등록날짜',
    `created_by` varchar(20) NOT NULL COMMENT '메뉴 등록자',
    `updated_at` datetime COMMENT '메뉴 변경날짜',
    `updated_by` varchar(20) NULL COMMENT '메뉴 변경자',
    `cafe_id` bigint unsigned NOT NULL COMMENT '연관된 카페 ID',
    PRIMARY KEY (`id`),
    KEY `cafe_id` (`cafe_id`),
    FOREIGN KEY (`cafe_id`) REFERENCES `cafe` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
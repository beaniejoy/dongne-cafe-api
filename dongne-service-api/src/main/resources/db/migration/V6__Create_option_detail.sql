CREATE TABLE `option_detail` (
    `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '옵션 상세 ID',
    `name` varchar(50) NOT NULL COMMENT '옵션 상세명',
    `extra_price` decimal(10, 2) NOT NULL COMMENT '옵션 추가 요금',
    `created_at` datetime NOT NULL COMMENT '옵션 상세 등록날짜',
    `created_by` varchar(20) NOT NULL COMMENT '옵션 상세 등록자',
    `updated_at` datetime COMMENT '옵션 상세 변경날짜',
    `updated_by` varchar(20) NULL COMMENT '옵션 상세 변경자',
    `option_id` bigint unsigned NOT NULL COMMENT '연관된 옵션 ID',
    PRIMARY KEY (`id`),
    KEY `option_id` (`option_id`),
    FOREIGN KEY (`option_id`) REFERENCES `menu_option` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
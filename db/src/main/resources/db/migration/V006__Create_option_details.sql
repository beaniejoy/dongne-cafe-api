CREATE TABLE `option_details` (
    `option_detail_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '옵션 상세 ID',
    `name` varchar(50) NOT NULL COMMENT '옵션 상세명',
    `extra_price` decimal(10, 2) NOT NULL COMMENT '옵션 추가 요금',
    `created_at` datetime NOT NULL COMMENT '옵션 상세 등록날짜',
    `created_by` varchar(320) NOT NULL COMMENT '옵션 상세 등록자',
    `updated_at` datetime COMMENT '옵션 상세 변경날짜',
    `updated_by` varchar(320) NULL COMMENT '옵션 상세 변경자',
    `menu_option_id` bigint unsigned NOT NULL COMMENT '연관된 옵션 ID',
    PRIMARY KEY (`option_detail_id`),
    KEY `fk_menuoptionid` (`menu_option_id`),
    FOREIGN KEY (`menu_option_id`) REFERENCES `menu_options` (`menu_option_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

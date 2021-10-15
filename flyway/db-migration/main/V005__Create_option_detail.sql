CREATE TABLE `option_detail` (
    `option_detail_id` bigint NOT NULL COMMENT '옵션 상세 번호',
    `name` varchar(50) NOT NULL COMMENT '옵션 상세명',
    `extra` int NOT NULL COMMENT '옵션 추가 요금',
    `created_date` datetime NOT NULL COMMENT '옵션 상세 등록날짜',
    `updated_date` datetime COMMENT '옵션 상세 수정날짜',
    `option_id` binary(16) NOT NULL COMMENT '연관된 옵션 식별번호',
    PRIMARY KEY (`option_detail_id`),
    KEY `option_id` (`option_id`),
    FOREIGN KEY (`option_id`) REFERENCES `menu_option` (`option_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
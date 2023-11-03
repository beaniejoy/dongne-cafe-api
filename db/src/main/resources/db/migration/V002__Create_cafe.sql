CREATE TABLE `cafe` (
    `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '카페 ID',
    `name` varchar(20) NOT NULL COMMENT '카페명',
    `address` varchar(100) NOT NULL COMMENT '카페 주소',
    `phone_number` varchar(11) NOT NULL COMMENT '카페 전화번호',
    `total_rate` float NOT NULL COMMENT '카페 종합 평가 점수',
    `description` varchar(255) COMMENT '카페 상세설명',
    `created_at` datetime NOT NULL COMMENT '카페 등록날짜',
    `created_by` varchar(320) NOT NULL COMMENT '카페 등록자',
    `updated_at` datetime NULL COMMENT '카페 변경날짜',
    `updated_by` varchar(320) NULL COMMENT '카페 변경자',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
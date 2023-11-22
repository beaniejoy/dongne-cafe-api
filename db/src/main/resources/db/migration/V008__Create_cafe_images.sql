CREATE TABLE `cafe_images` (
    `cafe_image_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '카페 이미지 ID',
    `img_url` varchar(255) NOT NULL COMMENT '이미지 경로',
    `created_at` datetime NOT NULL COMMENT '이미지 등록날짜',
    `created_by` varchar(320) NOT NULL COMMENT '이미지 등록자',
    `updated_at` datetime COMMENT '이미지 변경날짜',
    `updated_by` varchar(320) NULL COMMENT '이미지 변경자',
    `cafe_id` bigint unsigned NOT NULL COMMENT '연관된 카페 ID',
    PRIMARY KEY (`cafe_image_id`),
    KEY `fk_cafeid` (`cafe_id`),
    FOREIGN KEY (`cafe_id`) REFERENCES `cafes` (`cafe_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

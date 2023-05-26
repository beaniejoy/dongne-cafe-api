CREATE TABLE `cafe_menu_image` (
    `cafe_menu_image_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '카페 메뉴 이미지 ID',
    `img_url` varchar(255) NOT NULL COMMENT '이미지 경로',
    `created_at` datetime NOT NULL COMMENT '이미지 등록날짜',
    `created_by` varchar(320) NOT NULL COMMENT '이미지 등록자',
    `updated_at` datetime COMMENT '이미지 변경날짜',
    `updated_by` varchar(320) NULL COMMENT '이미지 변경자',
    `cafe_menu_id` bigint unsigned NOT NULL COMMENT '연관된 카페 메뉴 ID',
    PRIMARY KEY (`cafe_menu_image_id`),
    KEY `cafe_menu_id` (`cafe_menu_id`),
    FOREIGN KEY (`cafe_menu_id`) REFERENCES `cafe_menu` (`cafe_menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE `cafe_menu_image`
    ADD CONSTRAINT `cafemenuimage_cafemenuid_fk`
    FOREIGN KEY (`cafe_menu_id`)
    REFERENCES `cafe_menu` (`cafe_menu_id`);
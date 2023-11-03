CREATE TABLE `cafe_menu_category` (
    `menu_category_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '카페 메뉴 카테고리 ID',
    `name` varchar(50) NOT NULL COMMENT '메뉴 카테고리명',
    `description` varchar(255) NULL COMMENT '메뉴 카테고리 설명',
    `created_at` datetime NOT NULL COMMENT '메뉴 카테고리 등록날짜',
    `created_by` varchar(320) NOT NULL COMMENT '메뉴 카테고리 등록자',
    `updated_at` datetime COMMENT '메뉴 카테고리 변경날짜',
    `updated_by` varchar(320) NULL COMMENT '메뉴 카테고리 변경자',
    `cafe_id` bigint unsigned NOT NULL COMMENT '연관된 카페 ID',
    PRIMARY KEY (`menu_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE `cafe_menu_category`
    ADD CONSTRAINT `cafemenucategory_cafeid_fk`
    FOREIGN KEY (`cafe_id`)
    REFERENCES `cafe` (`cafe_id`);
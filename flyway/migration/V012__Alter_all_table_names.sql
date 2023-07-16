-- 1. Rename table name
ALTER TABLE `cafe` RENAME TO `cafes`;
ALTER TABLE `cafe_menu` RENAME TO `cafe_menus`;
ALTER TABLE `cafe_menu_category` RENAME TO `cafe_menu_categories`;
ALTER TABLE `cafe_image` RENAME TO `cafe_images`;
ALTER TABLE `cafe_menu_image` RENAME TO `cafe_menu_images`;
ALTER TABLE `menu_option` RENAME TO `menu_options`;
ALTER TABLE `option_detail` RENAME TO `option_details`;
ALTER TABLE `member` RENAME TO `members`;

-- 2. Change FK of cafe_menu
-- (주의) 아직 초기단계라 local, prod 환경에서 테스트 데이터 제외하고 아무것도 없기에 DELETE, ADD로 하는 것임
-- 실환경에서 데이터가 이미 있는 상황이면 먼저 변경될 fk에 값을 먼저 채워넣은 뒤에 기존 fk 제거하는 순서로 가야할 듯
ALTER TABLE `cafe_menus`
    DROP FOREIGN KEY `cafemenu_cafeid_fk`;

ALTER TABLE `cafe_menus`
    DROP COLUMN `cafe_id`,
    DROP INDEX `cafemenu_cafeid_fk`;

ALTER TABLE `cafe_menus`
    ADD COLUMN `menu_category_id` bigint unsigned NOT NULL;

ALTER TABLE `cafe_menus`
    ADD CONSTRAINT `cafemenus_menucategoryid_fk`
    FOREIGN KEY (`menu_category_id`)
    REFERENCES `cafe_menu_categories` (`menu_category_id`);

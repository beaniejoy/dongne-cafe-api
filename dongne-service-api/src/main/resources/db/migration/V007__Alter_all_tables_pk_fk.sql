-- #### Drop all foreign keys
ALTER TABLE `cafe_menu` DROP FOREIGN KEY `cafe_menu_ibfk_1`;
ALTER TABLE `cafe_menu` DROP INDEX `cafe_id`;

ALTER TABLE `cafe_image` DROP FOREIGN KEY `cafe_image_ibfk_1`;
ALTER TABLE `cafe_image` DROP INDEX `cafe_id`;

ALTER TABLE `menu_option` DROP FOREIGN KEY `menu_option_ibfk_1`;
ALTER TABLE `menu_option` DROP INDEX `menu_id`;

ALTER TABLE `option_detail` DROP FOREIGN KEY `option_detail_ibfk_1`;
ALTER TABLE `option_detail` DROP INDEX `option_id`;

-- #### Drop all primary keys & Add new primary keys
-- 1. delete auto_increment
ALTER TABLE `cafe` MODIFY id bigint NOT NULL;
ALTER TABLE `cafe_menu` MODIFY id bigint NOT NULL;
ALTER TABLE `cafe_image` MODIFY id bigint NOT NULL;
ALTER TABLE `menu_option` MODIFY id bigint NOT NULL;
ALTER TABLE `option_detail` MODIFY id bigint NOT NULL;

-- 2. drop primary key
ALTER TABLE `cafe` DROP PRIMARY KEY;
ALTER TABLE `cafe_menu` DROP PRIMARY KEY;
ALTER TABLE `cafe_image` DROP PRIMARY KEY;
ALTER TABLE `menu_option` DROP PRIMARY KEY;
ALTER TABLE `option_detail` DROP PRIMARY KEY;

-- 3. change 'id' column name and modify to primary key
ALTER TABLE `cafe`
    CHANGE `id` `cafe_id` bigint unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '카페 ID';
ALTER TABLE `cafe_menu`
    CHANGE `id` `cafe_menu_id` bigint unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '카페 메뉴 ID';
ALTER TABLE `cafe_image`
    CHANGE `id` `cafe_image_id` bigint unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '카페 이미지 ID';
ALTER TABLE `menu_option`
    CHANGE `id` `menu_option_id` bigint unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '옵션 ID';
ALTER TABLE `option_detail`
    CHANGE `id` `option_detail_id` bigint unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '옵션 상세 ID';

-- #### RENAME fk column
ALTER TABLE `menu_option`
    CHANGE `menu_id` `cafe_menu_id` bigint unsigned NOT NULL COMMENT '연관된 카페 메뉴 ID';
ALTER TABLE `option_detail`
    CHANGE `option_id` `menu_option_id` bigint unsigned NOT NULL COMMENT '연관된 옵션 ID';

-- #### Add foreign keys
ALTER TABLE `cafe_menu`
    ADD CONSTRAINT `cafemenu_cafeid_fk`
    FOREIGN KEY (`cafe_id`)
    REFERENCES `cafe` (`cafe_id`);

ALTER TABLE `cafe_image`
    ADD CONSTRAINT `cafeimage_cafeid_fk`
    FOREIGN KEY (`cafe_id`)
    REFERENCES `cafe` (`cafe_id`);

ALTER TABLE `menu_option`
    ADD CONSTRAINT `menuoption_cafemenuid_fk`
    FOREIGN KEY (`cafe_menu_id`)
    REFERENCES `cafe_menu` (`cafe_menu_id`);

ALTER TABLE `option_detail`
    ADD CONSTRAINT `optiondetail_menuoptionid_fk`
    FOREIGN KEY (`menu_option_id`)
    REFERENCES `menu_option` (`menu_option_id`);


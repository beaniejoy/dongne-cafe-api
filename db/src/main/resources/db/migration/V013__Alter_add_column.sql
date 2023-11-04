-- cafe_menus 상세 설명 칼럼 추가
ALTER TABLE `cafe_menus`
    ADD COLUMN `description` varchar(255) NULL COMMENT '카페 상세설명' AFTER `price`;

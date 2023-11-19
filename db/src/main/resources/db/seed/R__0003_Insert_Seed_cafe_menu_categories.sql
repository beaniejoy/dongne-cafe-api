DELIMITER $$
CREATE PROCEDURE insertCafeMenuCategories()
BEGIN
	DECLARE i INT DEFAULT 0;
    DECLARE j INT;
    DECLARE var_cafe_id INT;

    WHILE(i < 5) DO
		SET j = 1;
		SET var_cafe_id = (SELECT cafe_id
		                   FROM `cafes`
		                   LIMIT i, 1);

        INSERT IGNORE INTO `cafe_menu_categories` (name, description, created_at, created_by, updated_at, updated_by, cafe_id)
        VALUES ('커피음료', '카페인 커피 음료 카테고리입니다.', now(), 'system', now(), 'system', var_cafe_id);

        SET i = i + 1;
    END WHILE;

    INSERT IGNORE INTO `cafe_menu_categories` (name, description, created_at, created_by, updated_at, updated_by, cafe_id)
    VALUES ('시그니처', '우리 카페의 시그니처 커피입니다.', now(), 'system', now(), 'system', 7);
    INSERT IGNORE INTO `cafe_menu_categories` (name, description, created_at, created_by, updated_at, updated_by, cafe_id)
    VALUES ('스무디', '카페인 스무디 카테고리입니다.', now(), 'system', now(), 'system', 7);
    INSERT IGNORE INTO `cafe_menu_categories` (name, description, created_at, created_by, updated_at, updated_by, cafe_id)
    VALUES ('카테고리1', '카페인 카테고리1 입니다.', now(), 'system', now(), 'system', 7);
    INSERT IGNORE INTO `cafe_menu_categories` (name, description, created_at, created_by, updated_at, updated_by, cafe_id)
    VALUES ('카테고리2', '카페인 카테고리2 입니다.', now(), 'system', now(), 'system', 7);
    INSERT IGNORE INTO `cafe_menu_categories` (name, description, created_at, created_by, updated_at, updated_by, cafe_id)
    VALUES ('카테고리3', '카페인 카테고리3 입니다.', now(), 'system', now(), 'system', 7);
    INSERT IGNORE INTO `cafe_menu_categories` (name, description, created_at, created_by, updated_at, updated_by, cafe_id)
    VALUES ('카테고리4', '카페인 카테고리4 입니다.', now(), 'system', now(), 'system', 7);
    INSERT IGNORE INTO `cafe_menu_categories` (name, description, created_at, created_by, updated_at, updated_by, cafe_id)
    VALUES ('카테고리5', '카페인 카테고리5 입니다.', now(), 'system', now(), 'system', 7);
    INSERT IGNORE INTO `cafe_menu_categories` (name, description, created_at, created_by, updated_at, updated_by, cafe_id)
    VALUES ('카테고리6', '카페인 카테고리6 입니다.', now(), 'system', now(), 'system', 7);
    INSERT IGNORE INTO `cafe_menu_categories` (name, description, created_at, created_by, updated_at, updated_by, cafe_id)
    VALUES ('카테고리7', '카페인 카테고리7 입니다.', now(), 'system', now(), 'system', 7);
    INSERT IGNORE INTO `cafe_menu_categories` (name, description, created_at, created_by, updated_at, updated_by, cafe_id)
    VALUES ('카테고리8', '카페인 카테고리8 입니다.', now(), 'system', now(), 'system', 7);
    INSERT IGNORE INTO `cafe_menu_categories` (name, description, created_at, created_by, updated_at, updated_by, cafe_id)
    VALUES ('카테고리9', '카페인 카테고리9 입니다.', now(), 'system', now(), 'system', 7);
    INSERT IGNORE INTO `cafe_menu_categories` (name, description, created_at, created_by, updated_at, updated_by, cafe_id)
    VALUES ('카테고리10', '카페인 카테고리10 입니다.', now(), 'system', now(), 'system', 7);
    INSERT IGNORE INTO `cafe_menu_categories` (name, description, created_at, created_by, updated_at, updated_by, cafe_id)
    VALUES ('카테고리11', '카페인 카테고리11 입니다.', now(), 'system', now(), 'system', 7);
    INSERT IGNORE INTO `cafe_menu_categories` (name, description, created_at, created_by, updated_at, updated_by, cafe_id)
    VALUES ('카테고리12', '카페인 카테고리12 입니다.', now(), 'system', now(), 'system', 7);
    INSERT IGNORE INTO `cafe_menu_categories` (name, description, created_at, created_by, updated_at, updated_by, cafe_id)
    VALUES ('카테고리13', '카페인 카테고리13 입니다.', now(), 'system', now(), 'system', 7);
END$$
DELIMITER ;

CALL insertCafeMenuCategories();

DROP PROCEDURE IF EXISTS insertCafeMenuCategories;

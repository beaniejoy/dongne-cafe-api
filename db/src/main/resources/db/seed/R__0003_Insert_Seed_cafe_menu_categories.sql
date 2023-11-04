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
    VALUES ('커피음료', '카페인 커피 음료 카테고리입니다.', now(), 'system', now(), 'system', 7);
    INSERT IGNORE INTO `cafe_menu_categories` (name, description, created_at, created_by, updated_at, updated_by, cafe_id)
    VALUES ('스무디', '카페인 커피 음료 카테고리입니다.', now(), 'system', now(), 'system', 7);
END$$
DELIMITER ;

CALL insertCafeMenuCategories();

DROP PROCEDURE IF EXISTS insertCafeMenuCategories;

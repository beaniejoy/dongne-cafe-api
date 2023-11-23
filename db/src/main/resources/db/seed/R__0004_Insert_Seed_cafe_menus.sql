DROP PROCEDURE IF EXISTS insertCafeMenus;

DELIMITER $$
CREATE PROCEDURE insertCafeMenus()
BEGIN
	DECLARE i INT DEFAULT 0;
    DECLARE j INT;
    DECLARE var_cafe_id INT;
    DECLARE count_category INT;

    SET count_category = (SELECT COUNT(*) FROM `cafe_menu_categories`);
    WHILE(i < count_category) DO
		SET j = 1;

		WHILE(j <= 5) DO
			INSERT IGNORE INTO `cafe_menus` (name, price, created_at, created_by, updated_at, updated_by, menu_category_id)
			VALUES (CONCAT('커피', j), FLOOR(RAND() * 10 + 1) * 1000, now(), 'system', now(), 'system', i + 1);
			SET j = j + 1;
        END WHILE;

        SET i = i + 1;
    END WHILE;

END$$
DELIMITER ;

CALL insertCafeMenus();

DROP PROCEDURE IF EXISTS insertCafeMenus;

DROP PROCEDURE IF EXISTS insertMenus;

DELIMITER $$
CREATE PROCEDURE insertCafeMenus()
BEGIN
	DECLARE i INT DEFAULT 0;
    DECLARE j INT;
    DECLARE var_cafe_id binary(16);
    WHILE(i <= 4) DO
		SET j = 1;
		SET var_cafe_id = (SELECT cafe_id FROM `cafe` LIMIT i, 1);
		WHILE(j <= 10) DO
			INSERT IGNORE INTO `cafe_menu` (menu_id, name, price, cafe_id, created_date)
			VALUES (unhex(replace(uuid(), '-', '')), CONCAT('커피', j), FLOOR(RAND() * 10 + 1) * 1000, var_cafe_id, now());
			SET j = j + 1;
        END WHILE;
        SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;

CALL insertCafeMenus();
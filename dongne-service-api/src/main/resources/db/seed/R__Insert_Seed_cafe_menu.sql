DROP PROCEDURE IF EXISTS insertCafeMenus;

DELIMITER $$
CREATE PROCEDURE insertCafeMenus()
BEGIN
	DECLARE i INT DEFAULT 0;
    DECLARE j INT;
    DECLARE var_cafe_id INT;

    WHILE(i < 5) DO
		SET j = 1;
		SET var_cafe_id = (SELECT cafe_id
		                   FROM `cafe`
		                   LIMIT i, 1);

		WHILE(j <= 10) DO
			INSERT IGNORE INTO `cafe_menu` (name, price, created_at, created_by, updated_at, updated_by, cafe_id)
			VALUES (CONCAT('커피', j), FLOOR(RAND() * 10 + 1) * 1000, now(), 'system', null, null, var_cafe_id);
			SET j = j + 1;
        END WHILE;

        SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;

CALL insertCafeMenus();
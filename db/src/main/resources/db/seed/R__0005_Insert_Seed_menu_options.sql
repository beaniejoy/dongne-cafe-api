DELIMITER $$
CREATE PROCEDURE insertMenuOptions()
BEGIN
	DECLARE i INT DEFAULT 0;
    DECLARE var_cafe_menu_id INT;
    DECLARE count_cafe_menu INT;

    SET count_cafe_menu = (SELECT COUNT(*) FROM `cafe_menus`);

    WHILE(i < count_cafe_menu) DO
		SET var_cafe_menu_id = (SELECT cafe_menu_id
		                        FROM `cafe_menus`
		                        LIMIT i, 1);

        INSERT IGNORE INTO `menu_options` (title, created_at, created_by, updated_at, updated_by, cafe_menu_id)
        VALUES ('SIZE', now(), 'system', now(), 'system', var_cafe_menu_id);
        INSERT IGNORE INTO `menu_options` (title, created_at, created_by, updated_at, updated_by, cafe_menu_id)
        VALUES ('ICE/HOT', now(), 'system', now(), 'system', var_cafe_menu_id);

        SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;

CALL insertMenuOptions();

DROP PROCEDURE IF EXISTS insertMenuOptions;

DELIMITER $$
CREATE PROCEDURE insertOptionDetails()
BEGIN
	DECLARE i INT DEFAULT 0;
    DECLARE var_menu_option_id INT;
    DECLARE count_menu_option INT;

    SET count_menu_option = (SELECT COUNT(*) FROM `menu_options`);

    WHILE(i < count_menu_option) DO
		SET var_menu_option_id = (SELECT menu_option_id
		                          FROM `menu_options`
		                          LIMIT i, 1);

        INSERT IGNORE INTO `option_details` (name, extra_price, created_at, created_by, updated_at, updated_by, menu_option_id)
        VALUES ('MEDIUM', FLOOR(RAND() * 3 + 1) * 1000, now(), 'system', now(), 'system', var_menu_option_id);
        INSERT IGNORE INTO `option_details` (name, extra_price, created_at, created_by, updated_at, updated_by, menu_option_id)
        VALUES ('LARGE', FLOOR(RAND() * 3 + 1) * 1000, now(), 'system', now(), 'system', var_menu_option_id);
        INSERT IGNORE INTO `option_details` (name, extra_price, created_at, created_by, updated_at, updated_by, menu_option_id)
        VALUES ('VENTI', FLOOR(RAND() * 3 + 1) * 1000, now(), 'system', now(), 'system', var_menu_option_id);

        INSERT IGNORE INTO `option_details` (name, extra_price, created_at, created_by, updated_at, updated_by, menu_option_id)
        VALUES ('HOT', 0, now(), 'system', now(), 'system', var_menu_option_id + 1);
       INSERT IGNORE INTO `option_details` (name, extra_price, created_at, created_by, updated_at, updated_by, menu_option_id)
        VALUES ('ICE', 500, now(), 'system', now(), 'system', var_menu_option_id + 1);

        SET i = i + 2;
    END WHILE;
END$$
DELIMITER ;

CALL insertOptionDetails();

DROP PROCEDURE IF EXISTS insertOptionDetails;

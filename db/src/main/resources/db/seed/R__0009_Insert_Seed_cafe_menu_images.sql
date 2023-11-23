DROP PROCEDURE IF EXISTS insertCafeMenuImages;

DELIMITER $$
CREATE PROCEDURE insertCafeMenuImages()
BEGIN
	DECLARE i INT DEFAULT 0;
    DECLARE j INT;
    DECLARE idx_img INT DEFAULT 1;
    DECLARE var_cafe_menu_id INT;
    DECLARE count_cafe_menus INT;

    SET count_cafe_menus = (SELECT COUNT(*) FROM `cafe_menus`);

    WHILE(i < count_cafe_menus) DO
		SET j = 1;
		SET var_cafe_menu_id = (SELECT cafe_menu_id FROM `cafe_menus` LIMIT i, 1);

		WHILE(j <= 3) DO
			INSERT IGNORE INTO `cafe_menu_images` (img_url, created_at, created_by, updated_at, updated_by, cafe_menu_id)
			VALUES (CONCAT('https://d3qy02qh8hbgxp.cloudfront.net/cafe', idx_img, '.jpg'), now(), 'system', now(), 'system', var_cafe_menu_id);

			SET j = j + 1;
            SET idx_img = idx_img % 7 + 1;
        END WHILE;

        SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;

CALL insertCafeMenuImages();

DROP PROCEDURE IF EXISTS insertCafeMenuImages;

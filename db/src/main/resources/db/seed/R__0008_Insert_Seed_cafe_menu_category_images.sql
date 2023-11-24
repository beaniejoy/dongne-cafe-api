DROP PROCEDURE IF EXISTS insertCafeMenuCategoryImages;

DELIMITER $$
CREATE PROCEDURE insertCafeMenuCategoryImages()
BEGIN
	DECLARE i INT DEFAULT 0;
    DECLARE j INT;
    DECLARE idx_img INT DEFAULT 1;
    DECLARE var_menu_category_id INT;
    DECLARE count_cafe_categories INT;

    SET count_cafe_categories = (SELECT COUNT(*) FROM `cafe_menu_categories`);

    WHILE(i < count_cafe_categories) DO
		SET j = 1;
		SET var_menu_category_id = (SELECT menu_category_id FROM `cafe_menu_categories` LIMIT i, 1);

		WHILE(j <= 3) DO
			INSERT IGNORE INTO `cafe_menu_category_images` (img_url, created_at, created_by, updated_at, updated_by, menu_category_id)
			VALUES (CONCAT('https://d3qy02qh8hbgxp.cloudfront.net/cafe', idx_img, '.jpg'), now(), 'system', now(), 'system', var_menu_category_id);

			SET j = j + 1;
            SET idx_img = idx_img % 7 + 1;
        END WHILE;

        SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;

CALL insertCafeMenuCategoryImages();

DROP PROCEDURE IF EXISTS insertCafeMenuCategoryImages;

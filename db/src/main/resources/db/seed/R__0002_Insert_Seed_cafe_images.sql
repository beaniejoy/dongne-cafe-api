DROP PROCEDURE IF EXISTS insertCafeImages;

DELIMITER $$
CREATE PROCEDURE insertCafeImages()
BEGIN
	DECLARE i INT DEFAULT 0;
    DECLARE j INT;
    DECLARE idx_img INT DEFAULT 1;
    DECLARE var_cafe_id INT;
    DECLARE count_cafe INT;

    SET count_cafe = (SELECT COUNT(*) FROM `cafes`);

    WHILE(i < count_cafe) DO
		SET j = 1;
		SET var_cafe_id = (SELECT cafe_id FROM `cafes` LIMIT i, 1);

		WHILE(j <= 3) DO
			INSERT IGNORE INTO `cafe_images` (img_url, created_at, created_by, updated_at, updated_by, cafe_id)
			VALUES (CONCAT('https://d3qy02qh8hbgxp.cloudfront.net/cafe', idx_img, '.jpg'), now(), 'system', now(), 'system', var_cafe_id);

			SET j = j + 1;
            SET idx_img = idx_img % 7 + 1;
        END WHILE;

        SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;

CALL insertCafeImages();

DROP PROCEDURE IF EXISTS insertCafeImages;

DELIMITER $$
CREATE PROCEDURE insertCafeImages()
BEGIN
	DECLARE i INT DEFAULT 0;
    DECLARE j INT;
    DECLARE idx_img INT DEFAULT 1;
    DECLARE var_cafe_id INT;
    DECLARE count_cafe INT;

    SET count_cafe = (SELECT COUNT(*) FROM `cafe`);

    WHILE(i < count_cafe) DO
		SET j = 1;
		SET var_cafe_id = (SELECT cafe_id FROM `cafe` LIMIT i, 1);

		WHILE(j <= 3) DO
			INSERT IGNORE INTO `cafe_image` (img_url, created_at, created_by, updated_at, updated_by, cafe_id)
			VALUES (CONCAT('test_img_url_', idx_img), now(), 'system', null, null, var_cafe_id);

			SET j = j + 1;
            SET idx_img = idx_img + 1;
        END WHILE;

        SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;

CALL insertCafeImages();
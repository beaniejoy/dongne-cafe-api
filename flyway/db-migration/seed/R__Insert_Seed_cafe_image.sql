DROP PROCEDURE IF EXISTS insertCafeImages;

DELIMITER $$
CREATE PROCEDURE insertCafeImages()
BEGIN
	DECLARE i INT DEFAULT 0;
    DECLARE j INT;
    DECLARE idx_img INT DEFAULT 1;
    DECLARE var_cafe_id binary(16);
    DECLARE count_cafe INT;
    SET count_cafe = (SELECT COUNT(*) FROM `cafe`);
    WHILE(i <= count_cafe) DO
		SET j = 1;
		SET var_cafe_id = (SELECT cafe_id FROM `cafe` LIMIT i, 1);
		WHILE(j <= 3) DO
			INSERT IGNORE INTO `cafe_image` (cafe_img_id, img_url, cafe_id, created_date)
			VALUES (unhex(replace(uuid(), '-', '')), CONCAT('test_img_url_', idx_img), var_cafe_id, now());
			SET j = j + 1;
            SET idx_img = idx_img + 1;
        END WHILE;
        SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;

CALL insertCafeImages();
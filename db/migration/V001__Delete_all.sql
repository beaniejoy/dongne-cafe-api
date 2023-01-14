-- suppress warning message about unknown table
SET sql_notes = 0;

DROP TABLE IF EXISTS `option_detail`;
DROP TABLE IF EXISTS `menu_option`;
DROP TABLE IF EXISTS `cafe_image`;
DROP TABLE IF EXISTS `cafe_menu`;
DROP TABLE IF EXISTS `cafe`;

DROP PROCEDURE IF EXISTS insertCafeImages;
DROP PROCEDURE IF EXISTS insertCafeMenus;
DROP PROCEDURE IF EXISTS insertMenuOptions;
DROP PROCEDURE IF EXISTS insertOptionDetails;

-- SET sql_notes = 0;
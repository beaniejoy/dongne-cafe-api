-- suppress warning message about unknown table
SET sql_notes = 0;

DROP TABLE IF EXISTS `option_details`;
DROP TABLE IF EXISTS `menu_options`;
DROP TABLE IF EXISTS `cafe_images`;
DROP TABLE IF EXISTS `cafe_menus`;
DROP TABLE IF EXISTS `cafe_menu_categories`;
DROP TABLE IF EXISTS `cafes`;
DROP TABLE IF EXISTS `members`;

DROP PROCEDURE IF EXISTS insertCafeMenuCategories;
DROP PROCEDURE IF EXISTS insertCafeMenus;
DROP PROCEDURE IF EXISTS insertMenuOptions;
DROP PROCEDURE IF EXISTS insertOptionDetails;
DROP PROCEDURE IF EXISTS insertCafeImages;
DROP PROCEDURE IF EXISTS insertCafeMenuCategoryImages;
DROP PROCEDURE IF EXISTS insertCafeMenuImages;

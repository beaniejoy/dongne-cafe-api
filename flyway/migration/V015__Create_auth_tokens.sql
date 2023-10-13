CREATE TABLE `auth_tokens` (
    `token_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'refresh token ID',
    `member_id` bigint unsigned NOT NULL COMMENT '회원 ID',
    `access_token` varchar(200) NOT NULL COMMENT 'access 전용 token',
    `refresh_token` varchar(200) NOT NULL COMMENT 'refresh 전용 token',
    `created_at` datetime NOT NULL COMMENT '토큰 등록날짜',
    `updated_at` datetime NULL COMMENT '토큰 변경날짜',
    PRIMARY KEY (`token_id`),
    UNIQUE KEY `uk_accesstoken` (`access_token`),
    UNIQUE KEY `uk_refreshtoken` (`refresh_token`),
    KEY `member_id` (`member_id`),
    FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

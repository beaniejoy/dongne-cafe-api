CREATE TABLE `member` (
    `member_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '회원 ID',
    `email` varchar(20) NOT NULL COMMENT '계정 이메일',
    `password` varchar(255) NOT NULL COMMENT '비밀번호',
    `address` varchar(100) NOT NULL COMMENT '회원 주소',
    `phone_number` varchar(11) NOT NULL COMMENT '회원 전화번호',
    `role_type` varchar(20) COMMENT '회원 권한',
    `activated` tinyint(1) NOT NULL COMMENT '계정 활성화 여부',
    `created_at` datetime NOT NULL COMMENT '회원 등록날짜',
    `created_by` varchar(20) NOT NULL COMMENT '회원 등록자',
    `updated_at` datetime NULL COMMENT '회원 변경날짜',
    `updated_by` varchar(20) NULL COMMENT '회원 변경자',
    PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
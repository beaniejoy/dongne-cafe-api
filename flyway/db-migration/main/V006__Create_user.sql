CREATE TABLE `user` (
    `user_id` binary(16) NOT NULL COMMENT '사용자 식별번호',
    `email`varchar(50) NOT NULL COMMENT '사용자 이메일',
    `password` varchar(100) NOT NULL COMMENT '계정 비밀번호',
    `address` varchar(100) NOT NULL COMMENT '사용자 주소',
    `phone_number` varchar(11) NOT NULL COMMENT '사용자 전화번호',
    `role` varchar(20) NOT NULL COMMENT '사용자 권한',
    `created_date` datetime NOT NULL COMMENT '사용자 등록날짜',
    `updated_date` datetime COMMENT '사용자 수정날짜',
    PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
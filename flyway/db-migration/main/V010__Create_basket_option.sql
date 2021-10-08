CREATE TABLE `basket_option` (
    `basket_option_id` binary(16) NOT NULL COMMENT '품목 추가옵션 식별번호',
    `option_name`varchar(50) NOT NULL COMMENT '품목 추가옵션 내용',
    `option_extra` int NOT NULL COMMENT '품목 추가옵션 추가요금',
    `created_date` datetime NOT NULL COMMENT '품목 추가옵션 등록날짜',
    `updated_date` datetime COMMENT '품목 추가옵션 수정날짜',
    `basket_id` binary(16) NOT NULL COMMENT '추가옵션 대상 장바구니 품목 식별번호',
    PRIMARY KEY (`basket_option_id`),
    KEY `basket_id` (`basket_id`),
    FOREIGN KEY (`basket_id`) REFERENCES `basket` (`basket_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
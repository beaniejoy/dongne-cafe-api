CREATE TABLE `cafe_image` (
    `cafe_img_id` binary(16) NOT NULL COMMENT '카페 이미지 식별번호',
    `img_url` varchar(255) NOT NULL COMMENT '이미지 경로',
    `created_date` datetime NOT NULL COMMENT '이미지 등록날짜',
    `updated_date` datetime COMMENT '이미지 수정날짜',
    `cafe_id` binary(16) NOT NULL COMMENT '연관된 카페식별번호',
    PRIMARY KEY (`cafe_img_id`),
    KEY `cafe_id` (`cafe_id`),
    FOREIGN KEY (`cafe_id`) REFERENCES `cafe` (`cafe_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
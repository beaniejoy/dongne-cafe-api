CREATE TABLE `review` (
    `review_id` binary(16) NOT NULL COMMENT '리뷰 식별번호',
    `content`varchar(255) NOT NULL COMMENT '리뷰 내용',
    `img_url` varchar(255) NOT NULL COMMENT '리뷰 첨부사진 주소',
    `rate` tinyint NOT NULL COMMENT '평가점수(0 ~ 5점)',
    `created_date` datetime NOT NULL COMMENT '리뷰 등록날짜',
    `updated_date` datetime COMMENT '리뷰 수정날짜',
    `user_id` binary(16) NOT NULL COMMENT '리뷰 작성한 사용자 식별번호',
    `cafe_id` binary(16) NOT NULL COMMENT '리뷰 대상 카페 식별번호',
    PRIMARY KEY (`review_id`),
    KEY `user_id` (`user_id`),
    FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
    KEY `cafe_id` (`cafe_id`),
    FOREIGN KEY (`cafe_id`) REFERENCES `cafe` (`cafe_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
INSERT IGNORE INTO `cafe` (cafe_id, name, address, phone_number, total_rate, description, created_date)
VALUES (unhex(replace(uuid(), '-', '')), '비니카페', '서울시 동대문구 전농로', '01011112222', 3.98, '언제나 상쾌한 비니카페', now());

INSERT IGNORE INTO `cafe` (cafe_id, name, address, phone_number, total_rate, description, created_date)
VALUES (unhex(replace(uuid(), '-', '')), '조이카페', '서울시 영등포구', '01033334444', 4.67, '언제나 상쾌한 조이카페', now());

INSERT IGNORE INTO `cafe` (cafe_id, name, address, phone_number, total_rate, description, created_date)
VALUES (unhex(replace(uuid(), '-', '')), 'abc카페', '서울시 서대문구', '01025341432', 4.89, '언제나 상쾌한 abc카페', now());

INSERT IGNORE INTO `cafe` (cafe_id, name, address, phone_number, total_rate, description, created_date)
VALUES (unhex(replace(uuid(), '-', '')), '동네주변카페', '서울시 송파구', '01022223333', 4.23, '언제나 상쾌한 동네주변카페', now());

INSERT IGNORE INTO `cafe` (cafe_id, name, address, phone_number, total_rate, description, created_date)
VALUES (unhex(replace(uuid(), '-', '')), '방긋카페', '서울시 광진구', '01099998888', 4.35, '언제나 상쾌한 방긋카페', now());

INSERT IGNORE INTO `cafe` (cafe_id, name, address, phone_number, total_rate, description, created_date)
VALUES (unhex(replace(uuid(), '-', '')), 'example cafe1', '서울시 종로구', '01077779999', 2.85, '언제나 상쾌한 example cafe1', now());

INSERT IGNORE INTO `cafe` (cafe_id, name, address, phone_number, total_rate, description, created_date)
VALUES (unhex(replace(uuid(), '-', '')), 'example cafe2', '서울시', '01044445555', 3.12, '언제나 상쾌한 example cafe2', now());
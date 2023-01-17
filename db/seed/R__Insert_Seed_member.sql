-- beanie@test.com / 1111
INSERT IGNORE INTO `member` (email, password, address, phone_number, role_type, activated, created_at, created_by, updated_at, updated_by)
VALUES ('beanie@test.com', '{bcrypt}$2a$10$sgNB3pRNXFcCajZe5NTJ3OEAabzmwD7Q.lD0hOr5gAtC0q/q1kLHa', '서울 어디', '01011223344', 'ROLE_USER', true, now(), 'system', now(), 'system');
-- cafes > name unique key 설정
ALTER TABLE `cafes`
    ADD UNIQUE KEY uk_name (`name`);

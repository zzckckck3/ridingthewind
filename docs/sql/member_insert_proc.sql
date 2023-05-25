CREATE DEFINER=`ssafy`@`%` PROCEDURE `member_insert`()
BEGIN
	SET @i = 1;
	SET @num_of_member = 200;

	REPEAT
		insert into `riding_the_wind`.member(`member_id`, `member_password`, `member_name`, `member_phone`, `role`, `email_id`, `email_domain`, `birth`, `nickname`, `join_date`)
        value (CONCAT('user', @i), CONCAT('user', @i), CONCAT('user', @i), '010-1111-2222', 'USER', CONCAT('user', @i), 'naver.com', DATE_SUB(CURDATE(), INTERVAL 7000+@i DAY), CONCAT('USER', @i), DATE_SUB(CURDATE(), INTERVAL 1+@i DAY));
		SET @i = @i + 1;
		UNTIL @i > @num_of_member
	END REPEAT;
END
CREATE DEFINER=`ssafy`@`%` PROCEDURE `follow_insert`()
BEGIN
	-- 우리는 200명
	SET @i = 1;
	SET @num_of_article = 200;

	REPEAT
		insert into `riding_the_wind`.follow(`from`,`to`)
        values (CONCAT('user', @i), 'baeyonghyun'), (CONCAT('user', @i), 'yuhojae');
		SET @i = @i + 1;
		UNTIL @i > @num_of_article
	END REPEAT;
    
    -- 3명은 100명
	SET @i = 1;
	SET @num_of_article = 100;

	REPEAT
		insert into `riding_the_wind`.follow(`from`,`to`)
        values (CONCAT('user', @i+50), 'user1'), (CONCAT('user', @i+50), 'user2'), (CONCAT('user', @i+50), 'user3');
		SET @i = @i + 1;
		UNTIL @i > @num_of_article
	END REPEAT;
END
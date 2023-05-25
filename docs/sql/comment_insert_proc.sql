CREATE PROCEDURE `comment_insert` ()
BEGIN
	SET @i = 201;
	SET @num_of_article = 210;

	REPEAT
insert into `riding_the_wind`.`comment`(`article_no`, `member_id`, `content`, `register_time`) values(@i, 'yuhojae', '라크슌 저도 가봤는데 상당히 괜찮더라구요!', now() - INTERVAL 10 MINUTE);
insert into `riding_the_wind`.`comment`(`article_no`, `member_id`, `content`, `register_time`) values(@i, 'baeyonghyun', '라크슌 별로던데', now() - INTERVAL 7 MINUTE);
insert into `riding_the_wind`.`comment`(`article_no`, `member_id`, `content`, `register_time`) values(@i, 'baeyonghyun', '마약김밥은 맛있었음', now() - INTERVAL 6 MINUTE);
insert into `riding_the_wind`.`comment`(`article_no`, `member_id`, `content`, `register_time`) values(@i, 'dragon', '싸우지 마세요ㅠ', now() - INTERVAL 3 MINUTE);
insert into `riding_the_wind`.`comment`(`article_no`, `member_id`, `content`, `register_time`) values(@i, 'goodnews', '배고프다', now() - INTERVAL 1 MINUTE);
SET @i = @i + 1;
	UNTIL @i > @num_of_article
END REPEAT;
END
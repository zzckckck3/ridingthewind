CREATE DEFINER=`ssafy`@`%` PROCEDURE `article_insert`()
BEGIN
	SET @i = 1;
	SET @num_of_article = 200;

	REPEAT
		INSERT INTO riding_the_wind.article(article_no, member_id, `subject`, content, hit, `like`, register_time, modify_time)
		VALUES (@i, CONCAT('user', @i), CONCAT('글 제목 ', @i), CONCAT('글 내용 ', @i), 0, 0, DATE_SUB(CURDATE(), INTERVAL @i DAY), DATE_SUB(CURDATE(), INTERVAL @i DAY));
		SET @i = @i + 1;
	UNTIL @i > @num_of_article
	END REPEAT;
END
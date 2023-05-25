CREATE DEFINER=`ssafy`@`%` PROCEDURE `article_insert`()
BEGIN
	-- 임시 테이블 생성
    DROP TEMPORARY TABLE IF EXISTS temp_array;
	CREATE TEMPORARY TABLE temp_array (
		idx INT,
		`value` varchar(100)
	);

	-- 값 추가
	INSERT INTO temp_array (idx, `value`)
	VALUES (1, "안녕하세요~~"),
		   (2, "제주도 여행 다녀왔어요!!"),
		   (3, "숨겨진 보석 같은 여행지를 논란의 중심으로 선정해봤습니다!"),
		   (4, "대체 왜 다들 여기를 좋아하는 걸까요?"),
		   (5, "여행의 진정한 목적은 이거 아닐까요");

	SET @i = 1;
	SET @num_of_article = 200;

	REPEAT
		INSERT INTO riding_the_wind.article(article_no, member_id, `subject`, content, hit, `like`, register_time, modify_time)
		SELECT @i, CONCAT('user', @i), `value`, CONCAT('글 내용 ', @i), 0, 0, DATE_SUB(CURDATE(), INTERVAL 200-@i DAY), DATE_SUB(CURDATE(), INTERVAL @i DAY)
		FROM temp_array
		WHERE idx = (@i % 5) + 1;
		SET @i = @i + 1;
		UNTIL @i > @num_of_article
	END REPEAT;

	-- 임시 테이블 삭제
	DROP TEMPORARY TABLE temp_array;

END
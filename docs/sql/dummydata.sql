use riding_the_wind;

-- =========================== member init data =====================================
insert into `riding_the_wind`.member(`member_id`, `member_password`, `member_name`, `member_phone`, `role`, `email_id`, `email_domain`, `birth`, `nickname`, `join_date`)value('yuhojae', 'yuhojae', 'yuhojae', '010-7226-0214', 'USER', 'yuhojae', 'naver.com', DATE_SUB(CURDATE(), INTERVAL 100 DAY), 'yuhojae', DATE_SUB(CURDATE(), INTERVAL 1 DAY));
insert into `riding_the_wind`.member(`member_id`, `member_password`, `member_name`, `member_phone`, `role`, `email_id`, `email_domain`, `birth`, `nickname`, `join_date`)value('baeyonghyun', 'baeyonghyun', 'baeyonghyun', '010-2248-0214', 'USER', 'baeyonghyun', 'naver.com', DATE_SUB(CURDATE(), INTERVAL 100 DAY), 'baeyonghyun', DATE_SUB(CURDATE(), INTERVAL 1 DAY));
insert into `riding_the_wind`.member(`member_id`, `member_password`, `member_name`, `member_phone`, `role`, `email_id`, `email_domain`, `birth`, `nickname`, `join_date`)value('dragon', 'dragon', 'dragon', '010-2248-5848', 'USER', 'dragon', 'naver.com', DATE_SUB(CURDATE(), INTERVAL 100 DAY), 'dragon', DATE_SUB(CURDATE(), INTERVAL 1 DAY));
insert into `riding_the_wind`.member(`member_id`, `member_password`, `member_name`, `member_phone`, `role`, `email_id`, `email_domain`, `birth`, `nickname`, `join_date`)value('goodnews', 'goodnews', 'goodnews', '010-9958-5848', 'USER', 'goodnews', 'naver.com', DATE_SUB(CURDATE(), INTERVAL 100 DAY), 'goodnews', DATE_SUB(CURDATE(), INTERVAL 1 DAY));


-- =========================== follow init data =====================================
insert into `riding_the_wind`.follow(`from`,`to`) value('baeyonghyun','yuhojae');
insert into `riding_the_wind`.follow(`from`,`to`) value('baeyonghyun','goodnews');
insert into `riding_the_wind`.follow(`from`,`to`) value('baeyonghyun','dragon');
insert into `riding_the_wind`.follow(`from`,`to`) value('yuhojae','goodnews');
insert into `riding_the_wind`.follow(`from`,`to`) value('yuhojae','dragon');
insert into `riding_the_wind`.follow(`from`,`to`) value('yuhojae','baeyonghyun');


-- =========================== personal init data =====================================
insert into `riding_the_wind`.personal_trip(`content_id`, `member_id`) value(232225,'yuhojae');
insert into `riding_the_wind`.personal_trip(`content_id`, `member_id`) value(562471,'yuhojae');
insert into `riding_the_wind`.personal_trip(`content_id`, `member_id`) value(1897611,'yuhojae');
insert into `riding_the_wind`.personal_trip(`content_id`, `member_id`) value(1998333,'yuhojae');
insert into `riding_the_wind`.personal_trip(`content_id`, `member_id`) value(2663380,'yuhojae');

insert into `riding_the_wind`.personal_trip(`content_id`, `member_id`) value(232225,'baeyonghyun');
insert into `riding_the_wind`.personal_trip(`content_id`, `member_id`) value(562471,'baeyonghyun');
insert into `riding_the_wind`.personal_trip(`content_id`, `member_id`) value(1897611,'baeyonghyun');
insert into `riding_the_wind`.personal_trip(`content_id`, `member_id`) value(1998333,'baeyonghyun');
insert into `riding_the_wind`.personal_trip(`content_id`, `member_id`) value(2663380,'baeyonghyun');


-- =========================== article init data =====================================
insert into `riding_the_wind`.article(`article_no`, `member_id`, `subject`, `content`,`hit`, `like`, `register_time`, `modify_time`) value(201, 'yuhojae', '주말 맛집나들이~~','저번주 토요일은 이번달 다이어트로 고생한 제게 휴식을 주는 치팅데이였어요! 토요일에 다닌 맛집들을 소개할게요~~

 먼저 첫번째 코스는 모녀 마약김밥에 다녀왔습니다. 모녀 마약김밥은 정말 독특하고 중독성 있는 맛이었어요. 김밥 안의 마약소스는 정말 향긋하고 감칠맛이 났는데, 한 입 먹고 나서는 계속 먹고 싶었어요. 다양한 재료와 함께 먹으면 더욱 맛있었고, 가족과 함께 즐기기에 딱인 메뉴였어요. 추천합니다!

 다음 두번째로 다녀온 맛집 장군보쌈은 입안 가득 퍼지는 고기의 풍미와 함께 신선한 야채와 조화를 이루어 훌륭한 맛을 선사해줬어요. 보쌈을 좋아하는 분들이시라면 방문 강추입니다~

 세번째로 소개할 곳은 라크슌입니다.
 종로 맛집 라크슌은 정말로 훌륭한 음식을 제공해줘서 감탄이 절로 나왔어요. 신선한 재료와 섬세한 조리법으로 만들어지는 요리들은 맛과 품질 모두 최상이었고, 특히 저녁에 방문해서 분위기 좋은 공간에서 식사할 수 있어 만족감이 더욱 컸어요. 종로에서 특별한 식사를 원한다면 라크슌도 좋을것 같습니다~

 네번째는 양반댁입니다. 양반댁 식당은 전통적인 한정식을 맛볼 수 있는 곳으로, 정갈하고 풍부한 맛으로 기억에 남았어요. 고급스러운 분위기와 세심한 서비스도 좋았고, 다양한 반찬과 메뉴들로 소중한 시간을 보낼 수 있었습니다. 한국의 전통음식을 경험하고 싶은 분들께 추천합니다!

 마지막 식당은 인사동 옥정입니다.인사동 옥정은 맛과 분위기 둘 다 훌륭한 곳이었어요. 전통적인 한정식을 맛볼 수 있었는데, 신선한 재료와 정갈한 조리법으로 조화로운 맛을 선사해줬어요. 또한, 한옥의 아름다운 공간에서 식사할 수 있어서 더욱 특별한 경험이었습니다. 인사동을 방문한다면 꼭 옥정을 찾아보세요!

 여기까지 제가 주말에 다녀온 맛집 후기였습니다.
 도움이 되셨다면 추천, 팔로우 많이 해주세요~~', 5,5, now(), now());

insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(201,232225,3);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(201,562471,4);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(201,1897611,1);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(201,1998333,0);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(201,2663380,2);

insert into `riding_the_wind`.article(`article_no`, `member_id`, `subject`, `content`,`hit`, `like`, `register_time`, `modify_time`) value(202, 'baeyonghyun', '종로 맛집명소!!','저번주 토요일은 이번달 다이어트로 고생한 제게 휴식을 주는 치팅데이였어요! 토요일에 다닌 맛집들을 소개할게요~~

 먼저 첫번째 코스는 모녀 마약김밥에 다녀왔습니다. 모녀 마약김밥은 정말 독특하고 중독성 있는 맛이었어요. 김밥 안의 마약소스는 정말 향긋하고 감칠맛이 났는데, 한 입 먹고 나서는 계속 먹고 싶었어요. 다양한 재료와 함께 먹으면 더욱 맛있었고, 가족과 함께 즐기기에 딱인 메뉴였어요. 추천합니다!

 다음 두번째로 다녀온 맛집 장군보쌈은 입안 가득 퍼지는 고기의 풍미와 함께 신선한 야채와 조화를 이루어 훌륭한 맛을 선사해줬어요. 보쌈을 좋아하는 분들이시라면 방문 강추입니다~

 세번째로 소개할 곳은 라크슌입니다.
 종로 맛집 라크슌은 정말로 훌륭한 음식을 제공해줘서 감탄이 절로 나왔어요. 신선한 재료와 섬세한 조리법으로 만들어지는 요리들은 맛과 품질 모두 최상이었고, 특히 저녁에 방문해서 분위기 좋은 공간에서 식사할 수 있어 만족감이 더욱 컸어요. 종로에서 특별한 식사를 원한다면 라크슌도 좋을것 같습니다~

 네번째는 양반댁입니다. 양반댁 식당은 전통적인 한정식을 맛볼 수 있는 곳으로, 정갈하고 풍부한 맛으로 기억에 남았어요. 고급스러운 분위기와 세심한 서비스도 좋았고, 다양한 반찬과 메뉴들로 소중한 시간을 보낼 수 있었습니다. 한국의 전통음식을 경험하고 싶은 분들께 추천합니다!

 마지막 식당은 인사동 옥정입니다.인사동 옥정은 맛과 분위기 둘 다 훌륭한 곳이었어요. 전통적인 한정식을 맛볼 수 있었는데, 신선한 재료와 정갈한 조리법으로 조화로운 맛을 선사해줬어요. 또한, 한옥의 아름다운 공간에서 식사할 수 있어서 더욱 특별한 경험이었습니다. 인사동을 방문한다면 꼭 옥정을 찾아보세요!

 여기까지 제가 주말에 다녀온 맛집 후기였습니다.
 도움이 되셨다면 추천, 팔로우 많이 해주세요~~', 9,4, now(), now());

insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(202,232225,3);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(202,562471,4);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(202,1897611,1);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(202,1998333,0);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(202,2663380,2);

insert into `riding_the_wind`.article(`article_no`, `member_id`, `subject`, `content`,`hit`, `like`, `register_time`, `modify_time`) value(203, 'dragon', '서울 여행지 경로추천해요~','저번주 토요일은 이번달 다이어트로 고생한 제게 휴식을 주는 치팅데이였어요! 토요일에 다닌 맛집들을 소개할게요~~

 먼저 첫번째 코스는 모녀 마약김밥에 다녀왔습니다. 모녀 마약김밥은 정말 독특하고 중독성 있는 맛이었어요. 김밥 안의 마약소스는 정말 향긋하고 감칠맛이 났는데, 한 입 먹고 나서는 계속 먹고 싶었어요. 다양한 재료와 함께 먹으면 더욱 맛있었고, 가족과 함께 즐기기에 딱인 메뉴였어요. 추천합니다!

 다음 두번째로 다녀온 맛집 장군보쌈은 입안 가득 퍼지는 고기의 풍미와 함께 신선한 야채와 조화를 이루어 훌륭한 맛을 선사해줬어요. 보쌈을 좋아하는 분들이시라면 방문 강추입니다~

 세번째로 소개할 곳은 라크슌입니다.
 종로 맛집 라크슌은 정말로 훌륭한 음식을 제공해줘서 감탄이 절로 나왔어요. 신선한 재료와 섬세한 조리법으로 만들어지는 요리들은 맛과 품질 모두 최상이었고, 특히 저녁에 방문해서 분위기 좋은 공간에서 식사할 수 있어 만족감이 더욱 컸어요. 종로에서 특별한 식사를 원한다면 라크슌도 좋을것 같습니다~

 네번째는 양반댁입니다. 양반댁 식당은 전통적인 한정식을 맛볼 수 있는 곳으로, 정갈하고 풍부한 맛으로 기억에 남았어요. 고급스러운 분위기와 세심한 서비스도 좋았고, 다양한 반찬과 메뉴들로 소중한 시간을 보낼 수 있었습니다. 한국의 전통음식을 경험하고 싶은 분들께 추천합니다!

 마지막 식당은 인사동 옥정입니다.인사동 옥정은 맛과 분위기 둘 다 훌륭한 곳이었어요. 전통적인 한정식을 맛볼 수 있었는데, 신선한 재료와 정갈한 조리법으로 조화로운 맛을 선사해줬어요. 또한, 한옥의 아름다운 공간에서 식사할 수 있어서 더욱 특별한 경험이었습니다. 인사동을 방문한다면 꼭 옥정을 찾아보세요!

 여기까지 제가 주말에 다녀온 맛집 후기였습니다.
 도움이 되셨다면 추천, 팔로우 많이 해주세요~~', 2,8, now(), now());

insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(203,232225,3);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(203,562471,4);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(203,1897611,1);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(203,1998333,0);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(203,2663380,2);

insert into `riding_the_wind`.article(`article_no`, `member_id`, `subject`, `content`,`hit`, `like`, `register_time`, `modify_time`) value(204, 'goodnews', '식탐로드 제 1편','저번주 토요일은 이번달 다이어트로 고생한 제게 휴식을 주는 치팅데이였어요! 토요일에 다닌 맛집들을 소개할게요~~

 먼저 첫번째 코스는 모녀 마약김밥에 다녀왔습니다. 모녀 마약김밥은 정말 독특하고 중독성 있는 맛이었어요. 김밥 안의 마약소스는 정말 향긋하고 감칠맛이 났는데, 한 입 먹고 나서는 계속 먹고 싶었어요. 다양한 재료와 함께 먹으면 더욱 맛있었고, 가족과 함께 즐기기에 딱인 메뉴였어요. 추천합니다!

 다음 두번째로 다녀온 맛집 장군보쌈은 입안 가득 퍼지는 고기의 풍미와 함께 신선한 야채와 조화를 이루어 훌륭한 맛을 선사해줬어요. 보쌈을 좋아하는 분들이시라면 방문 강추입니다~

 세번째로 소개할 곳은 라크슌입니다.
 종로 맛집 라크슌은 정말로 훌륭한 음식을 제공해줘서 감탄이 절로 나왔어요. 신선한 재료와 섬세한 조리법으로 만들어지는 요리들은 맛과 품질 모두 최상이었고, 특히 저녁에 방문해서 분위기 좋은 공간에서 식사할 수 있어 만족감이 더욱 컸어요. 종로에서 특별한 식사를 원한다면 라크슌도 좋을것 같습니다~

 네번째는 양반댁입니다. 양반댁 식당은 전통적인 한정식을 맛볼 수 있는 곳으로, 정갈하고 풍부한 맛으로 기억에 남았어요. 고급스러운 분위기와 세심한 서비스도 좋았고, 다양한 반찬과 메뉴들로 소중한 시간을 보낼 수 있었습니다. 한국의 전통음식을 경험하고 싶은 분들께 추천합니다!

 마지막 식당은 인사동 옥정입니다.인사동 옥정은 맛과 분위기 둘 다 훌륭한 곳이었어요. 전통적인 한정식을 맛볼 수 있었는데, 신선한 재료와 정갈한 조리법으로 조화로운 맛을 선사해줬어요. 또한, 한옥의 아름다운 공간에서 식사할 수 있어서 더욱 특별한 경험이었습니다. 인사동을 방문한다면 꼭 옥정을 찾아보세요!

 여기까지 제가 주말에 다녀온 맛집 후기였습니다.
 도움이 되셨다면 추천, 팔로우 많이 해주세요~~', 3,4, now(), now());

insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(204,232225,3);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(204,562471,4);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(204,1897611,1);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(204,1998333,0);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(204,2663380,2);


-- =========================== comment init data =====================================
insert into `riding_the_wind`.`comment`(`article_no`, `member_id`, `content`, `register_time`) values(204, 'yuhojae', '라크슌 저도 가봤는데 상당히 괜찮더라구요!', now());
insert into `riding_the_wind`.`comment`(`article_no`, `member_id`, `content`, `register_time`) values(204, 'baeyonghyun', '라크슌 별로던데', now());
insert into `riding_the_wind`.`comment`(`article_no`, `member_id`, `content`, `register_time`) values(204, 'dragon', '싸우지 마세요ㅠ', now());
insert into `riding_the_wind`.`comment`(`article_no`, `member_id`, `content`, `register_time`) values(204, 'goodnews', '배고프다', now());


-- =========================== dummy data =====================================
call member_insert;
call follow_insert;
call article_insert;

use riding_the_wind;


-- =========================== member dummy data =====================================
insert into `riding_the_wind`.member(`member_id`, `member_password`, `member_name`, `member_phone`, `role`, `email_id`, `email_domain`, `birth`, `nickname`, `join_date`)value('yuhojae', 'yuhojae', 'yuhojae', '010-7226-0214', 'USER', 'yuhojae', 'naver.com', DATE_SUB(CURDATE(), INTERVAL 100 DAY), 'yuhojae', DATE_SUB(CURDATE(), INTERVAL 1 DAY));
insert into `riding_the_wind`.member(`member_id`, `member_password`, `member_name`, `member_phone`, `role`, `email_id`, `email_domain`, `birth`, `nickname`, `join_date`)value('baeyonghyun', 'baeyonghyun', 'baeyonghyun', '010-2248-0214', 'USER', 'baeyonghyun', 'naver.com', DATE_SUB(CURDATE(), INTERVAL 100 DAY), 'baeyonghyun', DATE_SUB(CURDATE(), INTERVAL 1 DAY));
insert into `riding_the_wind`.member(`member_id`, `member_password`, `member_name`, `member_phone`, `role`, `email_id`, `email_domain`, `birth`, `nickname`, `join_date`)value('dragon', 'dragon', 'dragon', '010-2248-5848', 'USER', 'dragon', 'naver.com', DATE_SUB(CURDATE(), INTERVAL 100 DAY), 'dragon', DATE_SUB(CURDATE(), INTERVAL 1 DAY));
insert into `riding_the_wind`.member(`member_id`, `member_password`, `member_name`, `member_phone`, `role`, `email_id`, `email_domain`, `birth`, `nickname`, `join_date`)value('goodnews', 'goodnews', 'goodnews', '010-9958-5848', 'USER', 'goodnews', 'naver.com', DATE_SUB(CURDATE(), INTERVAL 100 DAY), 'goodnews', DATE_SUB(CURDATE(), INTERVAL 1 DAY));
-- =========================== follow dummy data =====================================
insert into `riding_the_wind`.follow(`from`,`to`) value('baeyonghyun','yuhojae');
insert into `riding_the_wind`.follow(`from`,`to`) value('baeyonghyun','goodnews');
insert into `riding_the_wind`.follow(`from`,`to`) value('baeyonghyun','dragon');
insert into `riding_the_wind`.follow(`from`,`to`) value('yuhojae','goodnews');
insert into `riding_the_wind`.follow(`from`,`to`) value('yuhojae','dragon');
insert into `riding_the_wind`.follow(`from`,`to`) value('yuhojae','baeyonghyun');


-- =========================== personal trip data =====================================
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

-- =========================== 게시글 데이터 6개=================================================================================================================
-- 1개
insert into `riding_the_wind`.article(`article_no`, `member_id`, `subject`, `content`,`hit`, `like`, `register_time`, `modify_time`) value(212, 'baeyonghyun', '대한민국에 볼수 있는 작은 중국, 차이나타운에 가봤니?','여행 경로: 인천 종합어시장, 재미난 박물관, 신포국제시장, 태화원, 월미도 놀이공원, 을왕리해수욕장

인천 종합어시장:
인천 종합어시장은 인천에서 가장 유명한 전통 시장 중 하나입니다. 다양한 음식물과 생활용품, 의류 등을 구매할 수 있으며, 현지의 생동감과 다양한 먹거리를 경험할 수 있습니다. 신선한 해산물과 맛있는 거리 음식을 즐기는 것을 추천합니다.

재미난 박물관:
인천에는 재미난 박물관이 많이 있습니다. 예를 들면 인천 차이나타운 박물관, 인천상륙작전기념관, 인천아트플랫 등이 있습니다. 이러한 박물관에서는 역사와 문화를 배울 수 있으며, 흥미로운 전시물을 감상할 수 있습니다.

신포국제시장:
신포국제시장은 인천의 대표적인 시장으로 유명합니다. 다양한 상점과 가게가 모여있어 음식부터 의류, 액세서리 등 다양한 물건을 쇼핑할 수 있습니다. 현지의 분위기를 느끼며 즐거운 쇼핑을 즐길 수 있는 장소입니다.

태화원:
태화원은 아름다운 꽃과 정원으로 유명한 곳입니다. 계절에 따라 다양한 꽃들이 피어 있어 경치가 아름답습니다. 산책로를 따라 걷거나 휴식을 취하며 자연을 즐길 수 있습니다.

월미도 놀이공원:
월미도 놀이공원은 가족과 함께 즐길 수 있는 레저 시설이 있는 곳입니다. 다양한 놀이기구와 엔터테인먼트 시설이 있어 어린이부터 어른까지 모두 즐길 수 있습니다. 놀이공원 내에서 재미있는 시간을 보내며 여행의 휴식을 즐길 수 있습니다.

을왕리해수욕장:
을왕리해수욕장은 인천에서 가장 유명한 해수욕장 중 하나입니다. 아름다운 해변과 넓은 모래사장을 갖추고 있어 여름철에는 많은 사람들로 붐비는 곳입니다. 바닷가에서 해수욕을 즐기고 해변을 따라 산책하며 휴식을 취할 수 있습니다.

후기:
인천 여행에서 위의 경로를 따라가며 다양한 경험과 즐거움을 느낄 수 있었습니다. 종합어시장에서는 현지 문화와 음식을 경험하며 쇼핑을 즐길 수 있었고, 재미난 박물관에서는 인천의 역사와 문화를 배우며 흥미로운 전시물을 감상할 수 있었습니다. 신포국제시장에서는 다양한 물건을 쇼핑하고 현지 분위기를 느낄 수 있었고, 태화원에서는 아름다운 정원과 꽃을 감상하며 휴식을 취할 수 있었습니다. 월미도 놀이공원에서는 가족과 함께 재미있는 시간을 보내고 을왕리해수욕장에서는 아름다운 해변을 즐기며 여행의 마무리를 잘 할 수 있었습니다. 인천을 방문하는 여행객들에게 이 경로와 장소들을 추천합니다.', 
22,37 , now(), now());

select * from `riding_the_wind`.article_attraction_map;

insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(212,127585,4);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(212,128767,5);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(212,130719,1);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(212,132381,2);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(212,132742,0);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(212,135193,3);


-- 2개
insert into `riding_the_wind`.article(`article_no`, `member_id`, `subject`, `content`,`hit`, `like`, `register_time`, `modify_time`) value(213, 'dragon', '대구는 겨울에만 가세요 힐링대구여행기','
여행 경로: 대구광역시 관문시장, 관덕정, 안지랑 곱창골목, 안지랑시장, 대구앞산공원, 교육박물관, 물베기 한정식

대구광역시 관문시장:
대구광역시 관문시장은 대구에서 가장 오래된 전통 시장 중 하나로 유명합니다. 다양한 음식물과 생활용품, 의류 등을 판매하는 상점들이 모여있어 현지 문화와 다양한 먹거리를 경험할 수 있습니다. 맛있는 현지 음식을 맛보거나 쇼핑을 즐길 수 있습니다.

관덕정:
관덕정은 대구에서 유명한 유서 궁궐 중 하나입니다. 아름다운 정원과 근사한 건물들을 감상할 수 있으며, 역사적인 가치와 아름다움을 함께 느낄 수 있습니다. 관람 도중 가이드 투어를 이용하면 더욱 깊은 이해를 할 수 있습니다.

안지랑 곱창골목:
안지랑 곱창골목은 대구에서 가장 유명한 곱창 전문 거리로 알려져 있습니다. 다양한 곱창 요리와 구이 요리를 맛볼 수 있으며, 현지 음식 문화를 경험할 수 있습니다. 이곳에서 곱창을 맛보면서 맥주나 소주와 함께 즐거운 시간을 보낼 수 있습니다.

안지랑시장:
안지랑시장은 대구의 대표적인 전통 시장으로 유명합니다. 신선한 농수산물, 의류, 생활용품 등 다양한 상품들이 판매되고 있습니다. 현지인들의 생동감 넘치는 분위기를 느끼며 쇼핑을 즐길 수 있습니다.

대구앞산공원:
대구앞산공원은 아름다운 자연환경과 등산로를 갖춘 공원입니다. 산책이나 등산을 즐기면서 신선한 공기를 마시며 휴식을 취할 수 있습니다. 정상에서는 대구의 멋진 전경을 감상할 수 있어 경치도 좋습니다.

교육박물관:
대구에는 교육박물관이 다양하게 위치하고 있습니다. 역사, 과학, 문학 등 다양한 주제의 박물관에서 재미있고 유익한 시간을 보낼 수 있습니다. 대구의 교육적인 측면을 이해하고 깊은 지식을 얻을 수 있습니다.

물베기 한정식:
대구에서 유명한 음식 중 한 가지인 물베기 한정식은 특별한 경험이 될 것입니다. 물베기는 대구 지역의 전통 음식으로, 신선한 산채와 육류를 함께 먹는 한정식입니다. 풍미 있는 음식을 맛보며 지역의 특색을 느낄 수 있습니다.

후기:
대구를 여행하면서 위의 경로를 따라가며 다양한 경험과 맛있는 음식을 즐길 수 있었습니다. 관문시장과 안지랑시장에서는 현지 문화를 경험하고 쇼핑을 즐겼으며, 관덕정에서는 아름다운 궁궐을 감상할 수 있었습니다. 안지랑 곱창골목에서는 유명한 곱창 요리를 맛보았고, 대구앞산공원에서는 자연과 산책을 즐겼습니다. 교육박물관에서는 재미있는 시간을 보내며 지식을 쌓을 수 있었고, 물베기 한정식을 맛보며 대구의 특색있는 음식을 경험할 수 있었습니다. 대구를 방문하는 여행객들에게 이 경로와 장소들을 추천합니다.'
, 22,37 , now(), now());

select * from `riding_the_wind`.article_attraction_map;

insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(213,126132,4);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(213,129837,5);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(213,858791,6);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(213,998882,2);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(213,999012,3);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(213,1608882,0);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(213,1608980,1);


-- 3개
insert into `riding_the_wind`.article(`article_no`, `member_id`, `subject`, `content`,`hit`, `like`, `register_time`, `modify_time`) value(214, 'yuhojae', '해운대에도 눈이올까요 해운대로 와보세용,''
여행 경로: 강원도 강릉시 사천해변, 수리골추어탕, 원조초당순두부, 주문진수산시장, 경포대, 대관령박물관, 대관령자연휴양림

강릉시 사천해변:
강릉시 사천해변은 아름다운 해안선과 황금빛 모래사장이 특징인 곳입니다. 여름에는 해수욕을 즐기고, 겨울에는 해변을 따라 산책하며 휴식을 취할 수 있습니다. 해안의 경치와 함께 신선한 바닷마을 분위기를 느낄 수 있습니다.

수리골추어탕:
수리골추어탕은 강릉에서 유명한 음식 중 하나입니다. 신선한 강릉 추어와 고기를 사용하여 맛있고 진한 국물을 즐길 수 있습니다. 특히 추어탕과 함께 나오는 반찬들과 함께 먹으면 더욱 맛있습니다. 지역 특산물을 맛볼 수 있는 음식점으로 추천합니다.

원조초당순두부:
원조초당순두부는 강릉에서 유명한 순두부 전문 식당입니다. 신선한 콩으로 만든 순두부와 다양한 순두부 요리를 맛볼 수 있습니다. 부드럽고 고소한 순두부를 맛보면서 지역의 특색 있는 음식을 경험할 수 있습니다.

주문진수산시장:
주문진수산시장은 신선한 해산물로 유명한 시장입니다. 다양한 종류의 해산물을 구매할 수 있으며, 현지에서 조리된 해산물을 맛볼 수도 있습니다. 주문진수산시장을 방문하면 싱싱한 해산물을 구경하고 맛있는 해산물 요리를 즐길 수 있습니다.

경포대:
경포대는 강릉에서 가장 유명한 관광지 중 하나입니다. 아름다운 해안 경치와 함께 역사적인 가치를 갖고 있습니다. 해안을 따라 산책하며 경포대를 탐방하면서 휴식을 취할 수 있습니다.

대관령박물관:
대관령박물관은 대관령 지역의 역사와 문화를 소개하는 박물관입니다. 다양한 전시물과 정보를 통해 대관령의 역사적인 배경과 자연환경을 이해할 수 있습니다. 깊이 있는 관광 경험을 할 수 있는 장소입니다.

대관령자연휴양림:
대관령자연휴양림은 아름다운 자연환경과 휴식 공간을 제공하는 곳입니다. 자연과 깊이 어울리며 산책로를 따라 걷거나 피크닉을 즐길 수 있습니다. 신선한 공기와 아름다운 풍경을 감상하며 여유로운 시간을 보낼 수 있습니다.

후기:
강릉을 여행하면서 위의 경로를 따라가며 자연의 아름다움과 지역의 특산물을 경험할 수 있었습니다. 사천해변과 주문진수산시장에서는 신선한 해산물을 즐겼고, 수리골추어탕과 원조초당순두부에서는 지역의 특색 있는 음식을 맛볼 수 있었습니다. 경포대에서는 아름다운 해안 경치를 감상하며 휴식을 취했고, 대관령박물관에서는 지역의 역사와 문화를 배울 수 있었습니다. 대관령자연휴양림에서는 자연과 함께한 여유로운 시간을 보냈습니다. 강릉을 방문하는 여행객들에게 이 경로와 장소들을 추천합니다.'
, 3, 8 , now(), now());

select * from `riding_the_wind`.article_attraction_map;

insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(214,125417,6);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(214,125696,0);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(214,125790,4);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(214,130057,5);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(214,132774,3);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(214,133494,2);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(214,133927,1);

-- 4개
insert into `riding_the_wind`.article(`article_no`, `member_id`, `subject`, `content`,`hit`, `like`, `register_time`, `modify_time`) value(215, 'baeyonghyun', '밤바다는 좋지만 바가지는 안좋은 여수랍니다.','
여행 경로:

첫째날:

여수 석보: 여수 석보는 아름다운 해안선과 풍부한 자연 경관이 특징인 곳입니다. 해안을 따라 산책하며 멋진 전망을 감상할 수 있습니다.

디오션리조트 워터파크: 디오션리조트 워터파크는 물놀이와 레저 활동을 즐길 수 있는 장소입니다. 다양한 워터슬라이드와 수영장을 통해 시원한 여름을 즐길 수 있습니다.

교동시장: 교동시장은 전통적인 시장으로 다양한 상점과 음식점이 모여있습니다. 현지의 문화와 음식을 경험할 수 있는 장소입니다.

둘째날:

여수 수산물 특화시장: 여수 수산물 특화시장은 신선한 해산물과 수산가공품을 판매하는 시장입니다. 싱싱한 수산물을 구경하고 구입할 수 있으며, 현지의 맛있는 해산물 요리를 즐길 수 있습니다.

이순신광장: 이순신광장은 이순신 장군을 기리기 위해 조성된 광장입니다. 장군 동상과 함께 휴식을 취하며 역사적인 분위기를 느낄 수 있습니다.

싱글벙글빵집: 싱글벙글빵집은 유명한 빵집으로 맛있는 베이커리 제품을 즐길 수 있는 곳입니다. 다양한 종류의 빵을 맛보고 구입할 수 있습니다.

후기:
첫째날에는 여수의 아름다운 자연과 물놀이를 즐기는 디오션리조트 워터파크를 방문했습니다. 워터파크에서는 다양한 워터슬라이드와 수영장에서 시원한 여름을 즐길 수 있었습니다. 그 후 교동시장에 가서 현지 문화와 음식을 경험하였습니다.

둘째날에는 여수 수산물 특화시장에서 싱싱한 해산물을 감상하고 맛있는 해산물 요리를 즐겼습니다. 이순신광장에서는 역사적인 분위기를 느낄 수 있었고, 싱글벙글빵집에서는 맛있는 빵을 맛보았습니다. 각 장소마다 특색 있는 경험을 할 수 있었고, 여행의 다양한 측면을 즐길 수 있었습니다. 여수를 방문하는 여행객들에게 이 경로와 장소들을 추천합니다.'
, 3, 8 , now(), now());

select * from `riding_the_wind`.article_attraction_map;

insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(215,126906,0);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(215,132413,3);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(215,587699,1);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(215,2022607,5);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(215,2037230,6);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(215,2612849,2);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(215,2612869,4);

-- 5개
insert into `riding_the_wind`.article(`article_no`, `member_id`, `subject`, `content`,`hit`, `like`, `register_time`, `modify_time`) value(216, 'baeyonghyun', '무안군 재미없다고 말하면 무안해지는 꿀잼 무안코스','
여행 경로:

톱머리해수욕장: 톱머리해수욕장은 아름다운 해안선과 탁 트인 바다를 갖고 있는 해수욕장입니다. 여름에는 해수욕을 즐기고 해안을 따라 산책하는 등 여유로운 시간을 보낼 수 있습니다.

무안 비치호텔: 무안 비치호텔은 편안한 숙박 시설로 휴식과 편안함을 제공합니다. 바다 전망을 감상하며 휴가를 즐길 수 있는 곳입니다.

무안 유교리고가: 무안 유교리고가는 아름다운 자연 경관과 함께 전통적인 한옥과 문화를 체험할 수 있는 장소입니다. 유교 문화의 깊이를 느낄 수 있습니다.

원갑사: 원갑사는 아름다운 절로, 역사적인 가치를 지니고 있습니다. 조용하고 평화로운 분위기에서 여유를 즐길 수 있으며, 절내의 문화재와 자연을 감상할 수 있습니다.

톱머리마을: 톱머리마을은 전통적인 한옥과 예쁜 마을 풍경으로 유명합니다. 오래된 한옥 집들과 돌담길을 따라 걷면서 전통적인 분위기를 느낄 수 있습니다.

무안회산백련지: 무안회산백련지는 아름다운 연못과 연꽃으로 유명한 곳입니다. 자연의 아름다움과 평화로운 분위기를 느끼며 산책을 즐길 수 있습니다.

후기:
위의 경로를 따라 무안을 여행하면서 여러 가지 경험을 할 수 있었습니다. 톱머리해수욕장에서는 해변에서 여름을 즐겼고, 무안 비치호텔에서는 편안한 숙박을 즐겼습니다. 유교리고가에서는 유교 문화를 체험하고, 원갑사에서는 역사적인 가치를 감상했습니다. 톱머리마을에서는 전통적인 분위기를 느끼며 산책을 즐겼고, 무안회산백련지에서는 자연의 아름다움을 감상했습니다. 무안을 방문하는 여행객들에게 이 경로와 장소들을 추천합니다.'
, 3, 8 , now(), now());

select * from `riding_the_wind`.article_attraction_map;

insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(216,126275,2);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(216,127603,5);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(216,129100,1);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(216,129210,4);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(216,138864,3);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(216,319720,0);

-- 6개
insert into `riding_the_wind`.article(`article_no`, `member_id`, `subject`, `content`,`hit`, `like`, `register_time`, `modify_time`) value(217, 'baeyonghyun', '대한민국 제2의 도시, 광명을 소개합니다.','
여행 경로:

광명 금천한우타운: 광명 금천한우타운은 유명한 한우 맛집과 한우 관련 체험을 즐길 수 있는 곳입니다. 맛있는 한우 요리를 맛보고 한우에 대한 지식을 넓힐 수 있습니다.

광명에디슨뮤지엄: 광명에디슨뮤지엄은 토마스 앨바 에디슨에 대한 역사와 발명품을 전시하는 박물관입니다. 혁신적인 발명가의 업적을 배우고 관련 전시물을 감상할 수 있습니다.

충현박물관: 충현박물관은 광명의 역사와 문화를 소개하는 박물관입니다. 다양한 유물과 전시물을 통해 지역의 역사적인 배경을 이해할 수 있습니다.

광명영화원: 광명영화원은 영화와 관련된 전시물과 체험을 제공하는 공간입니다. 영화 역사에 대한 지식을 넓히고 다양한 체험 프로그램을 즐길 수 있습니다.

구름산 산림욕장: 구름산 산림욕장은 아름다운 자연환경과 산림욕을 즐길 수 있는 곳입니다. 신선한 공기를 마시며 자연 속에서 휴식을 취할 수 있습니다.

광명전통시장: 광명전통시장은 전통적인 시장으로 다양한 상점과 음식점이 모여있습니다. 현지의 문화와 음식을 경험할 수 있는 장소입니다.

후기:
광명을 여행하면서 위의 경로를 따라갔습니다. 광명 금천한우타운에서는 맛있는 한우 요리를 맛보고 한우에 대한 지식을 넓힐 수 있었습니다. 광명에디슨뮤지엄에서는 토마스 앨바 에디슨의 발명품과 업적을 배울 수 있었고, 충현박물관에서는 지역의 역사와 문화를 이해할 수 있었습니다. 광명영화원에서는 영화와 관련된 전시물을 감상하고 체험 프로그램을 즐겼습니다. 구름산 산림욕장에서는 아름다운 자연 속에서 휴식을 즐기고, 광명전통시장에서는 현지의 문화와 음식을 경험할 수 있었습니다. 광명을 방문하는 여행객들에게 이 경로와 장소들을 추천합니다.
', 3, 8 , now(), now());

select * from `riding_the_wind`.article_attraction_map;

insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(217,130707,2);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(217,736607,3);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(217,1754696,5);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(217,2733410,1);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(217,2733419,4);
insert into `riding_the_wind`.article_attraction_map(`article_no`,`content_id`,`order`) value(217,2830327,0);
/* 	-----------------------------------------------------
	스키마 생성
	-----------------------------------------------------	*/
drop database if exists `riding_the_wind`;
create database `riding_the_wind` DEFAULT CHARACTER SET utf8mb4;
use `riding_the_wind`;


/* 	-----------------------------------------------------
	회원 정보 테이블
	-----------------------------------------------------	*/
CREATE TABLE IF NOT EXISTS `riding_the_wind`.`member` (
  `member_id` VARCHAR(16) NOT NULL,
  `member_password` VARCHAR(64) NOT NULL,
  `member_name` VARCHAR(20) NOT NULL,
  `member_phone` CHAR(13) NOT NULL,
  `role` VARCHAR(10) NOT NULL DEFAULT 'USER',
  `email_id` VARCHAR(20) NOT NULL,
  `email_domain` VARCHAR(45) NOT NULL,
  `birth` CHAR(10),
  `nickname` VARCHAR(45) NOT NULL,
  `join_date` CHAR(10),
  `token` VARCHAR(1000),
  `deleted` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`member_id`)
  )
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


/* 	-----------------------------------------------------
	게시판 테이블
	-----------------------------------------------------	*/
CREATE TABLE IF NOT EXISTS `riding_the_wind`.`article` (
	`article_no` INT NOT NULL AUTO_INCREMENT,
	`member_id` VARCHAR(16) NOT NULL,
	`subject` VARCHAR(100) NOT NULL,
	`content` VARCHAR(2000) NOT NULL,
	`hit` INT NOT NULL DEFAULT 0,
	`like` INT NOT NULL DEFAULT 0,
	`register_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`modify_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`article_no`)
  )
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- 게시판 테이블 member_id를 회원 테이블 member_id의 foreign key로 지정
alter table `article` add constraint fk_article_to_member_member_id foreign key(member_id) references member(member_id) on delete cascade;


/* 	-----------------------------------------------------
	댓글 테이블
	-----------------------------------------------------	*/
CREATE TABLE IF NOT EXISTS `riding_the_wind`.`comment` (
	`comment_no` INT NOT NULL AUTO_INCREMENT,
	`article_no` INT NOT NULL,
	`member_id` VARCHAR(16) NOT NULL,
	`content` VARCHAR(100) NOT NULL,
	`register_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`comment_no`)
  )
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- 댓글 테이블의 member_id를 회원 테이블 member_id를 참조하는 foreign key로 지정
alter table `comment` add constraint fk_comment_to_member_member_id foreign key(member_id) references member(member_id) on delete cascade;

-- 댓글 테이블의 article_no를 게시판 테이블 article_no를 참조하는 foreign key로 지정
alter table `comment` add constraint fk_comment_to_article_article_no foreign key(article_no) references article(article_no) on delete cascade;


/* 	-----------------------------------------------------
	질문 테이블
	-----------------------------------------------------	*/
CREATE TABLE IF NOT EXISTS `riding_the_wind`.`question` (
	`question_no` INT NOT NULL AUTO_INCREMENT,
	`member_id` VARCHAR(16) NOT NULL,
	`content` VARCHAR(2000) NOT NULL,
	`register_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`question_no`)
  )
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- 게시판 테이블 member_id를 회원 테이블 member_id의 foreign key로 지정
alter table `question` add constraint fk_question_to_member_member_id foreign key(member_id) references member(member_id) on delete cascade;


/* 	-----------------------------------------------------
	답변 테이블
	-----------------------------------------------------	*/
CREATE TABLE IF NOT EXISTS `riding_the_wind`.`answer` (
	`answer_no` INT NOT NULL AUTO_INCREMENT,
	`question_no` INT NOT NULL,
	`member_id` VARCHAR(16) NOT NULL,
	`content` VARCHAR(100) NOT NULL,
	`register_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`answer_no`)
  )
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- 답변 테이블의 member_id를 회원 테이블의 member_id를 참조하는 foreign key로 지정
alter table `answer` add constraint fk_answer_to_member_member_id foreign key(member_id) references member(member_id) on delete cascade;

-- 답변 테이블의 question_no를 답변 테이블의 question_no를 참조하는 foreign key로 지정
alter table `answer` add constraint fk_answer_to_question_question_no foreign key(question_no) references question(question_no) on delete cascade;


/* 	-----------------------------------------------------
	공지사항 테이블
	-----------------------------------------------------	*/
CREATE TABLE IF NOT EXISTS `riding_the_wind`.`notice` (
	`notice_no` INT NOT NULL AUTO_INCREMENT,
	`content` VARCHAR(2000) NOT NULL,
	`register_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`notice_no`)
  )
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


/* 	-----------------------------------------------------
	시/도 테이블
	-----------------------------------------------------	*/
CREATE TABLE IF NOT EXISTS `riding_the_wind`.`sido` (
	`sido_code` INT NOT NULL,
	`sido_name` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`sido_code`))
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


/* 	-----------------------------------------------------
	구/군 테이블
	-----------------------------------------------------	*/
CREATE TABLE IF NOT EXISTS `riding_the_wind`.`gugun` (
	`gugun_code` INT NOT NULL,
	`gugun_name` VARCHAR(30) NULL DEFAULT NULL,
	`sido_code` INT NOT NULL,
  PRIMARY KEY (`gugun_code`, `sido_code`),
  INDEX `gugun_to_sido_sido_code_fk_idx` (`sido_code` ASC) VISIBLE,
  CONSTRAINT `gugun_to_sido_sido_code_fk`
  FOREIGN KEY (`sido_code`)
  REFERENCES `riding_the_wind`.`sido` (`sido_code`))
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


/* 	-----------------------------------------------------
	관광정보 테이블
	-----------------------------------------------------	*/
CREATE TABLE IF NOT EXISTS `riding_the_wind`.`attraction_info` (
	`content_id` INT NOT NULL,
	`content_type_id` INT NULL DEFAULT NULL,
	`title` VARCHAR(100) NULL DEFAULT NULL,
	`addr1` VARCHAR(100) NULL DEFAULT NULL,
	`addr2` VARCHAR(50) NULL DEFAULT NULL,
	`zipcode` VARCHAR(50) NULL DEFAULT NULL,
	`tel` VARCHAR(50) NULL DEFAULT NULL,
	`first_image` VARCHAR(200) NULL DEFAULT NULL,
	`first_image2` VARCHAR(200) NULL DEFAULT NULL,
	`readcount` INT NULL DEFAULT NULL,
	`sido_code` INT NULL DEFAULT NULL,
	`gugun_code` INT NULL DEFAULT NULL,
	`latitude` DECIMAL(20,17) NULL DEFAULT NULL,
	`longitude` DECIMAL(20,17) NULL DEFAULT NULL,
	`mlevel` VARCHAR(2) NULL DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  INDEX `attraction_to_content_type_id_fk_idx` (`content_type_id` ASC) VISIBLE,
  INDEX `attraction_to_sido_code_fk_idx` (`sido_code` ASC) VISIBLE,
  INDEX `attraction_to_gugun_code_fk_idx` (`gugun_code` ASC) VISIBLE,
  CONSTRAINT `attraction_to_gugun_code_fk`
  FOREIGN KEY (`gugun_code`)
  REFERENCES `riding_the_wind`.`gugun` (`gugun_code`),
  CONSTRAINT `attraction_to_sido_code_fk`
  FOREIGN KEY (`sido_code`)
  REFERENCES `riding_the_wind`.`sido` (`sido_code`))
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


/* 	-----------------------------------------------------
	관광정보 부가설명 테이블
	-----------------------------------------------------	*/
CREATE TABLE IF NOT EXISTS `riding_the_wind`.`attraction_description` (
	`content_id` INT NOT NULL,
	`homepage` VARCHAR(100) NULL DEFAULT NULL,
	`overview` VARCHAR(10000) NULL DEFAULT NULL,
	`telname` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  CONSTRAINT `attraction_detail_to_attraciton_id_fk`
  FOREIGN KEY (`content_id`)
  REFERENCES `riding_the_wind`.`attraction_info` (`content_id`))
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


/* 	-----------------------------------------------------
	상세 관광정보 테이블
	-----------------------------------------------------	*/
CREATE TABLE IF NOT EXISTS `riding_the_wind`.`attraction_detail` (
	`content_id` INT NOT NULL,
	`cat1` VARCHAR(3) NULL DEFAULT NULL,
	`cat2` VARCHAR(5) NULL DEFAULT NULL,
	`cat3` VARCHAR(9) NULL DEFAULT NULL,
	`created_time` VARCHAR(14) NULL DEFAULT NULL,
	`modified_time` VARCHAR(14) NULL DEFAULT NULL,
	`booktour` VARCHAR(5) NULL DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  CONSTRAINT `attraction_detail_to_basic_content_id_fk`
  FOREIGN KEY (`content_id`)
  REFERENCES `riding_the_wind`.`attraction_info` (`content_id`))
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


/* 	-----------------------------------------------------
	여행 계획 테이블
	-----------------------------------------------------	*/
CREATE TABLE IF NOT EXISTS `personal_trip` (
	content_id INT NOT NULL,
	`member_id` VARCHAR(16) NOT NULL,
	PRIMARY KEY (content_id, `member_id`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- 여행계획 테이블 member_id를 회원 테이블 member_id의 foreign key로 지정
alter table `personal_trip` add constraint fk_personal_trip_member foreign key(member_id) references member(member_id) on delete cascade;

-- 여행계획 테이블 attraction_id를 관광정보 테이블 content_id의 외래키로 지정
alter table `personal_trip` add constraint fk_personal_trip_attraction_info foreign key(content_id) references attraction_info(content_id);


/* 	-----------------------------------------------------
	게시판에 여행 경로를 등록하기 위한 매핑 테이블
	-----------------------------------------------------	*/
CREATE TABLE IF NOT EXISTS `article_attraction_map` (
	`article_no` INT NOT NULL,
	content_id INT NOT NULL,
    `order` INT NOT NULL,
	PRIMARY KEY (`article_no`, content_id)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- 게시판-여행경로 매핑 테이블 article_no를 게시판 테이블 article_no의 외래키로 지정
alter table `article_attraction_map` add constraint fk_article_aam foreign key(article_no) references article(article_no) on delete cascade;

-- 게시판-여행경로 매핑 테이블 attraction_id를 관광정보 테이블 content_id의 외래키로 지정
alter table `article_attraction_map` add constraint fk_attraction_aam foreign key(content_id) references attraction_info(content_id);

create table if not exists `riding_the_wind`.`follow`(
    `from` varchar(16) NOT NULL,
    `to` varchar(16) NOT NULL,
    PRIMARY KEY(`to`, `from`),
    foreign key(`to`) references member(member_id),
    foreign key(`from`) references member(member_id)
    );
-- 추천 시스템을 적용하기 위해 공유 게시판에 글이 등록된 횟수를 카운트 하는 column을 sido, gugun에 추가
ALTER TABLE `sido` modify `post_count` int(10) default 0;
ALTER TABLE `gugun` modify `post_count` int(10) default 0;


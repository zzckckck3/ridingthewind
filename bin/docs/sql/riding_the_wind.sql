-- 스키마 생성 및 사용
create database if not exists `riding_the_wind` DEFAULT CHARACTER SET utf8mb4;
use `riding_the_wind`;

-- member 테이블 생성
CREATE TABLE IF NOT EXISTS `riding_the_wind`.`member` (
  `member_id` VARCHAR(16) NOT NULL,
  `member_password` VARCHAR(64) NOT NULL,
  `member_name` VARCHAR(20) NOT NULL,
  `member_phone` CHAR(13) NOT NULL,
  `email_id` VARCHAR(20) NULL DEFAULT NULL,
  `email_domain` VARCHAR(45) NULL DEFAULT NULL,
  `birth` TIMESTAMP NOT NULL,
  `nickname` VARCHAR(45) NOT NULL,
  `join_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`member_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- board 테이블 생성
CREATE TABLE IF NOT EXISTS `riding_the_wind`.`board` (
  `article_no` INT NOT NULL AUTO_INCREMENT,
  `member_id` VARCHAR(16) NULL DEFAULT NULL,
  `subject` VARCHAR(100) NULL DEFAULT NULL,
  `content` VARCHAR(2000) NULL DEFAULT NULL,
  `hit` INT NULL DEFAULT 0,
  `register_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `like` INT NOT NULL DEFAULT 0,
  `modify_time` TIMESTAMP NULL DEFAULT now(),
  PRIMARY KEY (`article_no`),
  INDEX `board_to_members_member_id_fk` (`member_id` ASC) VISIBLE,
  CONSTRAINT `board_to_members_member_id_fk`
    FOREIGN KEY (`member_id`)
    REFERENCES `member` (`member_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- personal_trip(마이페이지) db
CREATE TABLE IF NOT EXISTS `personal_trip` (
  content_id INT NOT NULL,
  `member_id` VARCHAR(16) NOT NULL,
  PRIMARY KEY (content_id, `member_id`),
  INDEX `fk_member_member_id_idx` (`member_id` ASC) VISIBLE,
  CONSTRAINT `fk_member_member_id`
    FOREIGN KEY (`member_id`)
    REFERENCES `riding_the_wind`.`member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `riding_the_wind`.`sido` (
  `sido_code` INT NOT NULL,
  `sido_name` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`sido_code`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `riding_the_wind`.`gugun`
-- -----------------------------------------------------
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


-- -----------------------------------------------------
-- Table `riding_the_wind`.`attraction_info`
-- -----------------------------------------------------
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


-- -----------------------------------------------------
-- Table `riding_the_wind`.`attraction_description`
-- -----------------------------------------------------
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


-- -----------------------------------------------------
-- Table `riding_the_wind`.`attraction_detail`
-- -----------------------------------------------------
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

-- personal_trip테이블의 member_id를 member테이블 member_id의 외래키로 지정
alter table `personal_trip` add constraint fk_personal_trip_member foreign key(member_id) references member(member_id) on delete cascade;

-- personal_trip테이블의 attraction_id를 attraction_info테이블 content_id의 외래키로 지정
alter table `personal_trip` add constraint fk_personal_trip_attraction_info foreign key(content_id) references attraction_info(content_id);


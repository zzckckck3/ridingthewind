use riding_the_wind;

SELECT * FROM article;

insert into member(member_id, member_password, member_name, member_phone, email_id, email_domain, birth, nickname)
values ('byh9811', '1234', '배용현', '01011112222', 'byh9811', '@naver.com', '1998-11-28', 'null사랑하지않아'),
('ho0214', '1234', '유호재', '01029292929', 'ho0214', '@naver.com', '1998-02-14', '짱귀요미유호재');

insert into article(member_id, subject, content) 
values ('byh9811', '제목1', '내용1'),
('byh9811', '제목2', '내용2'),
('byh9811', '제목3', '내용3'),
('byh9811', '제목4', '내용4'),
('byh9811', '제목5', '내용5'),
('byh9811', '제목6', '내용6'),
('ho0214', '제목7', '내용7'),
('ho0214', '제목8', '내용8'),
('ho0214', '제목9', '내용9'),
('ho0214', '제목10', '내용10'),
('ho0214', '제목11', '내용11'),
('ho0214', '제목12', '내용12');


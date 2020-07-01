
INSERT INTO `groupings`.`grouping_user`
(`grouping_user_id`,
`birth_day`,
`email`,
`gender`,
`name`,
`nation_code`,
`password`,
`phone_number`,
`user_id`,
`user_status`,
`created_at`,
`updated_at`)
VALUES
('34a416b2-b2c4-11ea-93c1-1a2157508cce',
'1992-01-02',
'test@naver.com',
'MALE',
'진수민',
'SOUTH_KOREA',
'RRRRe123!',
'01047547247',
'read105',
'NORMAL',
now(),
now());

INSERT INTO `groupings`.`grouping_user`
(`grouping_user_id`,
`birth_day`,
`email`,
`gender`,
`name`,
`nation_code`,
`password`,
`phone_number`,
`user_id`,
`user_status`,
`created_at`,
`updated_at`)
VALUES
('34a416b2-b2c4-11ea-93c1-1a2157509cca',
'1992-03-02',
'test2@naver.com',
'FEMALE',
'이수민',
'SOUTH_KOREA',
'RRRRe123@',
'01047547248',
'read106',
'NORMAL',
now(),
now());

INSERT INTO `groupings`.`grouping_user`
(`grouping_user_id`,
`birth_day`,
`email`,
`gender`,
`name`,
`nation_code`,
`password`,
`phone_number`,
`user_id`,
`user_status`,
`created_at`,
`updated_at`)
VALUES
('34a416b2-b2c4-11ea-93c1-1a2157a08c99',
'1993-03-03',
'ljh0940@naver.com',
'MALE',
'이중혁',
'SOUTH_KOREA',
'abc1234@',
'01050333037',
'ljh0940',
'NORMAL',
now(),
now());

INSERT INTO `groupings`.`grouping_user`
(`grouping_user_id`,
`birth_day`,
`email`,
`gender`,
`name`,
`nation_code`,
`password`,
`phone_number`,
`user_id`,
`user_status`,
`created_at`,
`updated_at`)
VALUES
('34a416b2-b2c4-11ea-93c1-1a21b75081c3',
'1993-03-04',
'ljh3037@naver.com',
'FEMALE',
'일중혁',
'SOUTH_KOREA',
'abc1234$',
'01050333038',
'ljh3037',
'NORMAL',
now(),
now());

INSERT INTO `groupings`.`grouping_user`
(`grouping_user_id`,
`birth_day`,
`email`,
`gender`,
`name`,
`nation_code`,
`password`,
`phone_number`,
`user_id`,
`user_status`,
`created_at`,
`updated_at`)
VALUES
('34a416b2-b2c4-11ea-93c2-1ad1575083c4',
'1993-03-05',
'ljh5033@naver.com',
'MALE',
'삼중혁',
'SOUTH_KOREA',
'abc1234!',
'01050333039',
'ljh5033',
'NORMAL',
now(),
now());

INSERT INTO `groupings`.`crew`
(`group_id`,
`created_at`,
`updated_at`,
`available_gender`,
`description`,
`is_hidden`,
`max_user_age`,
`min_user_age`,
`point_description`,
`point_x`,
`point_y`,
`title`)
VALUES
(1,
now(),
now(),
'ALL',
'가즈아~~',
false,
'40',
'15',
'my home',
'523.23',
'424.32',
'코린이 탈출 가즈아~');

INSERT INTO `groupings`.`crew`
(`group_id`,
`created_at`,
`updated_at`,
`available_gender`,
`description`,
`is_hidden`,
`max_user_age`,
`min_user_age`,
`point_description`,
`point_x`,
`point_y`,
`title`)
VALUES
(2,
now(),
now(),
'ALL',
'가즈아ㅏㅏ~~',
false,
'30',
'24',
'my company',
'544.23',
'499.32',
'취뽀 가즈아~');

INSERT INTO `groupings`.`crew`
(`group_id`,
`created_at`,
`updated_at`,
`available_gender`,
`description`,
`is_hidden`,
`max_user_age`,
`min_user_age`,
`point_description`,
`point_x`,
`point_y`,
`title`)
VALUES
(3,
now(),
now(),
'ALL',
'밴드 그룹',
false,
'45',
'20',
'cafe',
'100.11',
'301.81',
'groupers 밴드');

INSERT INTO `groupings`.`crew`
(`group_id`,
`created_at`,
`updated_at`,
`available_gender`,
`description`,
`is_hidden`,
`max_user_age`,
`min_user_age`,
`point_description`,
`point_x`,
`point_y`,
`title`)
VALUES
(4,
now(),
now(),
'ALL',
'독서 그룹',
false,
'38',
'23',
'study room',
'100.11',
'301.81',
'groupers 독서 모임');

INSERT INTO `groupings`.`crew`
(`group_id`,
`created_at`,
`updated_at`,
`available_gender`,
`description`,
`is_hidden`,
`max_user_age`,
`min_user_age`,
`point_description`,
`point_x`,
`point_y`,
`title`)
VALUES
(5,
now(),
now(),
'ALL',
'술술 그룹',
false,
'38',
'23',
'hope',
'100.11',
'301.81',
'groupers 술 모임');

INSERT INTO `groupings`.`crew`
(`group_id`,
`created_at`,
`updated_at`,
`available_gender`,
`description`,
`is_hidden`,
`max_user_age`,
`min_user_age`,
`point_description`,
`point_x`,
`point_y`,
`title`)
VALUES
(6,
now(),
now(),
'MALE',
'운동 그룹',
false,
'38',
'23',
'hope',
'100.11',
'301.81',
'groupers 운동 모임');

INSERT INTO `groupings`.`crew`
(`group_id`,
`created_at`,
`updated_at`,
`available_gender`,
`description`,
`is_hidden`,
`max_user_age`,
`min_user_age`,
`point_description`,
`point_x`,
`point_y`,
`title`)
VALUES
(7,
now(),
now(),
'ALL',
'운동 그룹2',
false,
'38',
'23',
'ground',
'100.11',
'301.81',
'groupers 운동 모임2');

INSERT INTO `groupings`.`crew`
(`group_id`,
`created_at`,
`updated_at`,
`available_gender`,
`description`,
`is_hidden`,
`max_user_age`,
`min_user_age`,
`point_description`,
`point_x`,
`point_y`,
`title`)
VALUES
(8,
now(),
now(),
'ALL',
'운동 그룹3',
false,
'38',
'23',
'ground',
'100.11',
'301.81',
'groupers 운동 모임3');

INSERT INTO `groupings`.`crew`
(`group_id`,
`created_at`,
`updated_at`,
`available_gender`,
`description`,
`is_hidden`,
`max_user_age`,
`min_user_age`,
`point_description`,
`point_x`,
`point_y`,
`title`)
VALUES
(9,
now(),
now(),
'ALL',
'운동 그룹4',
false,
'38',
'23',
'ground',
'100.11',
'301.81',
'groupers 운동 모임4');

INSERT INTO `groupings`.`crew`
(`group_id`,
`created_at`,
`updated_at`,
`available_gender`,
`description`,
`is_hidden`,
`max_user_age`,
`min_user_age`,
`point_description`,
`point_x`,
`point_y`,
`title`)
VALUES
(10,
now(),
now(),
'ALL',
'술 모임',
false,
'38',
'23',
'hope',
'100.11',
'301.81',
'groupers 술 모임');

INSERT INTO `groupings`.`crew`
(`group_id`,
`created_at`,
`updated_at`,
`available_gender`,
`description`,
`is_hidden`,
`max_user_age`,
`min_user_age`,
`point_description`,
`point_x`,
`point_y`,
`title`)
VALUES
(11,
now(),
now(),
'ALL',
'소믈리에 모임',
false,
'38',
'23',
'hope',
'100.11',
'301.81',
'groupers 소믈리에 모임');

INSERT INTO `groupings`.`crew`
(`group_id`,
`created_at`,
`updated_at`,
`available_gender`,
`description`,
`is_hidden`,
`max_user_age`,
`min_user_age`,
`point_description`,
`point_x`,
`point_y`,
`title`)
VALUES
(12,
now(),
now(),
'ALL',
'제빵 모임',
false,
'38',
'23',
'hope',
'100.11',
'301.81',
'groupers 제빵 모임');

INSERT INTO `groupings`.`user_group_mapping`
(`user_group_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`grouping_user_id`)
VALUES
(1,
now(),
now(),
1,
'34a416b2-b2c4-11ea-93c1-1a2157508cce');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(1,
now(),
now(),
'music');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(2,
now(),
now(),
'life');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(3,
now(),
now(),
'work');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(4,
now(),
now(),
'wine');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(5,
now(),
now(),
'beer');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(6,
now(),
now(),
'game');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(7,
now(),
now(),
'movie');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(8,
now(),
now(),
'soccer');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(9,
now(),
now(),
'study');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(10,
now(),
now(),
'book');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(11,
now(),
now(),
'computer');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(12,
now(),
now(),
'english');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(13,
now(),
now(),
'java');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(14,
now(),
now(),
'python');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(15,
now(),
now(),
'c++');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(16,
now(),
now(),
'band');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(17,
now(),
now(),
'guitar');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(18,
now(),
now(),
'fashion');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(19,
now(),
now(),
'shoes');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(20,
now(),
now(),
'car');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(21,
now(),
now(),
'bike');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(22,
now(),
now(),
'motorcycle');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(23,
now(),
now(),
'vehicle');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(24,
now(),
now(),
'cook');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(25,
now(),
now(),
'bakery');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(26,
now(),
now(),
'Patissier');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(27,
now(),
now(),
'sommelier');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(28,
now(),
now(),
'baseball');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(29,
now(),
now(),
'basketball');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(30,
now(),
now(),
'golf');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(31,
now(),
now(),
'daily');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(32,
now(),
now(),
'travel');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(33,
now(),
now(),
'trip');

INSERT INTO `groupings`.`hashtag`
(`hashtag_id`,
`created_at`,
`updated_at`,
`hashtag`)
VALUES
(34,
now(),
now(),
'community');

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(1,
now(),
now(),
1,
1);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(2,
now(),
now(),
1,
2);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(3,
now(),
now(),
2,
2);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(4,
now(),
now(),
2,
11);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(5,
now(),
now(),
2,
13);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(6,
now(),
now(),
2,
14);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(7,
now(),
now(),
2,
15);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(8,
now(),
now(),
3,
16);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(9,
now(),
now(),
3,
17);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(10,
now(),
now(),
4,
9);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(11,
now(),
now(),
4,
10);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(12,
now(),
now(),
4,
12);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(13,
now(),
now(),
5,
2);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(14,
now(),
now(),
5,
4);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(15,
now(),
now(),
5,
5);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(16,
now(),
now(),
6,
3);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(17,
now(),
now(),
6,
6);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(18,
now(),
now(),
6,
8);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(19,
now(),
now(),
6,
1);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(20,
now(),
now(),
7,
5);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(21,
now(),
now(),
7,
8);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(22,
now(),
now(),
7,
28);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(23,
now(),
now(),
7,
34);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(24,
now(),
now(),
8,
5);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(25,
now(),
now(),
8,
29);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(26,
now(),
now(),
9,
5);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(27,
now(),
now(),
9,
28);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(28,
now(),
now(),
10,
34);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(29,
now(),
now(),
10,
5);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(30,
now(),
now(),
10,
31);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(31,
now(),
now(),
11,
31);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(32,
now(),
now(),
11,
27);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(33,
now(),
now(),
12,
31);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(34,
now(),
now(),
12,
24);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(35,
now(),
now(),
12,
25);

INSERT INTO `groupings`.`group_hashtag_mapping`
(`group_hashtag_mapping_id`,
`created_at`,
`updated_at`,
`group_id`,
`hashtag_id`)
VALUES
(36,
now(),
now(),
12,
26);

INSERT INTO `groupings`.`user_friend_mapping`
(`user_friend_mapping_id`,
`grouping_user_id`,
`friend_id`,
`created_at`,
`updated_at`)
VALUES
(1,
'34a416b2-b2c4-11ea-93c1-1a2157509cca',
'34a416b2-b2c4-11ea-93c1-1a2157a08c99',
now(),
now());

INSERT INTO `groupings`.`user_friend_mapping`
(`user_friend_mapping_id`,
`grouping_user_id`,
`friend_id`,
`created_at`,
`updated_at`)
VALUES
(2,
'34a416b2-b2c4-11ea-93c1-1a2157509cca',
'34a416b2-b2c4-11ea-93c1-1a21b75081c3',
now(),
now());

INSERT INTO `groupings`.`user_friend_mapping`
(`user_friend_mapping_id`,
`grouping_user_id`,
`friend_id`,
`created_at`,
`updated_at`)
VALUES
(3,
'34a416b2-b2c4-11ea-93c1-1a2157509cca',
'34a416b2-b2c4-11ea-93c2-1ad1575083c4',
now(),
now());

INSERT INTO `groupings`.`user_friend_mapping`
(`user_friend_mapping_id`,
`grouping_user_id`,
`friend_id`,
`created_at`,
`updated_at`)
VALUES
(4,
'34a416b2-b2c4-11ea-93c2-1ad1575083c4',
'34a416b2-b2c4-11ea-93c1-1a2157509cca',
now(),
now());

INSERT INTO `groupings`.`user_friend_mapping`
(`user_friend_mapping_id`,
`grouping_user_id`,
`friend_id`,
`created_at`,
`updated_at`)
VALUES
(5,
'34a416b2-b2c4-11ea-93c2-1ad1575083c4',
'34a416b2-b2c4-11ea-93c1-1a21b75081c3',
now(),
now());

INSERT INTO `groupings`.`user_friend_mapping`
(`user_friend_mapping_id`,
`grouping_user_id`,
`friend_id`,
`created_at`,
`updated_at`)
VALUES
(6,
'34a416b2-b2c4-11ea-93c1-1a2157508cce',
'34a416b2-b2c4-11ea-93c1-1a2157509cca',
now(),
now());

INSERT INTO `groupings`.`user_friend_mapping`
(`user_friend_mapping_id`,
`grouping_user_id`,
`friend_id`,
`created_at`,
`updated_at`)
VALUES
(7,
'34a416b2-b2c4-11ea-93c1-1a2157508cce',
'34a416b2-b2c4-11ea-93c1-1a2157a08c99',
now(),
now());

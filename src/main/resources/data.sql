
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

INSERT INTO `groupings`.`crew`
(`group_id`,
`created_at`,
`updated_at`,
`available_gender`,
`description`,
`max_user_age`,
`max_user_number`,
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
'40',
'30',
'15',
'my home',
'523.23',
'424.32',
'코린이 탈출 가즈아~');

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




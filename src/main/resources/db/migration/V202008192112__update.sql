
-- 教室表增加teaching_center_id字段
ALTER TABLE `tb_project_card_project_suite`  ADD id varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL;

-- 更新字段信息
UPDATE tb_project_card_project_suite set id=UUID() ;

-- 设置id为主键
ALTER TABLE `tb_project_card_project_suite` ADD PRIMARY KEY (`id`);


-- 教学班新增学年学期字段
ALTER TABLE tb_group ADD COLUMN term int(11) NULL DEFAULT NULL;
ALTER TABLE tb_group ADD COLUMN school_year varchar(255) NULL DEFAULT NULL;
-- 开课表新增辅助名称和通知单类别  药科大期末评教对接使用
ALTER TABLE tb_course_table ADD COLUMN notice_category varchar(255) NULL DEFAULT NULL;
ALTER TABLE tb_course_table ADD COLUMN auxiliary_name varchar(255) NULL DEFAULT NULL;
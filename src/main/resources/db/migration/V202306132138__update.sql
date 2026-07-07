-- 开课表新增教师工号字段
ALTER TABLE tb_course_table ADD COLUMN teacher_no varchar(255) NULL DEFAULT NULL;


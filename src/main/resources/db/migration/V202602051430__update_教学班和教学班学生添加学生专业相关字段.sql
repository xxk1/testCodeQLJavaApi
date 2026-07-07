-- 为 tb_group_member 添加学生学院字段

alter table `tb_group_member` add column student_major_id varchar(255)  DEFAULT NULL COMMENT '学生学院id';

alter table `tb_group_member` add column student_major_name varchar(255)  DEFAULT NULL COMMENT '学生学院名称';

-- 为 tb_group 添加学生学院字段

alter table `tb_group` add column student_major_ids LONGTEXT DEFAULT NULL COMMENT '学生学院ids(多个用逗号分隔)';

alter table `tb_group` add column student_major_names LONGTEXT DEFAULT NULL COMMENT '学生学院名称(多个用逗号分隔)';


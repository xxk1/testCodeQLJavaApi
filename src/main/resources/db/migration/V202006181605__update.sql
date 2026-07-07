-- 课表明细表添加字段:课程类型Id,课程类型名称
alter table tb_course_table_detail add column course_type_id int(11);
alter table tb_course_table_detail add column course_type_name varchar(255);

alter table tb_course_table_detail drop column course_type varchar(255);
-- 课程管理资源表修改
alter table tb_course_resource drop column file_size;
alter table tb_course_resource drop column file_type;
alter table tb_course_resource drop column file_path;
alter table tb_course_resource drop column file_real_name;
alter table tb_course_resource drop column file_saved_name;
alter table tb_course_resource drop column inner_ip;
alter table tb_course_resource drop column outer_ip;
alter table tb_course_resource add column resource_size bigint(20) DEFAULT NULL;
alter table tb_course_resource add column resource_name varchar(256) NULL DEFAULT NULL;


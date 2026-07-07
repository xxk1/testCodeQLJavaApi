-- 课程表新增课程管理所需字段
alter table tb_course add column course_leader_id varchar(256) NULL DEFAULT NULL;
alter table tb_course add column course_leader_name varchar(256) NULL DEFAULT NULL;
alter table tb_course add column parent_id varchar(256) NULL DEFAULT NULL;
alter table tb_course add column course_source int(11) DEFAULT 0;
alter table tb_course add column course_material_id varchar(256);


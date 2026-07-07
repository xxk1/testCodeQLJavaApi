-- 修改项目名称字段长度
alter table tb_course_table_detail_project modify column project_name varchar(2500);
alter table tb_project modify column project_name varchar(2500);
alter table tb_project_card modify column project_name varchar(2500);

-- 项目表新增课程id，新增项目种类、项目类别、项目要求、项目类型枚举
alter table tb_project
    add column course_id varchar(255) NULL DEFAULT NULL;
alter table tb_project
    add column project_type int(11) NULL DEFAULT NULL;
alter table tb_project
    add column project_category int(11) NULL DEFAULT NULL;
alter table tb_project
    add column project_claim int(11) NULL DEFAULT NULL;
alter table tb_project
    add column project_genre int(11) NULL DEFAULT NULL;

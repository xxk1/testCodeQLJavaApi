-- 新增来源类型和来源ID字段
alter table tb_course_resource
    add column source_id varchar(255);
alter table tb_course_resource
    add column source_type integer default 0;

update tb_course_resource
set source_type = 0
where source_type is null;
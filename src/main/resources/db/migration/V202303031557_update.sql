-- 新增被引用人ID和姓名
alter table tb_course_resource
    add column cited_user_id varchar(255);
alter table tb_course_resource
    add column cited_user_name varchar(255);

-- 新增复制的资源文件ID
alter table tb_course_resource_file
    add column copied_resource_file_id varchar(255);

-- 新增复制的课程主题ID
alter table tb_course_theme
    add column copied_theme_id varchar(255);

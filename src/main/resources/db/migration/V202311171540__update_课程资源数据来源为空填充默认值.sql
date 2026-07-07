-- 课程资源数据来源为空填充默认值
update tb_course_resource set source_type =0 where source_type is null;
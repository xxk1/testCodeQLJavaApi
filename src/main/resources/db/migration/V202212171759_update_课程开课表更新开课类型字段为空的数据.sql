-- 处理tb_course_table中开课类型为空的数据
update tb_course_table set student_type = 0
where student_type is null;
-- 课表明细表新增课程名称，授课老师名称，授课老师所属学院id，授课老师所属学院名称冗余字段，
alter table tb_course_table_detail
    add column teacher_college_ids varchar(255);
alter table tb_course_table_detail
    add column teacher_college_names varchar(255);
alter table tb_course_table_detail
    add column teacher_names varchar(255);
alter table tb_course_table_detail
    add column course_name varchar(255);

-- 课表明细教师表表新授课老师所属学院id，授课老师所属学院名称冗余字段，
alter table tb_course_table_detail_teacher
    add column teacher_college_name varchar(255);
alter table tb_course_table_detail_teacher
    add column teacher_college_id varchar(255);
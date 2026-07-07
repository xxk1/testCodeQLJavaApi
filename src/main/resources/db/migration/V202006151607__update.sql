-- 增加课表详情  课程/课次状态 0：已上课；1：未上课
alter table tb_course_table_detail
    add column course_status int
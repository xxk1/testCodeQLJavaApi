-- 课表明细表添加字段:课表数据源归属：1.本科生2.研究生3.国教院

alter table tb_course_table_detail ADD column source_data_source varchar(255) default '0';
-- 更新数据源
update tb_course_table_detail a,tb_course_table b set a.source_data_source=b.student_type where a.course_table_id=b.id;
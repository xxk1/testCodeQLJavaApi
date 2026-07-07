-- 课表详情新增节次开始时间字段
alter table tb_course_table_detail
    add column segment_start_time time(0) NULL DEFAULT NULL;

-- 课表详情新增节次结束时间字段
alter table tb_course_table_detail
    add column segment_end_time time(0) NULL DEFAULT NULL;

-- 删除课次名称
alter table tb_course_table_detail
    DROP class_times;

-- 删除课次编号
alter table tb_course_table_detail
    DROP class_code;

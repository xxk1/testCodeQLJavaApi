-- 课表详情新增字段
ALTER TABLE `tb_course_table_detail`
    add column `course_kind` int(10) default '0';

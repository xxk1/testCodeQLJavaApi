-- 修改教学包版本时间默认值为NULL
ALTER TABLE tb_course_teaching_package MODIFY COLUMN vision_time datetime DEFAULT NULL;

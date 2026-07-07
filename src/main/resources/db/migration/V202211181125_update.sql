-- 修改教学包简介长度
ALTER TABLE tb_course_teaching_package MODIFY COLUMN teaching_package_introduction  varchar(2500);

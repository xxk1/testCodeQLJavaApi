-- 新增课程资源引用次数字段
alter table tb_course_resource add column resource_references int(11) DEFAULT NULL;

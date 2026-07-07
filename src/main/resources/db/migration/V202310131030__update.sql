-- 课程资源添加他人引用次数字段
alter table tb_course_resource add column resource_other_references int(11) DEFAULT 0 AFTER `resource_references`;
-- 课程资源引用次数字段默认为0
update tb_course_resource set resource_references=0 where resource_references is null;
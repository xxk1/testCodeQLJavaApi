-- 修改课程介绍分类内容字段属性
alter table tb_course_introduction MODIFY COLUMN classification_content LONGTEXT DEFAULT null;

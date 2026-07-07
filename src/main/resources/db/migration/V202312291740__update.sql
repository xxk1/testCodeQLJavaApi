-- 修改课程结构名称字段长度
ALTER TABLE tb_course_structure MODIFY COLUMN course_structure_name  varchar(600);
-- 修改知识结构名称字段长度
ALTER TABLE tb_course_knowledge_structure MODIFY COLUMN content  varchar(600);
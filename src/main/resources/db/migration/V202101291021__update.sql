-- 开课表新增字段区分本科生开课与研究生开课
ALTER TABLE tb_course_table ADD COLUMN student_type INT(11) DEFAULT 0;




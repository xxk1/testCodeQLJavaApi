-- 教学班学生添加学生身份
ALTER TABLE tb_group_member ADD COLUMN student_identity INT(11) DEFAULT 0;

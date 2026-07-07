-- 修改班级组成和班级昵称长度
ALTER TABLE tb_group MODIFY COLUMN class_nick_name  varchar(2500);
ALTER TABLE tb_group MODIFY COLUMN class_composition_name  varchar(2500);
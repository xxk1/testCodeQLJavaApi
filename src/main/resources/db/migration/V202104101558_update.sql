-- 增加班级昵称字段
alter table tb_group add column `class_nick_name`  varchar(50) NULL ;
-- 增加班级组成名称字段
alter table tb_group add column `class_composition_name`  varchar(100) NULL ;

-- 把班级名称初始化班级昵称
update tb_group set class_nick_name=group_name where 1=1;
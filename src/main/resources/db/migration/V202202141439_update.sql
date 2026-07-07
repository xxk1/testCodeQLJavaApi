-- 小组方案表中新增小组方案类型
alter table tb_class_grouping_scheme
    add column scheme_type integer;

-- 将原有小组方案类型改为普通小组方案
update tb_class_grouping_scheme
set scheme_type=0;

-- 小组方案分组中添加负责人相关字段脚本
alter table tb_class_grouping
    add column responsible_teacher_id varchar(255);
alter table tb_class_grouping
    add column responsible_teacher_name varchar(255);
alter table tb_class_grouping
    add column responsible_teacher_no varchar(255);

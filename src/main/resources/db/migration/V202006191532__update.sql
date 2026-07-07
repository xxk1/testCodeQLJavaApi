-- 组表添加字段:分组状态,组类型字段
alter table tb_group add column source int(11);
alter table tb_group add column group_status int(11);
-- 组表添加字段:分组状态,组类型字段
alter table tb_group_member add column group_member_status int(11);
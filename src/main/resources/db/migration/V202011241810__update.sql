
-- 项目卡添加创建时间
alter table tb_project_card
    add column create_time datetime(0) NULL DEFAULT NULL;

-- 项目卡添加更新时间
alter table tb_project_card
    add column modify_time datetime(0) NULL DEFAULT NULL;

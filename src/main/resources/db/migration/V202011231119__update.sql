
-- 项目卡套件创建时间
alter table tb_project_suite
    add column create_time datetime(0) NULL DEFAULT NULL;

-- 项目卡套件更新时间
alter table tb_project_suite
    add column modify_time datetime(0) NULL DEFAULT NULL;

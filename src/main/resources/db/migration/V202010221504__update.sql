-- 项目归属新增归属编号字段
alter table `tb_project_attribute`
    add column data_code varchar(255) NULL DEFAULT NULL;
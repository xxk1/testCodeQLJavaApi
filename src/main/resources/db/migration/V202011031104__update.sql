-- 组学生表新增学生来源字段
alter table `tb_group_member`
    add column source int(10) NULL DEFAULT NULL;
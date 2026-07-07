-- 行政班信息表新增辅导员信息
-- 南医大已执行
alter table tb_administrative_class add column counsellor_id varchar(255);
alter table tb_administrative_class add column counsellor_no varchar(255);
alter table tb_administrative_class add column counsellor_name varchar(255);
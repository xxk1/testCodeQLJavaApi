-- 课程表增加使用状态字段脚本
alter table tb_course add column use_state bit(1) DEFAULT 1;
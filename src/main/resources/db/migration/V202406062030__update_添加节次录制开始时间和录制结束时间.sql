-- 添加节次录制开始时间和录制结束时间
alter table tb_segment add column record_start_time varchar(255);

alter table tb_segment add column record_end_time varchar(255);

-- 录制开始时间和录制结束时间初始化为节次开始时间和结束时间
update tb_segment set record_start_time=start_time,record_end_time=end_time;
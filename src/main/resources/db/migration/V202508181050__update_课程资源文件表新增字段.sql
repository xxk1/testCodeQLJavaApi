
-- AI识别状态（AI识别状态（0：未识别 1：识别中 2：识别成功 3：识别失败））
alter table tb_course_resource_file add column ai_identify_status int(11)  DEFAULT 0 ;


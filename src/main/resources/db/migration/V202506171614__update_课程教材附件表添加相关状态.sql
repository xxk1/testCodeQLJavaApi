
-- AI识别状态（0：未识别 1：识别中 2：识别成功 3：识别失败）
alter table tb_teaching_material_file add column ocr_status int(11)  DEFAULT 0 ;


-- ai生成题目状态（0：未识别 1：识别中 2：识别成功 3：识别失败）
alter table tb_teaching_material_file add column question_generate_status int(11)  DEFAULT 0 ;
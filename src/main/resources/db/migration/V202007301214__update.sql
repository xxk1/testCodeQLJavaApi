-- 项目卡新增项目名称字段
ALTER TABLE   `tb_project_card` add column `project_name` varchar(255);
-- 项目卡新增课程名称字段
ALTER TABLE   `tb_project_card` add column `course_name` varchar(255);
-- 项目卡新增课程id字段
ALTER TABLE   `tb_project_card` add column  `course_id` varchar(255);
-- 项目套件新增项目套件明细（所关联的所有套件信息）
ALTER TABLE   `tb_project_suite` add column project_suite_detail varchar(255);
-- 项目归属新增归属名称
ALTER TABLE `tb_project_attribute` add COLUMN	 `data_name` varchar(255);
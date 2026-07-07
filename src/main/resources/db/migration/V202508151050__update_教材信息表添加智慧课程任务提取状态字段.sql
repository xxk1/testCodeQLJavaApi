-- 课程教材附件表添加智慧课程任务提取状态
ALTER TABLE `tb_teaching_material_file` ADD COLUMN `wisdom_course_task_collect_status` int(11) DEFAULT 0 ;

-- 课程建设教材内容表添加智慧课程任务提取状态
ALTER TABLE `tb_teaching_material_file_content` ADD COLUMN `wisdom_course_task_collect_status` int(11) DEFAULT 0 ;
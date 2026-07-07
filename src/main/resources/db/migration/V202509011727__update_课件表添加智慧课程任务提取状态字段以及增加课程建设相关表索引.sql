-- 课程课件附件表添加智慧课程任务提取状态
ALTER TABLE `tb_course_resource_file`
    ADD COLUMN `wisdom_course_task_collect_status` int(11) DEFAULT 0;

-- 课程课件附件文件表添加智慧课程任务提取状态
ALTER TABLE `tb_course_resource_file_content`
    ADD COLUMN `wisdom_course_task_collect_status` int(11) DEFAULT 0;

CREATE INDEX idx_structure_status ON tb_course_structure (structure_status);

CREATE INDEX idx_resource_status ON tb_course_resource (resource_status);

CREATE INDEX idx_resource_status ON tb_course_resource_file (resource_status);


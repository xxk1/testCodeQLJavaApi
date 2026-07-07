ALTER TABLE `tb_course_resource`
    ADD COLUMN `vision_number` int(11) NULL DEFAULT 0 AFTER `resource_references`;

ALTER TABLE `tb_course_resource_file`
    ADD COLUMN `vision_number` int(11) NULL DEFAULT 0 AFTER `resource_status`;
ALTER TABLE `tb_course_resource`
    ADD COLUMN `course_id` varchar(255) NULL AFTER `resource_references`;

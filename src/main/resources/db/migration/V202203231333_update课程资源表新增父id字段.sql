
ALTER TABLE `tb_course_resource_file`
    ADD COLUMN `parent_id` varchar(255) NULL DEFAULT null AFTER `vision_number`;


ALTER TABLE `tb_course_resource`
    ADD COLUMN `parent_id` varchar(255) NULL DEFAULT null AFTER `vision_number`;


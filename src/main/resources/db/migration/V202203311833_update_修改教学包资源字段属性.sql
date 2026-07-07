alter table tb_course_teaching_package modify column course_resource_id text;
alter table tb_course_teaching_package modify column course_resource_file_id text;
alter table tb_course_teaching_package modify column course_structure_id text;
alter table tb_course_teaching_package modify column teaching_package_file_id text;

-- 新增教学包引用表
DROP TABLE IF EXISTS `tb_course_teaching_package_resource`;
CREATE TABLE `tb_course_teaching_package_resource` (
                                                       `id` varchar(255) NOT NULL,
                                                       `user_id` varchar(255) DEFAULT NULL,
                                                       `course_teaching_package_id` varchar(255) DEFAULT NULL,
                                                       `course_id` varchar(255) DEFAULT NULL,
                                                       `resource_id` varchar(255) DEFAULT NULL,
                                                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
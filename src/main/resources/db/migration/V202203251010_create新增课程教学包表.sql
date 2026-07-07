-- 新增课程教学包表
DROP TABLE IF EXISTS `tb_course_teaching_package`;
CREATE TABLE `tb_course_teaching_package` (
                                              `id` varchar(255) NOT NULL,
                                              `create_time` datetime DEFAULT NULL,
                                              `creator_id` varchar(255) DEFAULT NULL,
                                              `creator_name` varchar(255) DEFAULT NULL,
                                              `modifier_id` varchar(255) DEFAULT NULL,
                                              `modifier_name` varchar(255) DEFAULT NULL,
                                              `modify_time` datetime DEFAULT NULL,
                                              `course_resource_id` varchar(500) DEFAULT NULL,
                                              `course_resource_file_id` varchar(500) DEFAULT NULL,
                                              `course_structure_id` varchar(500) DEFAULT NULL,
                                              `teaching_package_name` varchar(255) DEFAULT NULL,
                                              `teaching_package_introduction` varchar(255) DEFAULT NULL,
                                              `teaching_package_file_id` varchar(255) DEFAULT NULL,
                                              `file_path` varchar(255) DEFAULT NULL,
                                              `inner_ip` varchar(255) DEFAULT NULL,
                                              `outer_ip` varchar(255) DEFAULT NULL,
                                              `label_id` varchar(500) DEFAULT NULL,
                                              `label_name` varchar(255) DEFAULT NULL,
                                              `course_version_id` varchar(255) DEFAULT NULL,
                                              `course_id` varchar(255) DEFAULT NULL,
                                              `course_name` varchar(255) DEFAULT NULL,
                                              `vision_number` int(11) DEFAULT '0',
                                              `vision_time` datetime DEFAULT '0000-00-00 00:00:00',
                                              `quote_num` int(11) DEFAULT NULL,
                                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `tb_course_objective` (
  `id` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `creator_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `modifier_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `modifier_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `content` mediumtext COLLATE utf8mb4_bin,
  `course_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `creator_no` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `modifier_no` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `name` varchar(2550) COLLATE utf8mb4_bin DEFAULT NULL,
  `show_order` int(11) DEFAULT NULL,
  `use_state` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_course_id` (`course_id`),
  KEY `idx_course_id_use_state` (`course_id`,`use_state`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
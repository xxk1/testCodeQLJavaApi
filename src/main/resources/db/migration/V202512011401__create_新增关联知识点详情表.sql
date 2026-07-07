CREATE TABLE `tb_course_objective_kg_node` (
  `id` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `creator_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `modifier_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `modifier_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `course_knowledge_graph_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `course_objective_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `creator_no` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `modifier_no` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `related_knowledge_point_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_objective_knowledge` (`course_objective_id`,`course_knowledge_graph_id`,`related_knowledge_point_id`),
  KEY `idx_course_objective_id_course_knowledge_graph_id` (`course_knowledge_graph_id`,`course_objective_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
-- 课程知识图谱AI生成任务表
CREATE TABLE `tb_course_knowledge_graph_ai_generate_task`  (
                                             `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
                                             `creator_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
                                             `creator_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
                                             `create_time` datetime NULL DEFAULT NULL,
                                             `modifier_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
                                             `modifier_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
                                             `modify_time` datetime NULL DEFAULT NULL,
                                             `course_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '课程id',
                                             `school_year` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '学年',
                                             `term` int(11) NULL DEFAULT NULL COMMENT '课程id',
                                             `group_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '教学班id',
                                             `graph_ai_generate_status` int(11) NULL DEFAULT NULL COMMENT '知识图谱智能生成任务状态',
                                             `remark` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
                                             `graph_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '知识图谱智能生成任务结果',
                                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;
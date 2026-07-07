CREATE TABLE `tb_course_knowledge_graph_node_video_info_text`  (
                                        `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                        `creator_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人id',
                                        `creator_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人名称',
                                        `create_time` datetime NOT NULL COMMENT '创建时间',
                                        `course_knowledge_graph_domain_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程知识图谱id',
                                        `text_data_source` int NULL DEFAULT NULL COMMENT '知识点关联视频数据来源',
                                        `video_info_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频信息id',
                                        `video_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频日期',
                                        `school_year` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学年',
                                        `term` int NULL DEFAULT NULL COMMENT '学期',
                                        `course_table_detail_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课表id',
                                        `segment` int NULL DEFAULT NULL COMMENT '节次',
                                        `course_table_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开课id',
                                        `group_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教学班id',
                                        `course_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程id',
                                        `teacher_ids` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课表明细老师id(多个时\",\"分割)',
                                        `teacher_nos` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课表明细老师工号(多个时\",\"分割)',
                                        `teacher_names` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课表明细老师名称(多个时\",\"分割)',
                                        `inner_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内网ip',
                                        `outer_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '外网ip',
                                        `thumbnail_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缩略图路径',
                                        `start_timestamp` double NULL DEFAULT NULL COMMENT '开始时间戳',
                                        `end_timestamp` double NULL DEFAULT NULL COMMENT '结束时间戳',
                                        `knowledge_node_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '知识点id',
                                        `knowledge_node_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '知识点名称',
                                        `text_content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文本内容',
                                        PRIMARY KEY (`id`) USING BTREE,
                                        INDEX `index_course_knowledge_graph_domain_id`(`course_knowledge_graph_domain_id` ASC) USING BTREE,
                                        CONSTRAINT `foreign_key_course_knowledge_graph_domain_id` FOREIGN KEY (`course_knowledge_graph_domain_id`) REFERENCES `tb_course_knowledge_graph_domain` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- 创建course_knowledge_graph_domain_id索引
CREATE INDEX idx_course_knowledge_graph_domain_id ON tb_course_knowledge_graph_node_video_info_text (course_knowledge_graph_domain_id);
-- 创建course_id索引
CREATE INDEX idx_course_id ON tb_course_knowledge_graph_node_video_info_text (course_id);
-- 创建group_id索引
CREATE INDEX idx_group_id ON tb_course_knowledge_graph_node_video_info_text (group_id);
-- 创建knowledge_node_id索引
CREATE INDEX idx_knowledge_node_id ON tb_course_knowledge_graph_node_video_info_text (knowledge_node_id);
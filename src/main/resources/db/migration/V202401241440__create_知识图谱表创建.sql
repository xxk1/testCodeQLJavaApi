-- 创建课程知识图谱领域

CREATE TABLE `tb_course_knowledge_graph_domain`  (
                                                     `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                                     `create_time` datetime(0) NULL DEFAULT NULL,
                                                     `creator_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                     `creator_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                     `modifier_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                     `modifier_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                     `modify_time` datetime(0) NULL DEFAULT NULL,
                                                     `course_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                     `course_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                     `node_count` int(11) NULL DEFAULT NULL,
                                                     `ship_count` int(11) NULL DEFAULT NULL,
                                                     `status` bit(1) NULL DEFAULT NULL,
                                                     `course_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                     `course_version_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                     PRIMARY KEY (`id`) USING BTREE,
                                                     INDEX `FKbixv5nwwaxgvt3hkx6acog92d`(`course_id`) USING BTREE,
                                                     INDEX `FK5kvxc8n7r43uofqn9cmi72eqp`(`course_version_id`) USING BTREE,
                                                     CONSTRAINT `FK5kvxc8n7r43uofqn9cmi72eqp` FOREIGN KEY (`course_version_id`) REFERENCES `tb_course_version` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                                     CONSTRAINT `FKbixv5nwwaxgvt3hkx6acog92d` FOREIGN KEY (`course_id`) REFERENCES `tb_course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- 创建知识图谱节点资源表

CREATE TABLE `tb_course_knowledge_graph_node_resource`  (
                                                            `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                                            `create_time` datetime(0) NULL DEFAULT NULL,
                                                            `creator_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                            `creator_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                            `modifier_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                            `modifier_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                            `modify_time` datetime(0) NULL DEFAULT NULL,
                                                            `graph_node_resource_status` int(11) NULL DEFAULT NULL,
                                                            `knowledge_node_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                            `resource_content_num` int(11) NULL DEFAULT NULL,
                                                            `resource_detail_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                            `resource_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                            `resource_size` bigint(20) NULL DEFAULT NULL,
                                                            `resource_type` int(11) NULL DEFAULT NULL,
                                                            `sort` int(11) NULL DEFAULT NULL,
                                                            `course_knowledge_graph_domain_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                            PRIMARY KEY (`id`) USING BTREE,
                                                            INDEX `FK5526li4rwjtemr896rcfjp69i`(`course_knowledge_graph_domain_id`) USING BTREE,
                                                            CONSTRAINT `FK5526li4rwjtemr896rcfjp69i` FOREIGN KEY (`course_knowledge_graph_domain_id`) REFERENCES `tb_course_knowledge_graph_domain` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- 创建知识图谱节点资源附件表

CREATE TABLE `tb_course_knowledge_graph_node_resource_file`  (
                                                                 `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                                                 `create_time` datetime(0) NULL DEFAULT NULL,
                                                                 `creator_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                 `creator_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                 `modifier_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                 `modifier_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                 `modify_time` datetime(0) NULL DEFAULT NULL,
                                                                 `file_display_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                 `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                 `file_real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                 `file_size` bigint(20) NULL DEFAULT NULL,
                                                                 `file_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                 `inner_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                 `outer_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                 `transcode_file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                 `transcode_file_saved_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                 `transcode_file_size` bigint(20) NULL DEFAULT NULL,
                                                                 `transcode_file_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                 `transcode_inner_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                 `transcode_message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                 `transcode_outer_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                 `transcode_status` int(11) NULL DEFAULT NULL,
                                                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- tb_course_knowledge_graph_node_resource 创建knowledge_node_id索引
CREATE INDEX `index_knowledge_node_id` ON tb_course_knowledge_graph_node_resource(knowledge_node_id);
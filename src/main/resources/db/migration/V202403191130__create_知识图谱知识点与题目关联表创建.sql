-- 知识图谱知识点与题目关联表创建
CREATE TABLE `tb_course_knowledge_graph_node_question`  (
            `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
            `creator_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
            `creator_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
            `create_time` datetime(0) NULL DEFAULT NULL,
            `course_knowledge_graph_domain_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
            `knowledge_node_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
            `question_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
            `sort` int(11) NULL DEFAULT NULL,
            PRIMARY KEY (`id`) USING BTREE,
            INDEX `FKacg0qhoicswsdg45cmifm7gjr`(`course_knowledge_graph_domain_id`) USING BTREE,
            CONSTRAINT `FKacg0qhoicswsdg45cmifm7gjr` FOREIGN KEY (`course_knowledge_graph_domain_id`)
                REFERENCES `tb_course_knowledge_graph_domain` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
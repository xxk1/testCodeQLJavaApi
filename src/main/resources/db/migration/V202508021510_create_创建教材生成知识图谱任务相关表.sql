CREATE TABLE `tb_kg_gen_task_master`
(
    `id`               varchar(255) COLLATE utf8mb4_bin NOT NULL,
    `create_time`      datetime                          DEFAULT NULL,
    `creator_id`       varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `creator_name`     varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `modifier_id`      varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `modifier_name`    varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `modify_time`      datetime                          DEFAULT NULL,
    `course_id`        varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `finish_time`      datetime                          DEFAULT NULL,
    `material_file_id` varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `remark`           varchar(2550) COLLATE utf8mb4_bin DEFAULT NULL,
    `start_time`       datetime                          DEFAULT NULL,
    `status`           int(11) DEFAULT NULL,
    `type`             int(11) DEFAULT NULL,
    `course_name`      varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `group_id`         varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `school_year`      varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `term`             int(11) DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    KEY                `index_course_id` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;


CREATE TABLE `tb_kg_gen_task_sub`
(
    `id`            varchar(255) COLLATE utf8mb4_bin NOT NULL,
    `create_time`   datetime                          DEFAULT NULL,
    `creator_id`    varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `creator_name`  varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `modifier_id`   varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `modifier_name` varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `modify_time`   datetime                          DEFAULT NULL,
    `finish_time`   datetime                          DEFAULT NULL,
    `master_id`     varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `remark`        varchar(2550) COLLATE utf8mb4_bin DEFAULT NULL,
    `start_time`    datetime                          DEFAULT NULL,
    `status`        int(11) DEFAULT NULL,
    `step_code`     int(11) DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

CREATE TABLE `tb_kg_gen_task_node`
(
    `id`              varchar(255) COLLATE utf8mb4_bin NOT NULL,
    `create_time`     datetime                          DEFAULT NULL,
    `creator_id`      varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `creator_name`    varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `modifier_id`     varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `modifier_name`   varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `modify_time`     datetime                          DEFAULT NULL,
    `file_content_id` varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `inner_url`       varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `outer_url`       varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `path`            varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `remark`          varchar(2550) COLLATE utf8mb4_bin DEFAULT NULL,
    `status`          int(11) DEFAULT NULL,
    `sub_task_id`     varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `send_time`       datetime                          DEFAULT NULL,
    `receive_time`    datetime                          DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    KEY               `index_sub_task_id` (`sub_task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

CREATE TABLE `tb_kg_gen_task_node_detail`
(
    `id`              varchar(255) COLLATE utf8mb4_bin NOT NULL,
    `create_time`     datetime                          DEFAULT NULL,
    `creator_id`      varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `creator_name`    varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `modifier_id`     varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `modifier_name`   varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `modify_time`     datetime                          DEFAULT NULL,
    `file_content_id` varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `inner_url`       varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `node_id`         varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `outer_url`       varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `path`            varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `remark`          varchar(2550) COLLATE utf8mb4_bin DEFAULT NULL,
    `status`          int(11) DEFAULT NULL,
    `sub_task_id`     varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `receive_time`    datetime                          DEFAULT NULL,
    `send_time`       datetime                          DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    KEY               `index_sub_task_id` (`sub_task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

CREATE TABLE `tb_kg_gen_node`
(
    `id`                 varchar(255) COLLATE utf8mb4_bin NOT NULL,
    `create_time`        datetime                         DEFAULT NULL,
    `creator_id`         varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `creator_name`       varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `modifier_id`        varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `modifier_name`      varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `modify_time`        datetime                         DEFAULT NULL,
    `content`            mediumtext COLLATE utf8mb4_bin,
    `level`              int(11) DEFAULT NULL,
    `master_task_id`     varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `node_name`          varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `parent_node_id`     varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `sub_task_id`        varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `chapter_content_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `node_order`         int(11) DEFAULT NULL,
    `level_type`         int(11) DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    KEY                  `index_sub_task_id` (`sub_task_id`),
    KEY                  `idx_parent_node_id` (`parent_node_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

CREATE TABLE `tb_kg_gen_node_resource_related_statistics`
(
    `id`                                     varchar(255) COLLATE utf8mb4_bin NOT NULL,
    `create_time`                            datetime                          DEFAULT NULL,
    `creator_id`                             varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `creator_name`                           varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `modifier_id`                            varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `modifier_name`                          varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `modify_time`                            datetime                          DEFAULT NULL,
    `course_question_num`                    int(11) DEFAULT NULL,
    `course_question_teacher_names`          varchar(5000) COLLATE utf8mb4_bin DEFAULT NULL,
    `master_task_id`                         varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `related_question_num`                   int(11) DEFAULT NULL,
    `related_video_clip_num`                 int(11) DEFAULT NULL,
    `sub_task_id`                            varchar(255) COLLATE utf8mb4_bin  DEFAULT NULL,
    `support_voice_tran_video_num`           int(11) DEFAULT NULL,
    `support_voice_tran_video_teacher_names` varchar(5000) COLLATE utf8mb4_bin DEFAULT NULL,
    `total_video_num`                        int(11) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY                                      `index_master_task_id` (`master_task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `tb_kg_gen_node_question`
(
    `id`                    varchar(255) COLLATE utf8mb4_bin NOT NULL,
    `create_time`           datetime                         DEFAULT NULL,
    `creator_id`            varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `creator_name`          varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `modifier_id`           varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `modifier_name`         varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `modify_time`           datetime                         DEFAULT NULL,
    `node_id`               varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `question_id`           varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `sort`                  int(11) DEFAULT NULL,
    `master_task_id`        varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `sub_task_id`           varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `question_creator_id`   varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `question_creator_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    KEY                     `index_node_id` (`node_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

CREATE TABLE `tb_kg_gen_node_video_info`
(
    `id`                     varchar(255) COLLATE utf8mb4_bin NOT NULL,
    `create_time`            datetime                         DEFAULT NULL,
    `creator_id`             varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `creator_name`           varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `modifier_id`            varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `modifier_name`          varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `modify_time`            datetime                         DEFAULT NULL,
    `course_id`              varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `course_table_detail_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `course_table_id`        varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `end_timestamp` double DEFAULT NULL,
    `group_id`               varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `inner_ip`               varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `knowledge_node_id`      varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `knowledge_node_name`    varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `outer_ip`               varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `school_year`            varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `segment`                int(11) DEFAULT NULL,
    `start_timestamp` double DEFAULT NULL,
    `teacher_ids`            varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `teacher_names`          varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `teacher_nos`            varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `term`                   int(11) DEFAULT NULL,
    `text_content`           longtext COLLATE utf8mb4_bin,
    `text_data_source`       int(11) DEFAULT NULL,
    `thumbnail_path`         varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `video_date`             varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `video_info_id`          varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `master_task_id`         varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `sub_task_id`            varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    KEY                      `index_master_task_id` (`master_task_id`),
    KEY                      `index_knowledge_node_id` (`knowledge_node_id`),
    KEY                      `index_video_info_id` (`video_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

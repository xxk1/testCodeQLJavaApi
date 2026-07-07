CREATE TABLE `tb_wisdom_course`
(
    `id`            varchar(255) COLLATE utf8mb4_bin NOT NULL,
    `create_time`   datetime                         DEFAULT NULL,
    `creator_id`    varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `creator_name`  varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `modifier_id`   varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `modifier_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `modify_time`   datetime                         DEFAULT NULL,
    `course_id`     varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `course_name`   varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `creator_no`    varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `modify_no`     varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `use_state`     bit(1)                           NOT NULL,
    PRIMARY KEY (`id`),
    KEY             `idx_course_id` (`course_id`),
    KEY             `idx_use_state` (`use_state`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `tb_wisdom_course_history_term`
(
    `id`                  varchar(255) COLLATE utf8mb4_bin NOT NULL,
    `create_time`         datetime                         DEFAULT NULL,
    `creator_id`          varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `creator_name`        varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `school_year`         varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `term`                int(11) DEFAULT NULL,
    `term_id`             varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `wisdom_course_id`    varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `history_task_status` int(11) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY                   `FKklse423anun2kuhc8vv7kikg2` (`wisdom_course_id`),
    CONSTRAINT `FKklse423anun2kuhc8vv7kikg2` FOREIGN KEY (`wisdom_course_id`) REFERENCES `tb_wisdom_course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `tb_wisdom_course_task`
(
    `id`               varchar(255) COLLATE utf8mb4_bin NOT NULL,
    `create_time`      datetime                         DEFAULT NULL,
    `creator_id`       varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `creator_name`     varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `modifier_id`      varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `modifier_name`    varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `modify_time`      datetime                         DEFAULT NULL,
    `resource_id`      varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `resource_name`    varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `resource_type`    int(11) DEFAULT NULL,
    `school_year`      varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `task_status`      int(11) DEFAULT NULL,
    `task_type`        int(11) DEFAULT NULL,
    `task_type_name`   varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `term`             int(11) DEFAULT NULL,
    `wisdom_course_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY                `FKtcj6q17gydjce5ivnooel1cad` (`wisdom_course_id`),
    CONSTRAINT `FKtcj6q17gydjce5ivnooel1cad` FOREIGN KEY (`wisdom_course_id`) REFERENCES `tb_wisdom_course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


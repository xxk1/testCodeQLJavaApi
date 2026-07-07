
CREATE TABLE `tb_experiment_schedule_course_log`
(
    `id`                         varchar(255) COLLATE utf8mb4_bin NOT NULL,
    `create_time`                datetime                         DEFAULT NULL,
    `creator_id`                 varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `creator_name`               varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `experiment_schedule_id`     varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `operation_description`      varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY  `experiment_schedule_id_index` (`experiment_schedule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

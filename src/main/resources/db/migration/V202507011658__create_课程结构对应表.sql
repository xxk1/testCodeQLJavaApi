
CREATE TABLE `tb_course_structure_mapping`
(
    `id`                         varchar(255) COLLATE utf8mb4_bin NOT NULL,
    `create_time`                datetime                         DEFAULT NULL,
    `creator_id`                 varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `creator_name`               varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `modifier_id`                varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `modifier_name`              varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `modify_time`                datetime                         DEFAULT NULL,
    `course_custom_structure_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `course_id`                  varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `course_version_id`          varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `ocr_structure_id`           varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY                          `idx_course_and_version` (`course_id`,`course_version_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

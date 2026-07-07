CREATE TABLE `tb_kg_gen_node_relation`
(
    `id`                 varchar(255) COLLATE utf8mb4_bin NOT NULL,
    `create_time`        datetime                         DEFAULT NULL,
    `new_master_task_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `new_node_id`        varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    `original_node_id`   varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY                  `idx_new_master_task_id` (`new_master_task_id`),
    KEY                  `idx_new_node_id` (`new_node_id`) USING BTREE,
    KEY                  `idx_original_node_id` (`original_node_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

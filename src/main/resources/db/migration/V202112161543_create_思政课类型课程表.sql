CREATE TABLE `tb_course_expansion`
(
    `id`              varchar(255) NOT NULL,
    `create_time`     datetime     DEFAULT NULL,
    `creator_id`      varchar(255) DEFAULT NULL,
    `creator_name`    varchar(255) DEFAULT NULL,
    `course_id`       varchar(255) DEFAULT NULL,
    `expansion_key`   varchar(255) DEFAULT NULL,
    `expansion_value` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
-- 班级分组方案
CREATE TABLE `tb_class_grouping_scheme`
(
    `id`                  varchar(255) NOT NULL,
    `create_time`         datetime      DEFAULT NULL,
    `creator_id`          varchar(255)  DEFAULT NULL,
    `creator_name`        varchar(255)  DEFAULT NULL,
    `group_id`            varchar(255)  DEFAULT NULL,
    `grouped_head_count`  int(11)       DEFAULT NULL,
    `scheme_name`         varchar(255)  DEFAULT NULL,
    `scheme_presentation` varchar(1000) DEFAULT NULL,
    `modifier_id`         varchar(255)  DEFAULT NULL,
    `modifier_name`       varchar(255)  DEFAULT NULL,
    `modify_time`         datetime      DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- 班级分组
CREATE TABLE `tb_class_grouping`
(
    `id`                       varchar(255) NOT NULL,
    `create_time`              datetime     DEFAULT NULL,
    `creator_id`               varchar(255) DEFAULT NULL,
    `creator_name`             varchar(255) DEFAULT NULL,
    `class_grouping_name`      varchar(255) DEFAULT NULL,
    `class_grouping_scheme_id` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK2rfcc4joorikx1efdflm1pnt8` (`class_grouping_scheme_id`),
    CONSTRAINT `FK2rfcc4joorikx1efdflm1pnt8` FOREIGN KEY (`class_grouping_scheme_id`) REFERENCES `tb_class_grouping_scheme` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- 班级分组成员
CREATE TABLE `tb_class_grouping_member`
(
    `id`                        varchar(255) NOT NULL,
    `create_time`               datetime      DEFAULT NULL,
    `creator_id`                varchar(255)  DEFAULT NULL,
    `creator_name`              varchar(255)  DEFAULT NULL,
    `administrative_class_name` varchar(1000) DEFAULT NULL,
    `college_id`                varchar(255)  DEFAULT NULL,
    `college_name`              varchar(255)  DEFAULT NULL,
    `major_id`                  varchar(255)  DEFAULT NULL,
    `major_name`                varchar(255)  DEFAULT NULL,
    `student_id`                varchar(255)  DEFAULT NULL,
    `student_name`              varchar(255)  DEFAULT NULL,
    `student_no`                varchar(255)  DEFAULT NULL,
    `whether_group_leader`      bit(1)        DEFAULT NULL,
    `class_grouping_id`         varchar(255)  DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKsmlq6bsk9xig380x3xon0cnwr` (`class_grouping_id`),
    CONSTRAINT `FKsmlq6bsk9xig380x3xon0cnwr` FOREIGN KEY (`class_grouping_id`) REFERENCES `tb_class_grouping` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
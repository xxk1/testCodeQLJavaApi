CREATE TABLE `tb_experimental_group` (
                                         `id` varchar(255) NOT NULL,
                                         `create_time` datetime DEFAULT NULL,
                                         `creator_id` varchar(255) DEFAULT NULL,
                                         `creator_name` varchar(255) DEFAULT NULL,
                                         `modifier_id` varchar(255) DEFAULT NULL,
                                         `modifier_name` varchar(255) DEFAULT NULL,
                                         `modify_time` datetime DEFAULT NULL,
                                         `group_id` varchar(255) DEFAULT NULL,
                                         `group_leader_id` varchar(255) DEFAULT NULL,
                                         `group_leader_name` varchar(255) DEFAULT NULL,
                                         `group_leader_no` varchar(255) DEFAULT NULL,
                                         `group_name` varchar(255) DEFAULT NULL,
                                         `member_num` int(11) DEFAULT NULL,
                                         `responsible_teacher_id` varchar(255) DEFAULT NULL,
                                         `responsible_teacher_name` varchar(255) DEFAULT NULL,
                                         `responsible_teacher_no` varchar(255) DEFAULT NULL,
                                         `use_state` bit(1) NOT NULL,
                                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `tb_experimental_group_member`
(
    `id`                    varchar(255) NOT NULL,
    `create_time`           datetime     DEFAULT NULL,
    `creator_id`            varchar(255) DEFAULT NULL,
    `creator_name`          varchar(255) DEFAULT NULL,
    `is_group_leader`       bit(1)       NOT NULL,
    `student_id`            varchar(255) DEFAULT NULL,
    `student_name`          varchar(255) DEFAULT NULL,
    `student_no`            varchar(255) DEFAULT NULL,
    `experimental_group_id` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKtni84ch1guqtv59r8paib9ix4` (`experimental_group_id`),
    CONSTRAINT `FKtni84ch1guqtv59r8paib9ix4` FOREIGN KEY (`experimental_group_id`) REFERENCES `tb_experimental_group` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
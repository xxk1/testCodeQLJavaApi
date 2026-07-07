
CREATE TABLE `tb_experiment_original_course_table_detail`  (
                                                               `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                                               `create_time` datetime NULL DEFAULT NULL,
                                                               `creator_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                               `creator_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                               `course_date` date NULL DEFAULT NULL,
                                                               `course_kind` int(11) NULL DEFAULT NULL,
                                                               `course_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                               `course_table_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                               `course_type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                               `segment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                               `segment_end_time` time NULL DEFAULT NULL,
                                                               `segment_start_time` time NULL DEFAULT NULL,
                                                               `source` int(11) NULL DEFAULT NULL,
                                                               `teacher_college_ids` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                               `teacher_college_names` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                               `teacher_names` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                               `week` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                               `week_num` int(11) NULL DEFAULT NULL,
                                                               `course_type_id` int(11) NULL DEFAULT NULL,
                                                               PRIMARY KEY (`id`) USING BTREE,
                                                               INDEX `FKdkapkhc38dr01tpujp911ubwl`(`course_type_id`) USING BTREE,
                                                               CONSTRAINT `FKdkapkhc38dr01tpujp911ubwl` FOREIGN KEY (`course_type_id`) REFERENCES `tb_course_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;



CREATE TABLE `tb_experiment_original_course_table_detail_project`  (
                                                                       `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                                                       `project_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                       `project_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                       `project_name` varchar(2500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                       `experiment_original_course_table_detail_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                       PRIMARY KEY (`id`) USING BTREE,
                                                                       INDEX `FKdow4vyjrj5qcbsrv4lw1nr5on`(`experiment_original_course_table_detail_id`) USING BTREE,
                                                                       CONSTRAINT `FKdow4vyjrj5qcbsrv4lw1nr5on` FOREIGN KEY (`experiment_original_course_table_detail_id`) REFERENCES `tb_experiment_original_course_table_detail` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


CREATE TABLE `tb_experiment_original_course_table_detail_room_user`  (
                                                                         `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                                                         `create_time` datetime NULL DEFAULT NULL,
                                                                         `creator_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                         `creator_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                         `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                         `has_live` bit(1) NOT NULL,
                                                                         `item_teacher_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                         `item_teacher_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                         `item_teacher_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                         `personnel_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                         `room_category_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                         `room_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                         `room_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                         `room_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                         `experiment_original_course_table_detail_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                         PRIMARY KEY (`id`) USING BTREE,
                                                                         INDEX `FKbh4xmfkh3bwqrj76js2v4uvdw`(`experiment_original_course_table_detail_id`) USING BTREE,
                                                                         CONSTRAINT `FKbh4xmfkh3bwqrj76js2v4uvdw` FOREIGN KEY (`experiment_original_course_table_detail_id`) REFERENCES `tb_experiment_original_course_table_detail` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


CREATE TABLE `tb_experiment_original_course_table_detail_segment`  (
                                                                       `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                                                       `experiment_original_course_table_detail_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                       `build_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                       `segment` int(11) NULL DEFAULT NULL,
                                                                       PRIMARY KEY (`id`) USING BTREE,
                                                                       INDEX `FKqdspxb8onrte3cnolk4oo8mi4`(`experiment_original_course_table_detail_id`) USING BTREE,
                                                                       INDEX `FK9eockkp6mrvlck07cd9xo3lin`(`build_id`, `segment`) USING BTREE,
                                                                       CONSTRAINT `FK9eockkp6mrvlck07cd9xo3lin` FOREIGN KEY (`build_id`, `segment`) REFERENCES `tb_segment` (`buildid`, `segment`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                                                       CONSTRAINT `FKqdspxb8onrte3cnolk4oo8mi4` FOREIGN KEY (`experiment_original_course_table_detail_id`) REFERENCES `tb_experiment_original_course_table_detail` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


CREATE TABLE `tb_experiment_original_course_table_detail_teacher`  (
                                                                       `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                                                       `create_time` datetime NULL DEFAULT NULL,
                                                                       `creator_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                       `creator_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                       `teacher_college_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                       `teacher_college_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                       `teacher_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                       `teacher_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                       `teacher_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                       `teacher_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                       `experiment_original_course_table_detail_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                       PRIMARY KEY (`id`) USING BTREE,
                                                                       INDEX `FKhw56ddjkpxknqxtxfmekt0rb2`(`experiment_original_course_table_detail_id`) USING BTREE,
                                                                       CONSTRAINT `FKhw56ddjkpxknqxtxfmekt0rb2` FOREIGN KEY (`experiment_original_course_table_detail_id`) REFERENCES `tb_experiment_original_course_table_detail` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


CREATE TABLE `tb_experiment_schedule_course_table_detail`  (
                                                               `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                                               `create_time` datetime NULL DEFAULT NULL,
                                                               `creator_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                               `creator_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                               `course_date` date NULL DEFAULT NULL,
                                                               `course_kind` int(11) NULL DEFAULT NULL,
                                                               `course_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                               `course_table_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                               `course_type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                               `experiment_original_course_table_detail_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                               `schedule_source` int(11) NULL DEFAULT NULL,
                                                               `schedule_status` int(11) NULL DEFAULT NULL,
                                                               `segment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                               `segment_end_time` time NULL DEFAULT NULL,
                                                               `segment_start_time` time NULL DEFAULT NULL,
                                                               `teacher_college_ids` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                               `teacher_college_names` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                               `teacher_names` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                               `week` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                               `week_num` int(11) NULL DEFAULT NULL,
                                                               `course_type_id` int(11) NULL DEFAULT NULL,
                                                               `course_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                               `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                               `group_name` varchar(2500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                               `group_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                               `course_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                               `modifier_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                                               `modifier_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                                               `modify_time` datetime NULL DEFAULT NULL,
                                                               `entrance_guard_generated_status` int(11) NULL DEFAULT NULL,
                                                               PRIMARY KEY (`id`) USING BTREE,
                                                               INDEX `FKr15xlq3klm94byta6m809dj0q`(`course_type_id`) USING BTREE,
                                                               CONSTRAINT `FKr15xlq3klm94byta6m809dj0q` FOREIGN KEY (`course_type_id`) REFERENCES `tb_course_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


CREATE TABLE `tb_experiment_schedule_course_table_detail_project`  (
                                                                       `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                                                       `project_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                       `project_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                       `project_name` varchar(2500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                       `experiment_schedule_course_table_detail_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                       PRIMARY KEY (`id`) USING BTREE,
                                                                       INDEX `FKfv3755r0ndk8w0vwcbwowphyo`(`experiment_schedule_course_table_detail_id`) USING BTREE,
                                                                       CONSTRAINT `FKfv3755r0ndk8w0vwcbwowphyo` FOREIGN KEY (`experiment_schedule_course_table_detail_id`) REFERENCES `tb_experiment_schedule_course_table_detail` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


CREATE TABLE `tb_experiment_schedule_course_table_detail_room_user`  (
                                                                         `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                                                         `create_time` datetime NULL DEFAULT NULL,
                                                                         `creator_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                         `creator_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                         `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                         `has_live` bit(1) NOT NULL,
                                                                         `item_teacher_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                         `item_teacher_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                         `item_teacher_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                         `personnel_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                         `room_category_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                         `room_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                         `room_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                         `room_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                         `experiment_schedule_course_table_detail_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                         PRIMARY KEY (`id`) USING BTREE,
                                                                         INDEX `FKt4a6msy6ky6jk60hksfnngyke`(`experiment_schedule_course_table_detail_id`) USING BTREE,
                                                                         CONSTRAINT `FKt4a6msy6ky6jk60hksfnngyke` FOREIGN KEY (`experiment_schedule_course_table_detail_id`) REFERENCES `tb_experiment_schedule_course_table_detail` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


CREATE TABLE `tb_experiment_schedule_course_table_detail_segment`  (
                                                                       `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                                                       `experiment_schedule_course_table_detail_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                       `build_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                       `segment` int(11) NULL DEFAULT NULL,
                                                                       PRIMARY KEY (`id`) USING BTREE,
                                                                       INDEX `FKavvn5jkss5geo9mfdlko62f53`(`experiment_schedule_course_table_detail_id`) USING BTREE,
                                                                       INDEX `FK5hp6w6rxh4qjt9b4elhl36vwg`(`build_id`, `segment`) USING BTREE,
                                                                       CONSTRAINT `FK5hp6w6rxh4qjt9b4elhl36vwg` FOREIGN KEY (`build_id`, `segment`) REFERENCES `tb_segment` (`buildid`, `segment`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                                                       CONSTRAINT `FKavvn5jkss5geo9mfdlko62f53` FOREIGN KEY (`experiment_schedule_course_table_detail_id`) REFERENCES `tb_experiment_schedule_course_table_detail` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


CREATE TABLE `tb_experiment_schedule_course_table_detail_teacher`  (
                                                                       `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                                                       `create_time` datetime NULL DEFAULT NULL,
                                                                       `creator_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                       `creator_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                       `teacher_college_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                       `teacher_college_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                       `teacher_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                       `teacher_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                       `teacher_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                       `teacher_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                       `experiment_schedule_course_table_detail_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                                                       PRIMARY KEY (`id`) USING BTREE,
                                                                       INDEX `FK19u3ch130wqamlifqo41sxvgu`(`experiment_schedule_course_table_detail_id`) USING BTREE,
                                                                       CONSTRAINT `FK19u3ch130wqamlifqo41sxvgu` FOREIGN KEY (`experiment_schedule_course_table_detail_id`) REFERENCES `tb_experiment_schedule_course_table_detail` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;



-- 新增专业表
CREATE TABLE `tb_major` (
  `id`              varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `major_name`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `year`            varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `college_id`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- 增加行政班基础信息表
CREATE TABLE `tb_administrative_class`  (
  `id`                  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `class_name`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `college_id`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `college_name`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `grade_major_code`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `major_id`            varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `major_name`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `school_system`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `student_count`       int(255) DEFAULT NULL,
  `year`                varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `source`              int(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- 新增行政班和教学班信息表
CREATE TABLE `tb_administrative_class_and_group` (
   `id`                 varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
   `administrative_class_id`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,	 
   `class_name`         varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
   `create_time`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
   `source`             int(11) DEFAULT NULL,
   `students_count`     int(11) DEFAULT NULL,
   `teacher_id`         varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
   `teacher_name`       varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
   `year`               varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
   `college_id`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
   `group_id`           varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
   `major_id`           varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
   PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
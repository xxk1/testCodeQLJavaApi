-- 新增项目表
CREATE TABLE `tb_project`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `project_code` varchar(255)  CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `project_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `project_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `creator_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creator_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_time` datetime(0) NULL DEFAULT NULL,
  `modifier_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modifier_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;




-- 新增项目归属表
CREATE TABLE `tb_project_attribute`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `attribute_kind` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `project_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `creator_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creator_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- 新增项目卡和项目套件
CREATE TABLE `tb_project_card_project_suite`  (
  `project_card_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `project_suite_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL

) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- 新增项目套件表
CREATE TABLE `tb_project_suite`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `project_suite_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	`project_suite_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `suite_status` int(11) NULL DEFAULT NULL ,
	`suite_type` int(11) NULL DEFAULT NULL ,
	 PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;





-- 新增项目卡表
CREATE TABLE `tb_project_card`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `project_card_period` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `term_short_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `project_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;



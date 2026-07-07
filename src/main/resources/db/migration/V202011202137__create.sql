CREATE TABLE `tb_project_suite_articles`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `articles_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `articles_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `project_suite_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM  CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `tb_project_suite_low_value_articles`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `low_value_articles_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `low_value_articles_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `project_suite_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM  CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

ALTER TABLE `tb_project_suite_articles` ADD CONSTRAINT `111` FOREIGN KEY (`project_suite_id`) REFERENCES `tb_project_suite` (`id`);
ALTER TABLE `tb_project_suite_low_value_articles` ADD CONSTRAINT `222` FOREIGN KEY (`project_suite_id`) REFERENCES `tb_project_suite` (`id`);


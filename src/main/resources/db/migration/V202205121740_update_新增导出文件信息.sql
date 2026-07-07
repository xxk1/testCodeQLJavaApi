ALTER TABLE `tb_download_record`
    ADD COLUMN `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `pack_type`,
ADD COLUMN `file_real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `file_path`,
ADD COLUMN `file_saved_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `file_real_name`,
ADD COLUMN `file_size` bigint(20) NULL DEFAULT NULL AFTER `file_saved_name`,
ADD COLUMN `file_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `file_size`,
ADD COLUMN `inner_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `file_type`,
ADD COLUMN `outer_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER file_type
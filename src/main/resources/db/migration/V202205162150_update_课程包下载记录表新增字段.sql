-- 新增学院ID与学院名称字段
ALTER TABLE `tb_download_record`
    ADD COLUMN `college_id` varchar(255) NULL AFTER `course_name`,
ADD COLUMN `college_name` varchar(255) NULL AFTER `college_id`;

-- 修改导报记录表课程ID字段类型

ALTER TABLE `tb_download_record`
    MODIFY COLUMN `course_id` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL AFTER `modify_time`,
    MODIFY COLUMN `course_name` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL AFTER `course_id`;
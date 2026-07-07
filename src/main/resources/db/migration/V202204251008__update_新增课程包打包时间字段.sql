ALTER TABLE `tb_course_version`
    ADD COLUMN `course_bag_upload_time` datetime(0) NULL AFTER `course_bag_upload_status`;
-- 新增打包人ID字段
ALTER TABLE `tb_course_version`
    ADD COLUMN `course_bag_user_id` varchar(255) NULL AFTER `course_bag_upload_time`;

-- 打包状态设置默认值
ALTER TABLE `tb_course_version` ALTER COLUMN `course_bag_upload_status` SET  DEFAULT '0';
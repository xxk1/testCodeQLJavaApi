-- 增加文件转码后文件信息相关字段
ALTER TABLE tb_course_resource_file ADD COLUMN transcode_file_size bigint(20) DEFAULT NULL;
ALTER TABLE tb_course_resource_file ADD COLUMN transcode_file_type varchar(255) DEFAULT NULL;
ALTER TABLE tb_course_resource_file ADD COLUMN transcode_file_path varchar(255) DEFAULT NULL;
ALTER TABLE tb_course_resource_file ADD COLUMN transcode_file_saved_name varchar(255) DEFAULT NULL;
ALTER TABLE tb_course_resource_file ADD COLUMN transcode_inner_ip varchar(255) DEFAULT NULL;
ALTER TABLE tb_course_resource_file ADD COLUMN transcode_outer_ip varchar(255) DEFAULT NULL;
ALTER TABLE tb_course_resource_file ADD COLUMN transcode_status int(11) DEFAULT 0;
ALTER TABLE tb_course_resource_file ADD COLUMN transcode_message varchar(255) DEFAULT NULL;

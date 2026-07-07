CREATE TABLE `tb_download_record`  (
                                       `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                       `create_time` datetime(0) NULL DEFAULT NULL,
                                       `creator_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                       `creator_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                       `modifier_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                       `modifier_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                       `modify_time` datetime(0) NULL DEFAULT NULL,
                                       `course_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                       `course_name` varchar(255) NULL,
                                       `version_id` varchar(255) NULL,
                                       `version_serial_number` int(11) NULL DEFAULT NULL,
                                       `pack_status` int(255) NULL DEFAULT 0,
                                       `pack_user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                       `pack_type` int(11) NULL ,
                                       PRIMARY KEY (`id`)
);
-- 把version表中的数据迁移到新表中
insert into tb_download_record(id,
                               creator_id,
                               create_time,
                               creator_name,modifier_id,modify_time,modifier_name,course_id,version_id,
                               version_serial_number,pack_status,pack_user_id)
select UUID() as id ,
       tcv.course_bag_user_id as creator_id,
       tcv.course_bag_upload_time as create_time ,
       tcv.creator_name as creator_name,
       tcv.course_bag_user_id as modifier_id,
       tcv.course_bag_upload_time as modify_time,
       tcv.creator_name as modifier_name,
       tcv.course_id,
       tcv.id as version_id,
       tcv.version_serial_number,
       tcv.course_bag_upload_status as pack_status,
       tcv.course_bag_user_id as pack_user_id
from tb_course_version tcv  where  tcv.course_bag_upload_status IS NOT NULL;



-- 删除课程版本表多余字段
ALTER TABLE tb_course_version DROP course_bag_upload_status;
ALTER TABLE tb_course_version DROP course_bag_upload_time;
ALTER TABLE tb_course_version DROP course_bag_user_id;


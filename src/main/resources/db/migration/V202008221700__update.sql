-- 课表明细教室表是否能直播，组id
ALTER TABLE `tb_course_table_detail_room_user`
    add column `has_live` bit(1) NOT NULL;
ALTER TABLE `tb_course_table_detail_room_user`
    add column `group_id` varchar(255);



-- 课表明细教室表增加物品准备老师id，物品准备老师编号，物品准备老师名称字段
ALTER TABLE `tb_course_table_detail_room_user` ADD item_teacher_id varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci ;
ALTER TABLE `tb_course_table_detail_room_user` ADD item_teacher_no varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci ;
ALTER TABLE `tb_course_table_detail_room_user` ADD item_teacher_name varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci;



-- tb_group_member 增加student_id、group_member_status字段索引
ALTER TABLE `tb_group_member`
    ADD INDEX student_id_index (`student_id`);
ALTER TABLE `tb_group_member`
    ADD INDEX group_member_status_index (`group_member_status`);

-- tb_course_table_detail_teacher 增加teacher_id字段索引
ALTER TABLE `tb_course_table_detail_teacher`
    ADD INDEX teacher_id_index (`teacher_id`);

-- tb_group 增加group_status_index字段索引
ALTER TABLE `tb_group`
    ADD INDEX group_status_index (`group_status`);


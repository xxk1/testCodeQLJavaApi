-- 更新tb_course_table_detail数据，填充老师名称和课程没有的情况（二次排课）
UPDATE tb_course_table_detail a,tb_course_table b SET a.course_name=b.course_name WHERE a.course_table_id=b.id;
UPDATE tb_course_table_detail a,
( SELECT course_table_detail_id as id, GROUP_CONCAT( teacher_name ) as teacherNames FROM tb_course_table_detail_teacher GROUP BY course_table_detail_id ) b
SET a.teacher_names = b.teacherNames where a.id = b.id;
-- 创建索引
CREATE INDEX index_teacher_name ON tb_course_table_detail (`teacher_names`);
CREATE INDEX index_course_table_detail_id ON tb_course_table_detail_teacher (`course_table_detail_id`);
CREATE INDEX index_course_table_detail_id ON tb_course_table_detail_room_user (`course_table_detail_id`);
CREATE INDEX index_course_table_detail_id ON tb_course_segment (`course_table_detail_id`);

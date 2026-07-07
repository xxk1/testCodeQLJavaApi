-- 为 tb_course_table 添加复合索引（覆盖 WHERE 和 JOIN 条件）
CREATE INDEX idx_course_table_filter ON tb_course_table (source, college_id, school_year, term, id);
-- 为 tb_course_table 添加复合索引包含student_type
CREATE INDEX idx_course_table_student_filter ON tb_course_table (source, college_id, school_year, term, student_type, id, group_id);
-- 为 tb_course_table_detail 添加复合索引（覆盖 course_date 和 JOIN 条件）
CREATE INDEX idx_course_table_detail_filter ON tb_course_table_detail (course_date, course_table_id, segment);

-- 为 tb_group_member 添加覆盖索引（直接获取 student_id）
CREATE INDEX idx_group_member_filter ON tb_group_member (group_id, group_member_status, student_id);

-- tb_group 表：创建 (id, group_status) 组合索引
CREATE INDEX idx_group_id_status ON tb_group(id, group_status);

-- tb_course_table 表：创建 (group_id, source, college_id, school_year, term) 组合索引
CREATE INDEX idx_course_table_group_filter ON tb_course_table(group_id, source, college_id, school_year, term);

-- tb_course_table_detail 表：创建 (course_table_id, course_date) 组合索引
CREATE INDEX idx_course_detail_id_date ON tb_course_table_detail(course_table_id, course_date);


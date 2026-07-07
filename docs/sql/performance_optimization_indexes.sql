-- ==========================================
-- 学生课程列表接口性能优化 - 索引创建脚本
-- ==========================================
-- 用途：为 /v2/courseTables/studentCourseList 接口优化查询性能
-- 创建时间：2026-03-13
-- 预期效果：查询性能提升 50%-80%
-- ==========================================

-- ==========================================
-- 1. tb_group_member 表索引优化
-- ==========================================

-- 索引 1：student_id + group_member_status 复合索引
-- 用于：按学生ID查询班级成员
-- 场景：WHERE student_id = ? AND group_member_status = 0
CREATE INDEX idx_group_member_student_status 
ON tb_group_member(student_id, group_member_status);

-- 索引 2：open_id + group_member_status 复合索引
-- 用于：按微信openId查询班级成员
-- 场景：WHERE open_id = ? AND group_member_status = 0
CREATE INDEX idx_group_member_openid_status 
ON tb_group_member(open_id, group_member_status);

-- 索引 3：group_id + group_member_status 复合索引
-- 用于：按班级ID查询成员数量
-- 场景：WHERE group_id IN (?) AND group_member_status = 0
CREATE INDEX idx_group_member_group_status 
ON tb_group_member(group_id, group_member_status);


-- ==========================================
-- 2. tb_course 表索引优化
-- ==========================================

-- 索引：course_id 索引（主键索引通常已存在）
-- 用于：关联查询课程信息
-- 场景：INNER JOIN tb_course c ON ct.course_id = c.id
-- 注意：如果 id 是主键，则无需额外创建索引
-- CREATE INDEX idx_course_id ON tb_course(id);


-- ==========================================
-- 3. tb_course_table 表索引优化
-- ==========================================

-- 索引 4：course_id 索引
-- 用于：关联查询课程信息
-- 场景：INNER JOIN tb_course c ON ct.course_id = c.id
CREATE INDEX idx_course_table_course_id 
ON tb_course_table(course_id);

-- 索引 5：school_year + term 复合索引
-- 用于：按学年和学期过滤课程表
-- 场景：WHERE school_year = ? AND term = ?
CREATE INDEX idx_course_table_year_term 
ON tb_course_table(school_year, term);

-- 索引 6：group_id 索引
-- 用于：关联查询班级信息
-- 场景：INNER JOIN tb_group g ON ct.group_id = g.id
CREATE INDEX idx_course_table_group_id 
ON tb_course_table(group_id);

-- 索引 7：course_category_id 索引
-- 用于：关联查询课程类别
-- 场景：LEFT JOIN tb_course_category cc ON ct.course_category_id = cc.id
CREATE INDEX idx_course_table_category_id 
ON tb_course_table(course_category_id);


-- ==========================================
-- 4. tb_group 表索引优化
-- ==========================================

-- 索引 8：group_status 索引
-- 用于：过滤正常状态的班级
-- 场景：WHERE group_status = 0
CREATE INDEX idx_group_status 
ON tb_group(group_status);


-- ==========================================
-- 5. tb_course_table_detail 表索引优化
-- ==========================================

-- 索引 9：course_table_id 索引
-- 用于：关联查询课表明细
-- 场景：INNER JOIN tb_course_table_detail ctd ON ct.id = ctd.course_table_id
CREATE INDEX idx_course_table_detail_course_table_id 
ON tb_course_table_detail(course_table_id);


-- ==========================================
-- 6. tb_course_table_detail_teacher 表索引优化
-- ==========================================

-- 索引 10：course_table_detail_id + show_order 复合索引
-- 用于：关联查询教师信息并按顺序排序
-- 场景：INNER JOIN tb_course_table_detail_teacher t ON ctd.id = t.course_table_detail_id ORDER BY t.show_order
CREATE INDEX idx_course_table_detail_teacher_detail_order 
ON tb_course_table_detail_teacher(course_table_detail_id, show_order);


-- ==========================================
-- 7. 查询索引创建结果
-- ==========================================

-- 查询 tb_group_member 表索引
SELECT 
    TABLE_NAME,
    INDEX_NAME,
    COLUMN_NAME,
    SEQ_IN_INDEX
FROM information_schema.STATISTICS
WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'tb_group_member'
ORDER BY TABLE_NAME, INDEX_NAME, SEQ_IN_INDEX;

-- 查询 tb_course 表索引
SELECT 
    TABLE_NAME,
    INDEX_NAME,
    COLUMN_NAME,
    SEQ_IN_INDEX
FROM information_schema.STATISTICS
WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'tb_course'
ORDER BY TABLE_NAME, INDEX_NAME, SEQ_IN_INDEX;

-- 查询 tb_course_table 表索引
SELECT 
    TABLE_NAME,
    INDEX_NAME,
    COLUMN_NAME,
    SEQ_IN_INDEX
FROM information_schema.STATISTICS
WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'tb_course_table'
ORDER BY TABLE_NAME, INDEX_NAME, SEQ_IN_INDEX;

-- 查询 tb_group 表索引
SELECT 
    TABLE_NAME,
    INDEX_NAME,
    COLUMN_NAME,
    SEQ_IN_INDEX
FROM information_schema.STATISTICS
WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'tb_group'
ORDER BY TABLE_NAME, INDEX_NAME, SEQ_IN_INDEX;

-- 查询 tb_course_table_detail 表索引
SELECT 
    TABLE_NAME,
    INDEX_NAME,
    COLUMN_NAME,
    SEQ_IN_INDEX
FROM information_schema.STATISTICS
WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'tb_course_table_detail'
ORDER BY TABLE_NAME, INDEX_NAME, SEQ_IN_INDEX;

-- 查询 tb_course_table_detail_teacher 表索引
SELECT 
    TABLE_NAME,
    INDEX_NAME,
    COLUMN_NAME,
    SEQ_IN_INDEX
FROM information_schema.STATISTICS
WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'tb_course_table_detail_teacher'
ORDER BY TABLE_NAME, INDEX_NAME, SEQ_IN_INDEX;


-- ==========================================
-- 7. 验证索引使用情况（执行计划分析）
-- ==========================================

-- 示例：验证主查询是否使用索引
-- 请将 :userId、:schoolYear、:term 替换为实际值
EXPLAIN 
SELECT DISTINCT
    ct.id as courseTableId,
    ct.course_id as courseId,
    ct.course_name as courseName,
    c.course_code as courseCode,
    ct.college_name as collegeName,
    g.id as groupId,
    g.group_name as groupName
FROM tb_course_table ct
INNER JOIN tb_course c ON ct.course_id = c.id
INNER JOIN tb_group g ON ct.group_id = g.id
INNER JOIN tb_group_member gm ON g.id = gm.group_id
WHERE gm.student_id = '替换为实际userId'
  AND ct.school_year = '2025-2026'
  AND ct.term = 1
  AND g.group_status = 0
  AND gm.group_member_status = 0;

-- 示例：验证教师信息查询是否使用索引
-- 请将 :courseTableIds 替换为实际的课程表ID列表
EXPLAIN
SELECT ct.id as courseTableId, t.teacher_id, t.teacher_name
FROM tb_course_table ct
INNER JOIN tb_course_table_detail ctd ON ct.id = ctd.course_table_id
INNER JOIN tb_course_table_detail_teacher t ON ctd.id = t.course_table_detail_id
WHERE ct.id IN ('course_table_id_1', 'course_table_id_2')
ORDER BY t.show_order;


-- ==========================================
-- 8. 索引维护建议
-- ==========================================

-- 定期分析表统计信息（建议每周执行）
ANALYZE TABLE tb_group_member;
ANALYZE TABLE tb_course;
ANALYZE TABLE tb_course_table;
ANALYZE TABLE tb_group;
ANALYZE TABLE tb_course_table_detail;
ANALYZE TABLE tb_course_table_detail_teacher;

-- 优化表碎片（建议每月执行）
OPTIMIZE TABLE tb_group_member;
OPTIMIZE TABLE tb_course;
OPTIMIZE TABLE tb_course_table;
OPTIMIZE TABLE tb_group;
OPTIMIZE TABLE tb_course_table_detail;
OPTIMIZE TABLE tb_course_table_detail_teacher;


-- ==========================================
-- 注意事项
-- ==========================================
-- 1. 在生产环境执行前，请先在测试环境验证
-- 2. 建议在业务低峰期执行索引创建
-- 3. 索引创建可能需要几分钟时间，取决于表数据量
-- 4. 创建索引期间可能会短暂锁表，请注意业务影响
-- 5. 如果索引已存在，SQL 会报错，可以忽略或先检查是否存在
-- ==========================================

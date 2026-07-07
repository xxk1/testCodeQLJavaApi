-- 创建 课程课程编码 索引
CREATE INDEX idx_course_course_code ON tb_course (course_code);

-- 创建 课程考试成绩课程编码 索引
CREATE INDEX idx_score_course_code ON tb_student_score (course_code);

-- 创建 课程考试成绩学号 索引
CREATE INDEX idx_score_student_no ON tb_student_score (student_no);

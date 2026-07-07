-- 创建课表明细课表上课时间索引
CREATE INDEX idx_tabledetail_course_date ON tb_course_table_detail (course_date);
-- 1.查看权限库中学校id  以药大举例  权限库中为1001
-- 2. 更新coursedb中的学院id
update tb_college
set id='1001',
    college_code='1001'
where college_name = '中国药科大学';

-- 3. 更新课程中的学院id和code
update tb_course
set college_id='1001',
    college_code='1001'
where college_name = '中国药科大学';

-- 4, 更新开课中的学院id和code
update tb_course_table set college_id='1001' where college_name='中国药科大学';
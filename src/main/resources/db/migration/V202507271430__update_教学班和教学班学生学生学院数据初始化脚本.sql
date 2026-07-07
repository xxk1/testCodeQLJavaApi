-- 在coursedb所在数据库中创建一个临时库test_authority，将tb_user复制到其中

-- 更新教学班学生表中的学生学院字段
update tb_group_member a inner join test_authority.tb_user b on a.student_id = b.id
set a.student_college_id = b.org_id,a.student_college_name = b.org_name;

-- 更新教学班表中的学生学院字段
UPDATE  tb_group a inner join
    (
        select group_id ,
               GROUP_CONCAT(DISTINCT student_college_id  ORDER BY student_college_id) as student_college_ids,
               GROUP_CONCAT(DISTINCT student_college_name ORDER BY student_college_id) as student_college_names
        from  tb_group_member
        group by group_id
    ) b on a.id = b.group_id set a.student_college_ids = b.student_college_ids,a.student_college_names = b.student_college_names;


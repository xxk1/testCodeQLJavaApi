-- 授课教师信息表新增教师职称
alter table tb_course_table_detail_teacher
    add column teacher_title varchar(255);

alter table tb_group
    add column major_composition varchar(5000);

-- 更新职称历史数据
-- 1. 复制authority库中的tb_user表到course库中
-- 2. 更新课表教师中的教师职称
update tb_course_table_detail_teacher a,tb_user b
set a.teacher_title=b.teacher_title
where a.teacher_id = b.id;

-- 3.删除复制过来的用户表
-- 可用语句    drop table  if exists tb_user;

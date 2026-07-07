/**
  注意！！！
  注意！！！
  注意！！！

  使用过二次排课和实验排课功能的才需要执行本脚本；
  判断是否使用过sql语句：select count(1) from tb_course_table_detail ctd where ctd.source= 5;
  count(1)：大于0：使用过；等于0：未使用过

  1、使用sql语句复制authority库中的tb_user表和数据至coursedb库，再执行以下语句
  2、执行完下面语句后， 再删除coursedb中tb_user表
 */


--  修正 二次排课和实验排课 课表明细老师信息中的老师学院id和学院名称
update tb_course_table_detail_teacher ctdt,tb_course_table_detail ctd,tb_user u
set ctdt.teacher_college_id=u.org_id,teacher_college_name=u.org_name
where ctdt.course_table_detail_id=ctd.id
  and u.id = ctdt.teacher_id
  and ctd.source= 5
;

-- 修正 二次排课和实验排课 课表明细信息中老师学院ids，老师学院names，老师names
update tb_course_table_detail ctd set
                                      ctd.teacher_college_ids = (select GROUP_CONCAT(ctdt.teacher_college_id order by ctdt.id) from tb_course_table_detail_teacher ctdt where ctdt.course_table_detail_id=ctd.id),
                                      ctd.teacher_college_names = (select GROUP_CONCAT(ctdt.teacher_college_name order by ctdt.id) from tb_course_table_detail_teacher ctdt where ctdt.course_table_detail_id=ctd.id),
                                      ctd.teacher_names = (select GROUP_CONCAT(ctdt.teacher_name order by ctdt.id) from tb_course_table_detail_teacher ctdt where ctdt.course_table_detail_id=ctd.id)
where ctd.source= 5
;
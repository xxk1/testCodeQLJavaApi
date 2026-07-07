-- 课表明细表添加字段:课表数据源归属名称：1.本科生2.研究生3.国教院

alter table tb_course_table_detail ADD column source_data_source_name varchar(255) default '本科生';
-- 更新数据源
UPDATE tb_course_table_detail
SET source_data_source_name =
        CASE
            WHEN source_data_source = 0 THEN '本科生'
            WHEN source_data_source = 1 THEN '研究生'
            WHEN source_data_source = 2 THEN '留学生'
            ELSE '本科生'
            END
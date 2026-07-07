-- 课程知识图谱添加排序字段和版本标签字段
alter table tb_course_knowledge_graph_domain add column show_order int(11) AFTER `course_version_id`;

alter table tb_course_knowledge_graph_domain add column version_label varchar(255) AFTER `show_order`;

-- 创建零时表
CREATE  TABLE tmp_table (
                            id VARCHAR(255) NOT NULL PRIMARY KEY,
                            course_id VARCHAR(255) NOT NULL,
                            course_version_id VARCHAR(255) NOT NULL,
                            row_num INT(11) not null
);

-- 临时表数据同步
INSERT INTO tmp_table
select id,course_id,course_version_id,row_num from (
                                                       SELECT
                                                           id,
                                                           course_id,
                                                           course_version_id,
                                                           @row_num:=IF(@current_course_id = course_id AND @current_course_version_id = course_version_id, @row_num + 1, 1) AS row_num,
                                                           @current_course_id:=course_id,
                                                           @current_course_version_id:=course_version_id
                                                       FROM
                                                           tb_course_knowledge_graph_domain,
                                                           (SELECT @row_num:=0, @current_course_id:=NULL, @current_course_version_id:=NULL) AS vars
                                                       ORDER BY
                                                           course_id,
                                                           course_version_id
                                                   ) a ;

-- 更新数据
update tb_course_knowledge_graph_domain a
    inner join tmp_table b on a.id = b.id set a.show_order = b.row_num ,a.version_label = CONCAT('V1.',(b.row_num-1));

-- 删除临时表
drop table tmp_table;



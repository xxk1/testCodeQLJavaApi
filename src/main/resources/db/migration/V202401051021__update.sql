-- 课程资源表新增排序字段
alter table tb_course_resource add column sort int(11) DEFAULT NULL;
-- 更新历史数据排序为：按照创建人和课程结构分组，按照更新时间倒序
SET @group := '';
SET @row_number := 0;

UPDATE tb_course_resource AS o
    JOIN (
    SELECT id, CONCAT(creator_id,course_structure_id), modify_time,
    @row_number := IF(@group = CONCAT(creator_id,course_structure_id), @row_number + 1, 1) AS row_num,
    @group := CONCAT(creator_id,course_structure_id)
    FROM tb_course_resource
    ORDER BY  CONCAT(creator_id,course_structure_id),modify_time
    ) AS ordered ON o.id = ordered.id
    SET o.sort = ordered.row_num;
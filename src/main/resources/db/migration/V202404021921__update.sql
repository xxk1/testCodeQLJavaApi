-- 课程资源表新增辅助排序字段
alter table tb_course_resource add column auxiliary_sort int(11) DEFAULT NULL;

-- 更新历史数据排序为：按照创建人和课程结构分组，按照更新时间倒序
SET @group := '';
SET @row_number := 0;

UPDATE tb_course_resource AS o
    JOIN (
    SELECT id, course_structure_id, modify_time,
    @row_number := IF(@group = course_structure_id, @row_number + 1, 0) AS row_num,
    @group := course_structure_id
    FROM tb_course_resource where is_public=1 and resource_status=0
    ORDER BY  course_structure_id,modify_time
    ) AS ordered ON o.id = ordered.id
    SET o.auxiliary_sort = ordered.row_num;
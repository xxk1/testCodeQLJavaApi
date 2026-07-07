-- 增加remark字段
ALTER TABLE `tb_wisdom_course_task` 
ADD COLUMN `remark` longtext NULL AFTER `wisdom_course_id`;

-- 1. 优化主查询（寻找task_type=2且task_status=2的记录）
-- 这是一个覆盖索引，能让子查询仅通过索引即可完成，速度极快。
ALTER TABLE tb_wisdom_course_task ADD INDEX idx_main_task (task_type, task_status, resource_id);

-- 2. 优化左连接查询（核心优化）
-- 这个复合索引将直接用于快速定位existing表中特定resource_id和task_type的记录。
ALTER TABLE tb_wisdom_course_task ADD INDEX idx_resource_type (resource_id, task_type);

-- 删除语音转写没有生成成功的所有相关任务
DELETE t1 FROM tb_wisdom_course_task t1
WHERE t1.task_type IN (3,4,5,7,14) 
AND t1.resource_id IN (
    SELECT resource_id FROM (
        -- 这是原封不动的子查询，现在作为内层查询
        SELECT DISTINCT resource_id 
        FROM tb_wisdom_course_task 
        WHERE task_type = 2 AND task_status != 2
    ) AS temp_table -- 必须为派生表指定一个别名
);
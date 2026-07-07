-- sort空值填充为0
update tb_course_knowledge_graph_node_question set sort = 0 where sort is null;

-- 更新相关排序顺序(sort)
SET @row_number := 0;
SET @prev_knowledge_node_id := NULL;
UPDATE tb_course_knowledge_graph_node_question
SET sort = (
    SELECT new_sort
    FROM (
        SELECT 
            id,
            knowledge_node_id,
            create_time,
            @row_number := IF(@prev_knowledge_node_id = knowledge_node_id, @row_number + 1, 0) AS new_sort,
            @prev_knowledge_node_id := knowledge_node_id
        FROM 
            tb_course_knowledge_graph_node_question
        -- 确保查询排序逻辑与更新条件一致，这里按最终需要的顺序排序
        ORDER BY 
            knowledge_node_id, create_time
    ) AS temp
    WHERE temp.id = tb_course_knowledge_graph_node_question.id
);
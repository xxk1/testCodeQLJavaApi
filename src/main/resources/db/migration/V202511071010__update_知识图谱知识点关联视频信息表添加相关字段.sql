-- 知识图谱知识点关联视频信息表添加相似性分数字段和高亮文本内容字段
alter table tb_course_knowledge_graph_node_video_info_text add column high_light_text_content
    longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '高亮文本内容';
alter table tb_course_knowledge_graph_node_video_info_text add column similarity_score Double NULL DEFAULT NULL;

-- 知识图谱知识点关联视频信息表添加相似性分数字段和高亮文本内容字段
alter table tb_kg_gen_node_video_info add column high_light_text_content
    longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '高亮文本内容';
alter table tb_kg_gen_node_video_info add column similarity_score Double NULL DEFAULT NULL;
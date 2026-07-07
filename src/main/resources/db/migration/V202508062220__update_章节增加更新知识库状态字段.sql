-- 增加知识库向量数据库推送状态
ALTER TABLE  `tb_teaching_material_file_content`
    ADD COLUMN `vectordb_status` int(11) NULL DEFAULT 0 AFTER `file_content_status`;
-- 增加知识库搜索引擎推送状态
ALTER TABLE  `tb_teaching_material_file_content`
    ADD COLUMN `search_engine_status` int(11) NULL DEFAULT 0 AFTER `vectordb_status`;

update tb_teaching_material_file_content set vectordb_status = 0, search_engine_status= 0;
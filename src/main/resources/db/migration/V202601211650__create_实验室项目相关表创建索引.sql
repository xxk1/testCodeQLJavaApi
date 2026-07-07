-- 创建 tb_project project_code 索引
CREATE INDEX idx_project_code ON tb_project (project_code);

-- 创建 tb_project_attribute data_id和attribute_kind 索引
CREATE INDEX idx_data_id_attribute_kind ON tb_project_attribute (data_id,attribute_kind);
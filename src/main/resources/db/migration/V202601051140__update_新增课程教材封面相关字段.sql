ALTER TABLE tb_teaching_material_file
    ADD COLUMN cover_file_display_name varchar(255)  DEFAULT NULL,
  ADD COLUMN cover_file_real_name varchar(255)  DEFAULT NULL,
  ADD COLUMN cover_file_type varchar(255)  DEFAULT NULL,
  ADD COLUMN cover_inner_url varchar(255)  DEFAULT NULL,
  ADD COLUMN cover_outer_url varchar(255)  DEFAULT NULL,
  ADD COLUMN cover_file_path varchar(255)  DEFAULT NULL,
  ADD COLUMN cover_file_size bigint(20) NULL DEFAULT NULL
;

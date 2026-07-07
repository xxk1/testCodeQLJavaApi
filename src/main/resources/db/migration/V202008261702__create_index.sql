-- 更改数据库表引擎
alter table tb_course_table engine=innodb;
alter table tb_course_table_detail engine=innodb;
alter table tb_course_table_detail_project engine=innodb;
alter table tb_course_table_detail_room_user engine=innodb;
alter table tb_course_table_detail_teacher engine=innodb;

-- 删除课表原有索引
DROP INDEX FKk7jtlec2gw8f4474w8hh1fohx ON tb_course_table;
DROP INDEX FK3er5dvm4y0oha0eruye2kfcr6 ON tb_course_table;
DROP INDEX FKj6fwa3jyj02d0ghlbi0g2rq13 ON tb_course_table;
DROP INDEX school_year_index ON tb_course_table;

-- 创建课表添加索引
CREATE INDEX FKk7jtlec2gw8f4474w8hh1fohx ON tb_course_table (course_id);
CREATE INDEX FK3er5dvm4y0oha0eruye2kfcr6 ON tb_course_table (course_category_id);
CREATE INDEX FKj6fwa3jyj02d0ghlbi0g2rq13 ON tb_course_table (group_id);
CREATE INDEX school_year_index ON tb_course_table (school_year);

-- 删除课表明细原有索引
DROP INDEX FKkjvj3gld4it7nvx7aibvkqb6q ON tb_course_table_detail;
DROP INDEX idx_tabledetail_course_date ON tb_course_table_detail;

-- 创建课表明细索引
CREATE INDEX FKkjvj3gld4it7nvx7aibvkqb6q ON tb_course_table_detail (course_table_id);
CREATE INDEX idx_tabledetail_course_date ON tb_course_table_detail (course_date);

-- 删除课表项目原有索引
DROP INDEX FKh1yaiycy0pyelsuk9sh2j10mo ON tb_course_table_detail_project;
-- 创建课表项目索引
CREATE INDEX FKh1yaiycy0pyelsuk9sh2j10mo ON tb_course_table_detail_project (course_table_detail_id);

-- 删除课表教室原有索引
DROP INDEX FK4u3le49775nakgs9dpj2jw58a ON tb_course_table_detail_room_user;
-- 创建课表教室索引
CREATE INDEX FK4u3le49775nakgs9dpj2jw58a ON tb_course_table_detail_room_user (course_table_detail_id);

-- 删除课表老师原有索引
DROP INDEX FKlsakbircy231jdwhg05mkqx1b ON tb_course_table_detail_teacher;
DROP INDEX teacher_id_index ON tb_course_table_detail_teacher;
-- 创建课表老师索引
CREATE INDEX FKlsakbircy231jdwhg05mkqx1b ON tb_course_table_detail_teacher (course_table_detail_id);
CREATE INDEX teacher_id_index ON tb_course_table_detail_teacher (teacher_id);
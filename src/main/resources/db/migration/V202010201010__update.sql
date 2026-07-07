-- 删除项目表课程id字段
ALTER TABLE `tb_project` DROP `course_id`;

-- 项目表新增项目分类名称字段
alter table `tb_project`
    add column project_classification_name varchar(255) NULL DEFAULT NULL;
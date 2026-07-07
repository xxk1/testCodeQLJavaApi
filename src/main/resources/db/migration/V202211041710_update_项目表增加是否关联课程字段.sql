## 项目表增加是否关联课程字段（0：否，1：是）
ALTER TABLE tb_project ADD  whether_associate_courses int(11) NOT NULL Default 1;

-- 学院表新增父级学院id和排序字段
alter table tb_college
    add column parent_id varchar(255) default '#';
alter table tb_college
    add column show_order integer not null default 0;
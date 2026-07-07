-- 备课包主题
create table tb_course_theme
(
    id               varchar(255) not null,
    create_time      datetime,
    creator_id       varchar(255),
    creator_name     varchar(255),
    modifier_id      varchar(255),
    modifier_name    varchar(255),
    modify_time      datetime,
    theme_desc       varchar(255),
    theme_detail_num integer,
    theme_name       varchar(255),
    theme_status     integer,
    primary key (id)
) engine = InnoDB;

-- 备课包主题明细
create table tb_course_theme_detail
(
    id                   varchar(255) not null,
    create_time          datetime,
    creator_id           varchar(255),
    creator_name         varchar(255),
    show_order           integer,
    theme_detail_content varchar(255),
    course_theme_id      varchar(255),
    primary key (id)
) engine = InnoDB;
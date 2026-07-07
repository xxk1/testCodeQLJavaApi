-- 新增课程版本信息表
create table tb_course_version
(
    id                    varchar(255) not null,
    create_time           datetime,
    creator_id            varchar(255),
    creator_name          varchar(255),
    modifier_id           varchar(255),
    modifier_name         varchar(255),
    modify_time           datetime,
    course_version_status integer,
    filing_time           datetime,
    filing_user_id        varchar(255),
    filing_user_name      varchar(255),
    filing_user_no        varchar(255),
    remark                varchar(255),
    version_completion    double precision,
    version_serial_number integer,
    course_id             varchar(255),
    primary key (id)
) engine = InnoDB;

-- 课程建设基本信息表更新
alter table tb_course_material
    add column class_hours double precision;
alter table tb_course_material
    add column course_content varchar(1255);
alter table tb_course_material
    add column reference_materials longtext;
alter table tb_course_material
    add column teaching_material longtext;
alter table tb_course_material
    add column teaching_user_type varchar(255);
alter table tb_course_material
    add column course_id varchar(255);
alter table tb_course_material
    add column course_category_id integer;
alter table tb_course_material
    add column course_version_id varchar(255);

update tb_course_material a
set course_id= (select id from tb_course where course_material_id = a.id);

-- 更新教学团队表
alter table tb_course_teaching_team
    add column job_title varchar(255);
alter table tb_course_teaching_team
    add column school_name varchar(255);
alter table tb_course_teaching_team
    add column teacher_data_source integer;
alter table tb_course_teaching_team
    add column teacher_profile varchar(1255);
alter table tb_course_teaching_team
    add column course_version_id varchar(255);
alter table tb_course_teaching_team
    add column modifier_id varchar(255);
alter table tb_course_teaching_team
    add column modifier_name varchar(255);
alter table tb_course_teaching_team
    add column modify_time datetime;

update tb_course_teaching_team
set modifier_id=creator_id,
    modifier_name=creator_name,
    modify_time=create_time;

-- 新增课程目标表
CREATE TABLE `tb_course_chapter_goal`
(
    `id`                  varchar(255) NOT NULL,
    `create_time`         datetime     DEFAULT NULL,
    `creator_id`          varchar(255) DEFAULT NULL,
    `creator_name`        varchar(255) DEFAULT NULL,
    `modifier_id`         varchar(255) DEFAULT NULL,
    `modifier_name`       varchar(255) DEFAULT NULL,
    `modify_time`         datetime     DEFAULT NULL,
    `chapter_goal_type`   int(11)      DEFAULT NULL,
    `content`             longtext,
    `course_structure_id` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKq979ar3usy08grp44nubhqtp1` (`course_structure_id`),
    CONSTRAINT `FKq979ar3usy08grp44nubhqtp1` FOREIGN KEY (`course_structure_id`) REFERENCES `tb_course_structure` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- 新增课程完成度表
create table tb_course_completion
(
    id                  varchar(255) not null,
    create_time         datetime,
    creator_id          varchar(255),
    creator_name        varchar(255),
    modifier_id         varchar(255),
    modifier_name       varchar(255),
    modify_time         datetime,
    course_content_enum integer,
    is_completed        bit          not null,
    course_id           varchar(255),
    course_version_id   varchar(255),
    primary key (id)
) engine = InnoDB;

-- 新增课程介绍表
CREATE TABLE `tb_course_introduction`
(
    `id`                     varchar(255) NOT NULL,
    `create_time`            datetime      DEFAULT NULL,
    `creator_id`             varchar(255)  DEFAULT NULL,
    `creator_name`           varchar(255)  DEFAULT NULL,
    `modifier_id`            varchar(255)  DEFAULT NULL,
    `modifier_name`          varchar(255)  DEFAULT NULL,
    `modify_time`            datetime      DEFAULT NULL,
    `classification_content` varchar(1255) DEFAULT NULL,
    `classification_name`    varchar(255)  DEFAULT NULL,
    `course_id`              varchar(255)  DEFAULT NULL,
    `course_version_id`      varchar(255)  DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK4311o5wpm2c354sgbh9p2m0j8` (`course_version_id`),
    CONSTRAINT `FK4311o5wpm2c354sgbh9p2m0j8` FOREIGN KEY (`course_version_id`) REFERENCES `tb_course_version` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- 新增课程知识结构表
create table tb_course_knowledge_structure
(
    id                       varchar(255) not null,
    create_time              datetime,
    creator_id               varchar(255),
    creator_name             varchar(255),
    modifier_id              varchar(255),
    modifier_name            varchar(255),
    modify_time              datetime,
    content                  varchar(255),
    knowledge_structure_type integer,
    parent_id                varchar(255),
    show_order               integer      not null,
    structure_status         integer,
    course_id                varchar(255),
    course_version_id        varchar(255),
    primary key (id)
) engine = InnoDB;

-- 新增知识点与课程资源关联表
create table tb_course_resource_knowledge_point
(
    id                 varchar(255) not null,
    create_time        datetime,
    creator_id         varchar(255),
    creator_name       varchar(255),
    modifier_id        varchar(255),
    modifier_name      varchar(255),
    modify_time        datetime,
    knowledge_point_id varchar(255),
    resource_id        varchar(255),
    primary key (id)
) engine = InnoDB;

-- 课程结构表新增课程版本id
alter table tb_course_structure
    add column course_version_id varchar(255);

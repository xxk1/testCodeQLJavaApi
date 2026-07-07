create index idx_course_version_status on tb_course_version (course_version_status);

create index idx_course_id on tb_course_version (course_id);

create index idx_course_version_id on tb_course_teaching_team (course_version_id);

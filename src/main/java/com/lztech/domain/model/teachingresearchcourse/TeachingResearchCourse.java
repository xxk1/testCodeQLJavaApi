package com.lztech.domain.model.teachingresearchcourse;

import com.lztech.domain.model.base.BaseModel;
import com.lztech.domain.model.teachingresearchsection.TeachingResearchSection;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "tb_teaching_research_course")
public class TeachingResearchCourse extends BaseModel {
    private String courseId;
    private String courseName;
    private String courseCode;
    @ManyToOne
    @JoinColumn(name = "teaching_research_section_id")
    private TeachingResearchSection teachingResearchSection;
}

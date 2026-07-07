package com.lztech.domain.model.teachingresearchsection;

import com.lztech.domain.model.base.BaseUpdateModel;
import com.lztech.domain.model.teachingresearchcourse.TeachingResearchCourse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "tb_teaching_research_section")
public class TeachingResearchSection extends BaseUpdateModel {
    private String collegeId;
    private String collegeName;
    /**
     * 教研室名称
     */
    private String teachingResearchName;
    @OneToMany(mappedBy = "teachingResearchSection", fetch = FetchType.EAGER)
    private List<TeachingResearchCourse> teachingResearchCourseList = new ArrayList<>();
}

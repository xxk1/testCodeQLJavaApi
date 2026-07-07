package com.lztech.domain.model.projectcard;


import com.lztech.domain.model.projectcardprojectsuite.ProjectCardProjectSuite;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "tb_project_card")
public class ProjectCard {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    private String projectCardPeriod;

    private String projectId;

    private  String projectName;

    private String courseId;
    
    private String courseCode;

    private String courseName;

    private Date createTime; // 创建时间

    private Date modifyTime;// 更新时间

    @OneToMany(mappedBy = "projectCard", fetch = FetchType.EAGER)
    private List<ProjectCardProjectSuite> projectCardProjectSuiteList = new ArrayList<>();


}

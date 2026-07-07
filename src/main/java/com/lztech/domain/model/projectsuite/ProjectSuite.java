package com.lztech.domain.model.projectsuite;

import com.lztech.domain.model.projectcardprojectsuite.ProjectCardProjectSuite;
import com.lztech.domain.model.projectsuite.enums.ProjectSuiteStatus;
import com.lztech.domain.model.projectsuite.enums.ProjectSuiteType;
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
@Table(name = "tb_project_suite")
/**
 *  项目套件
 */
public class ProjectSuite {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    private String projectSuiteName;

    private String projectSuiteNo;
    /**
     * 项目套件状态
     */
    @Enumerated(EnumType.ORDINAL)
    private ProjectSuiteStatus suiteStatus;
    /**
     * 设备仪器和低值易耗品
     */
    @Enumerated(EnumType.ORDINAL)
    private ProjectSuiteType suiteType;

    /**
     * 项目套件明细（所关联的所有套件信息）
     */
    private String projectSuiteDetail;

    private Date createTime; // 创建时间

    private Date modifyTime;// 更新时间

    @OneToMany(mappedBy = "projectSuite", fetch = FetchType.EAGER)
    private List<ProjectCardProjectSuite> projectCardProjectSuiteList = new ArrayList<>();
    /**设备仪器**/
    @OneToMany(mappedBy = "projectSuite")
    private List<ProjectSuiteArticles> projectSuiteArticles = new ArrayList<>();
    /**低值易耗品**/
    @OneToMany(mappedBy = "projectSuite")
    private List<ProjectSuiteLowValueArticles> projectSuiteLowValueArticles = new ArrayList<>();


}

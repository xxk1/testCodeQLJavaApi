package com.lztech.domain.model.projectsuite;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 项目套件与设备仪器关联
 */

@Setter
@Getter
@Entity
@Table(name = "tb_project_suite_articles")
public class ProjectSuiteArticles {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    private String articlesId;
    private String articlesNo;
    /**
     * 项目套件
     */
    @ManyToOne
    @JoinColumn(name = "project_suite_id")
    private ProjectSuite projectSuite;

    private Integer number;

}

package com.lztech.domain.model.projectcardprojectsuite;

import com.lztech.domain.model.projectcard.ProjectCard;
import com.lztech.domain.model.projectsuite.ProjectSuite;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "tb_project_card_project_suite")
public class ProjectCardProjectSuite {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;//序列

    /**
     * 项目卡
     */
    @ManyToOne
    @JoinColumn(name = "project_card_id")
    private ProjectCard projectCard;
    /**
     * 项目套件
     */
    @ManyToOne
    @JoinColumn(name = "project_suite_id")
    private ProjectSuite projectSuite;
}

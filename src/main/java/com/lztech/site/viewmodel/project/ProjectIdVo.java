package com.lztech.site.viewmodel.project;

import lombok.Data;

import java.util.List;

@Data
public class ProjectIdVo {
    private Boolean isAllProjects;

    private List<String> projectIdList;
}


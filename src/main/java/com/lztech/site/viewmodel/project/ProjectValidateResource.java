package com.lztech.site.viewmodel.project;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProjectValidateResource {

    private Integer status;

    private String message;
    
    private List<ProjectFileModel> projectFileModelList = new ArrayList<>();

}

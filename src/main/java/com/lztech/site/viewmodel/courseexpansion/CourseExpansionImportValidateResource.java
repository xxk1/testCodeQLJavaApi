package com.lztech.site.viewmodel.courseexpansion;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CourseExpansionImportValidateResource {

    private Integer status;

    private String message;

    private List<CourseExpansionImportFileModel> courseExpansionImportFileModelList = new ArrayList<>();
}


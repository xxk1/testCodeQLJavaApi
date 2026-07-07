package com.lztech.site.viewmodel.coursetypecount;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class CourseTypeCount {

    @Id
    private String id;
    private String type;
    private Integer count;
    private Integer sortName;

}

package com.lztech.site.viewmodel.coursetabledetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author 胖虎
 * @version 1.0
 * @date 2021/9/17 14:36
 * @QQ 790280011
 * @content:
 */
@Getter
@Setter
@ToString
@Data
@Entity
public class CourseTableIdAndCourseTableDetailId  {

    @Id
    private String id;

    @JsonProperty("courseTableId")
    private String courseTableId;
}

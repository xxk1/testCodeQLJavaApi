package com.lztech.site.viewmodel.coursematerial;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * TeacherMateriaStatistic
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-16T08:13:50.808Z")


public class TeacherMateriaStatistic {
    @JsonProperty("teacherNameList")
    @Valid
    private List<String> teacherNameList = null;

    @JsonProperty("resourceNumList")
    @Valid
    private List<String> resourceNumList = null;

    public TeacherMateriaStatistic teacherNameList(List<String> teacherNameList) {
        this.teacherNameList = teacherNameList;
        return this;
    }

    public TeacherMateriaStatistic addTeacherNameListItem(String teacherNameListItem) {
        if (this.teacherNameList == null) {
            this.teacherNameList = new ArrayList<String>();
        }
        this.teacherNameList.add(teacherNameListItem);
        return this;
    }

    /**
     * 老师姓名
     *
     * @return teacherNameList
     **/
    @ApiModelProperty(value = "老师姓名")


    public List<String> getTeacherNameList() {
        return teacherNameList;
    }

    public void setTeacherNameList(List<String> teacherNameList) {
        this.teacherNameList = teacherNameList;
    }

    public TeacherMateriaStatistic resourceNumList(List<String> resourceNumList) {
        this.resourceNumList = resourceNumList;
        return this;
    }

    public TeacherMateriaStatistic addResourceNumListItem(String resourceNumListItem) {
        if (this.resourceNumList == null) {
            this.resourceNumList = new ArrayList<String>();
        }
        this.resourceNumList.add(resourceNumListItem);
        return this;
    }

    /**
     * 老师素材数量
     *
     * @return resourceNumList
     **/
    @ApiModelProperty(value = "老师素材数量")


    public List<String> getResourceNumList() {
        return resourceNumList;
    }

    public void setResourceNumList(List<String> resourceNumList) {
        this.resourceNumList = resourceNumList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeacherMateriaStatistic teacherMateriaStatistic = (TeacherMateriaStatistic) o;
        return Objects.equals(this.teacherNameList, teacherMateriaStatistic.teacherNameList) &&
                Objects.equals(this.resourceNumList, teacherMateriaStatistic.resourceNumList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherNameList, resourceNumList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TeacherMateriaStatistic {\n");

        sb.append("    teacherNameList: ").append(toIndentedString(teacherNameList)).append("\n");
        sb.append("    resourceNumList: ").append(toIndentedString(resourceNumList)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}


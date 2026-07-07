package com.lztech.site.viewmodel.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * UpdateCourseStatusInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-16T06:49:19.989Z")

public class UpdateCourseStatusInfo {
    @JsonProperty("modifier")
    private String modifier = null;

    @JsonProperty("modifierName")
    private String modifierName = null;

    @JsonProperty("courseStatus")
    private Integer courseStatus = null;

    public UpdateCourseStatusInfo modifier(String modifier) {
        this.modifier = modifier;
        return this;
    }

    /**
     * 更新人id
     *
     * @return modifier
     **/
    @ApiModelProperty(value = "更新人id")


    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public UpdateCourseStatusInfo modifierName(String modifierName) {
        this.modifierName = modifierName;
        return this;
    }

    /**
     * 更新人姓名
     *
     * @return modifierName
     **/
    @ApiModelProperty(value = "更新人姓名")


    public String getModifierName() {
        return modifierName;
    }

    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }

    public UpdateCourseStatusInfo courseStatus(Integer courseStatus) {
        this.courseStatus = courseStatus;
        return this;
    }

    /**
     * 课次状态
     *
     * @return courseStatus
     **/
    @ApiModelProperty(value = "课次状态")


    public Integer getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(Integer courseStatus) {
        this.courseStatus = courseStatus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UpdateCourseStatusInfo updateCourseStatusInfo = (UpdateCourseStatusInfo) o;
        return Objects.equals(this.modifier, updateCourseStatusInfo.modifier) &&
                Objects.equals(this.modifierName, updateCourseStatusInfo.modifierName) &&
                Objects.equals(this.courseStatus, updateCourseStatusInfo.courseStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modifier, modifierName, courseStatus);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UpdateCourseStatusInfo {\n");

        sb.append("    modifier: ").append(toIndentedString(modifier)).append("\n");
        sb.append("    modifierName: ").append(toIndentedString(modifierName)).append("\n");
        sb.append("    courseStatus: ").append(toIndentedString(courseStatus)).append("\n");
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


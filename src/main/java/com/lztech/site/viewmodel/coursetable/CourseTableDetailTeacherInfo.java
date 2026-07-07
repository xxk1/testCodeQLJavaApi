package com.lztech.site.viewmodel.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTableDetailTeacherInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-12-20T05:58:15.448Z")


public class CourseTableDetailTeacherInfo {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("teacherId")
    private String teacherId = null;

    @JsonProperty("teacherName")
    private String teacherName = null;

    @JsonProperty("teacherNo")
    private String teacherNo = null;

    @JsonProperty("teacherTitle")
    private String teacherTitle = null;

    @JsonProperty("teacherCollegeId")
    private String teacherCollegeId = null;

    @JsonProperty("teacherCollegeName")
    private String teacherCollegeName = null;

    public CourseTableDetailTeacherInfo id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 课表明细老师明细id
     *
     * @return id
     **/
    @ApiModelProperty(value = "课表明细老师明细id")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CourseTableDetailTeacherInfo teacherId(String teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    /**
     * 老师id
     *
     * @return teacherId
     **/
    @ApiModelProperty(value = "老师id")


    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public CourseTableDetailTeacherInfo teacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    /**
     * 老师姓名
     *
     * @return teacherName
     **/
    @ApiModelProperty(value = "老师姓名")


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public CourseTableDetailTeacherInfo teacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
        return this;
    }

    /**
     * 老师工号
     *
     * @return teacherNo
     **/
    @ApiModelProperty(value = "老师工号")


    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public CourseTableDetailTeacherInfo teacherTitle(String teacherTitle) {
        this.teacherTitle = teacherTitle;
        return this;
    }

    /**
     * 老师职称
     *
     * @return teacherTitle
     **/
    @ApiModelProperty(value = "老师职称")


    public String getTeacherTitle() {
        return teacherTitle;
    }

    public void setTeacherTitle(String teacherTitle) {
        this.teacherTitle = teacherTitle;
    }

    public CourseTableDetailTeacherInfo teacherCollegeId(String teacherCollegeId) {
        this.teacherCollegeId = teacherCollegeId;
        return this;
    }

    /**
     * 老师所属学院id
     *
     * @return teacherCollegeId
     **/
    @ApiModelProperty(value = "老师所属学院id")


    public String getTeacherCollegeId() {
        return teacherCollegeId;
    }

    public void setTeacherCollegeId(String teacherCollegeId) {
        this.teacherCollegeId = teacherCollegeId;
    }

    public CourseTableDetailTeacherInfo teacherCollegeName(String teacherCollegeName) {
        this.teacherCollegeName = teacherCollegeName;
        return this;
    }

    /**
     * 老师所属学院名称
     *
     * @return teacherCollegeName
     **/
    @ApiModelProperty(value = "老师所属学院名称")


    public String getTeacherCollegeName() {
        return teacherCollegeName;
    }

    public void setTeacherCollegeName(String teacherCollegeName) {
        this.teacherCollegeName = teacherCollegeName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableDetailTeacherInfo courseTableDetailTeacherInfo = (CourseTableDetailTeacherInfo) o;
        return Objects.equals(this.id, courseTableDetailTeacherInfo.id) &&
                Objects.equals(this.teacherId, courseTableDetailTeacherInfo.teacherId) &&
                Objects.equals(this.teacherName, courseTableDetailTeacherInfo.teacherName) &&
                Objects.equals(this.teacherNo, courseTableDetailTeacherInfo.teacherNo) &&
                Objects.equals(this.teacherTitle, courseTableDetailTeacherInfo.teacherTitle) &&
                Objects.equals(this.teacherCollegeId, courseTableDetailTeacherInfo.teacherCollegeId) &&
                Objects.equals(this.teacherCollegeName, courseTableDetailTeacherInfo.teacherCollegeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teacherId, teacherName, teacherNo, teacherTitle, teacherCollegeId, teacherCollegeName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableDetailTeacherInfo {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    teacherId: ").append(toIndentedString(teacherId)).append("\n");
        sb.append("    teacherName: ").append(toIndentedString(teacherName)).append("\n");
        sb.append("    teacherNo: ").append(toIndentedString(teacherNo)).append("\n");
        sb.append("    teacherTitle: ").append(toIndentedString(teacherTitle)).append("\n");
        sb.append("    teacherCollegeId: ").append(toIndentedString(teacherCollegeId)).append("\n");
        sb.append("    teacherCollegeName: ").append(toIndentedString(teacherCollegeName)).append("\n");
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


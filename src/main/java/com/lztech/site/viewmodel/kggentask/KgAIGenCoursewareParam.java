package com.lztech.site.viewmodel.kggentask;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * KgAIGenCourseVideoParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-07-22T10:17:27.444+08:00")

public class KgAIGenCoursewareParam {

    @JsonProperty("isReGen")
    private Boolean isReGen = null;

    @JsonProperty("operatorId")
    private String operatorId = null;

    @JsonProperty("operatorNo")
    private String operatorNo = null;

    @JsonProperty("operatorName")
    private String operatorName = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("coursewareFileList")
    @Valid
    private List<CoursewareFileModel> coursewareFileList = new ArrayList<>();



    public KgAIGenCoursewareParam isReGen(Boolean isReGen) {
        this.isReGen = isReGen;
        return this;
    }
    /**
     * 是否重新生成
     * @return isReGen
     **/
    @ApiModelProperty(value = "是否重新生成", position = 1)
    public Boolean isIsReGen() {
        return isReGen;
    }
    public void setIsReGen(Boolean isReGen) {
        this.isReGen = isReGen;
    }

    public KgAIGenCoursewareParam operatorId(String operatorId) {
        this.operatorId = operatorId;
        return this;
    }
    /**
     * 操作人id
     * @return operatorId
     **/
    @ApiModelProperty(value = "操作人id", position = 2)
    public String getOperatorId() {
        return operatorId;
    }
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public KgAIGenCoursewareParam operatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
        return this;
    }
    /**
     * 操作人编号
     * @return operatorNo
     **/
    @ApiModelProperty(value = "操作人编号", position = 3)
    public String getOperatorNo() {
        return operatorNo;
    }
    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
    }

    public KgAIGenCoursewareParam operatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }
    /**
     * 操作人姓名
     * @return operatorName
     **/
    @ApiModelProperty(value = "操作人姓名", position = 4)
    public String getOperatorName() {
        return operatorName;
    }
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public KgAIGenCoursewareParam courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }
    /**
     * 课程id
     * @return courseId
     **/
    @ApiModelProperty(value = "课程id", position = 5)
    public String getCourseId() {
        return courseId;
    }
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public KgAIGenCoursewareParam courseName(String courseName) {
        this.courseName = courseName;
        return this;
    }
    /**
     * 课程名称
     * @return courseName
     **/
    @ApiModelProperty(value = "课程名称", position = 6)
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public KgAIGenCoursewareParam coursewareFileList(List<CoursewareFileModel> coursewareFileList) {
        this.coursewareFileList = coursewareFileList;
        return this;
    }
    public KgAIGenCoursewareParam addCoursewareFileListItem(CoursewareFileModel coursewareFileListItem) {
        if (this.coursewareFileList == null) {
            this.coursewareFileList = new ArrayList<CoursewareFileModel>();
        }
        this.coursewareFileList.add(coursewareFileListItem);
        return this;
    }
    /**
     * Get coursewareFileList
     * @return coursewareFileList
     **/
    @ApiModelProperty(value = "",position = 7)
    @Valid
    public List<CoursewareFileModel> getCoursewareFileList() {
        return coursewareFileList;
    }

    public void setCoursewareFileList(List<CoursewareFileModel> coursewareFileList) {
        this.coursewareFileList = coursewareFileList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KgAIGenCoursewareParam kgAIGenCourseVideoParam = (KgAIGenCoursewareParam) o;
        return Objects.equals(this.isReGen, kgAIGenCourseVideoParam.isReGen) &&
                Objects.equals(this.operatorId, kgAIGenCourseVideoParam.operatorId) &&
                Objects.equals(this.operatorNo, kgAIGenCourseVideoParam.operatorNo) &&
                Objects.equals(this.operatorName, kgAIGenCourseVideoParam.operatorName) &&
                Objects.equals(this.courseId, kgAIGenCourseVideoParam.courseId) &&
                Objects.equals(this.courseName, kgAIGenCourseVideoParam.courseName) &&
                Objects.equals(this.coursewareFileList, kgAIGenCourseVideoParam.coursewareFileList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isReGen, operatorId, operatorNo, operatorName, courseId, courseName,coursewareFileList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class KgAIGenCoursewareParam {\n");
        sb.append("    isReGen: ").append(toIndentedString(isReGen)).append("\n");
        sb.append("    operatorId: ").append(toIndentedString(operatorId)).append("\n");
        sb.append("    operatorNo: ").append(toIndentedString(operatorNo)).append("\n");
        sb.append("    operatorName: ").append(toIndentedString(operatorName)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    coursewareFileList: ").append(toIndentedString(coursewareFileList)).append("\n");
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

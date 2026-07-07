package com.lztech.site.viewmodel.coursematerial;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentFontStyle;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * CourseResourceStatisticVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-12T09:32:06.891Z")

@Entity
@ExcelIgnoreUnannotated //忽视无注解的属性
@ContentRowHeight(20) //文本高度
@HeadRowHeight(22) //标题高度
public class CourseResourceStatisticVo {

    @ColumnWidth(20) //列宽
    @ContentFontStyle(fontHeightInPoints = 12)
    @ExcelProperty(value = {"课程名称"}, index = 0)
    @JsonProperty("courseName")
    private String courseName = null;

    @ColumnWidth(20) //列宽
    @ContentFontStyle(fontHeightInPoints = 12)
    @ExcelProperty(value = {"所属学院"}, index = 1)
    @JsonProperty("courseCollege")
    private String courseCollege = null;

    @Id
    @ColumnWidth(20) //列宽
    @ContentFontStyle(fontHeightInPoints = 12)
    @ExcelProperty(value = {"课程编号"}, index = 2)
    @JsonProperty("courseCode")
    private String courseCode = null;

    @ColumnWidth(20) //列宽
    @ContentFontStyle(fontHeightInPoints = 12)
    @ExcelProperty(value = {"素材总数"}, index = 3)
    @JsonProperty("materialNum")
    private Integer materialNum = 0;

    @ColumnWidth(20) //列宽
    @ContentFontStyle(fontHeightInPoints = 12)
    @ExcelProperty(value = {"课件"}, index = 4)
    @JsonProperty("coursewareNum")
    private Integer coursewareNum = 0;

    @ColumnWidth(20) //列宽
    @ContentFontStyle(fontHeightInPoints = 12)
    @ExcelProperty(value = {"图片"}, index = 6)
    @JsonProperty("pictureNum")
    private Integer pictureNum = 0;

    @ColumnWidth(20) //列宽
    @ContentFontStyle(fontHeightInPoints = 12)
    @ExcelProperty(value = {"视频"}, index = 5)
    @JsonProperty("videoNum")
    private Integer videoNum = 0;

    @JsonProperty("courseStructureId")
    private String courseStructureId = null;

    @JsonProperty("collegeCode")
    private String collegeCode = null;

    @JsonProperty("useState")
    private String useState = null;

    @JsonProperty("courseId")
    private String courseId = null;

    public CourseResourceStatisticVo courseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    /**
     * 课程名称
     *
     * @return courseName
     **/
    @ApiModelProperty(value = "课程名称")


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public CourseResourceStatisticVo courseCollege(String courseCollege) {
        this.courseCollege = courseCollege;
        return this;
    }

    /**
     * 课程学院
     *
     * @return courseCollege
     **/
    @ApiModelProperty(value = "课程学院")


    public String getCourseCollege() {
        return courseCollege;
    }

    public void setCourseCollege(String courseCollege) {
        this.courseCollege = courseCollege;
    }

    public CourseResourceStatisticVo courseCode(String courseCode) {
        this.courseCode = courseCode;
        return this;
    }

    /**
     * 课程编号
     *
     * @return courseCode
     **/
    @ApiModelProperty(value = "课程编号")


    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public CourseResourceStatisticVo materialNum(Integer materialNum) {
        this.materialNum = materialNum;
        return this;
    }

    /**
     * 素材总数
     *
     * @return materialNum
     **/
    @ApiModelProperty(value = "素材总数")


    public Integer getMaterialNum() {
        return materialNum;
    }

    public void setMaterialNum(Integer materialNum) {
        this.materialNum = materialNum;
    }

    public CourseResourceStatisticVo coursewareNum(Integer coursewareNum) {
        this.coursewareNum = coursewareNum;
        return this;
    }

    /**
     * 课件数量
     *
     * @return coursewareNum
     **/
    @ApiModelProperty(value = "课件数量")


    public Integer getCoursewareNum() {
        return coursewareNum;
    }

    public void setCoursewareNum(Integer coursewareNum) {
        this.coursewareNum = coursewareNum;
    }

    public CourseResourceStatisticVo pictureNum(Integer pictureNum) {
        this.pictureNum = pictureNum;
        return this;
    }

    /**
     * 图片数量
     *
     * @return pictureNum
     **/
    @ApiModelProperty(value = "图片数量")


    public Integer getPictureNum() {
        return pictureNum;
    }

    public void setPictureNum(Integer pictureNum) {
        this.pictureNum = pictureNum;
    }

    public CourseResourceStatisticVo videoNum(Integer videoNum) {
        this.videoNum = videoNum;
        return this;
    }

    /**
     * 视频数量
     *
     * @return videoNum
     **/
    @ApiModelProperty(value = "视频数量")


    public Integer getVideoNum() {
        return videoNum;
    }

    public void setVideoNum(Integer videoNum) {
        this.videoNum = videoNum;
    }

    public CourseResourceStatisticVo courseStructureId(String courseStructureId) {
        this.courseStructureId = courseStructureId;
        return this;
    }

    /**
     * 课程结构ID
     *
     * @return courseStructureId
     **/
    @ApiModelProperty(value = "课程结构ID")


    public String getCourseStructureId() {
        return courseStructureId;
    }

    public void setCourseStructureId(String courseStructureId) {
        this.courseStructureId = courseStructureId;
    }

    public CourseResourceStatisticVo collegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
        return this;
    }

    /**
     * 学院编号
     *
     * @return collegeCode
     **/
    @ApiModelProperty(value = "学院编号")


    public String getCollegeCode() {
        return collegeCode;
    }

    public void setCollegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
    }

    public CourseResourceStatisticVo useState(String useState) {
        this.useState = useState;
        return this;
    }

    /**
     * 课程是否删除
     *
     * @return useState
     **/
    @ApiModelProperty(value = "课程是否删除")


    public String getUseState() {
        return useState;
    }

    public void setUseState(String useState) {
        this.useState = useState;
    }

    public CourseResourceStatisticVo courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程ID
     *
     * @return courseId
     **/
    @ApiModelProperty(value = "课程ID")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseResourceStatisticVo courseResourceStatisticVo = (CourseResourceStatisticVo) o;
        return Objects.equals(this.courseName, courseResourceStatisticVo.courseName) &&
                Objects.equals(this.courseCollege, courseResourceStatisticVo.courseCollege) &&
                Objects.equals(this.courseCode, courseResourceStatisticVo.courseCode) &&
                Objects.equals(this.materialNum, courseResourceStatisticVo.materialNum) &&
                Objects.equals(this.coursewareNum, courseResourceStatisticVo.coursewareNum) &&
                Objects.equals(this.pictureNum, courseResourceStatisticVo.pictureNum) &&
                Objects.equals(this.videoNum, courseResourceStatisticVo.videoNum) &&
                Objects.equals(this.courseStructureId, courseResourceStatisticVo.courseStructureId) &&
                Objects.equals(this.collegeCode, courseResourceStatisticVo.collegeCode) &&
                Objects.equals(this.useState, courseResourceStatisticVo.useState) &&
                Objects.equals(this.courseId, courseResourceStatisticVo.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                courseName, courseCollege, courseCode, materialNum, coursewareNum,
                pictureNum, videoNum, courseStructureId, collegeCode, useState, courseId
        );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseResourceStatisticVo {\n");

        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    courseCollege: ").append(toIndentedString(courseCollege)).append("\n");
        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
        sb.append("    materialNum: ").append(toIndentedString(materialNum)).append("\n");
        sb.append("    coursewareNum: ").append(toIndentedString(coursewareNum)).append("\n");
        sb.append("    pictureNum: ").append(toIndentedString(pictureNum)).append("\n");
        sb.append("    videoNum: ").append(toIndentedString(videoNum)).append("\n");
        sb.append("    courseStructureId: ").append(toIndentedString(courseStructureId)).append("\n");
        sb.append("    collegeCode: ").append(toIndentedString(collegeCode)).append("\n");
        sb.append("    useState: ").append(toIndentedString(useState)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
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


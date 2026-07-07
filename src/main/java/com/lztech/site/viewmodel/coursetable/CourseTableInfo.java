package com.lztech.site.viewmodel.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseTableInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-12-07T09:06:46.524Z")


public class CourseTableInfo {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("courseName")
    private String courseName = null;

    @JsonProperty("studentType")
    private Integer studentType = null;

    @JsonProperty("courseTeacherName")
    private String courseTeacherName = null;

    @JsonProperty("courseCode")
    private String courseCode = null;

    @JsonProperty("schoolYear")
    private String schoolYear = null;

    @JsonProperty("term")
    private Integer term = null;

    @JsonProperty("source")
    private Integer source = null;

    @JsonProperty("weekType")
    private Integer weekType = null;

    @JsonProperty("groupId")
    private String groupId = null;

    @JsonProperty("groupName")
    private String groupName = null;

    @JsonProperty("groupNo")
    private String groupNo = null;

    @JsonProperty("collegeId")
    private String collegeId = null;
    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("courseCategoryId")
    private String courseCategoryId = null;

    @JsonProperty("courseCategory")
    private String courseCategory = null;

    @JsonProperty("courseTypeId")
    private Integer courseTypeId = null;

    @JsonProperty("courseTypeName")
    private String courseTypeName = null;


    @JsonProperty("groupMemberNum")
    private Integer groupMemberNum = null;

    @JsonProperty("courseTableDetails")
    @Valid
    private List<CourseTableDetailInfo> courseTableDetails = null;

    public Integer getGroupMemberNum() {
        return groupMemberNum;
    }

    public void setGroupMemberNum(Integer groupMemberNum) {
        this.groupMemberNum = groupMemberNum;
    }

    public CourseTableInfo id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 课表id
     *
     * @return id
     **/
    @ApiModelProperty(value = "课表id")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CourseTableInfo courseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    /**
     * 课程id
     *
     * @return courseId
     **/
    @ApiModelProperty(value = "课程id")


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public CourseTableInfo courseName(String courseName) {
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

    public CourseTableInfo studentType(Integer studentType) {
        this.studentType = studentType;
        return this;
    }

    /**
     * 课程开课类型：0：本科生 1：研究生
     *
     * @return studentType
     **/
    @ApiModelProperty(value = "课程开课类型：0：本科生 1：研究生")


    public Integer getStudentType() {
        return studentType;
    }

    public void setStudentType(Integer studentType) {
        this.studentType = studentType;
    }

    public CourseTableInfo courseTeacherName(String courseTeacherName) {
        this.courseTeacherName = courseTeacherName;
        return this;
    }

    /**
     * 课程老师姓名（多个逗号分割）
     *
     * @return courseTeacherName
     **/
    @ApiModelProperty(value = "课程老师姓名（多个逗号分割）")


    public String getCourseTeacherName() {
        return courseTeacherName;
    }

    public void setCourseTeacherName(String courseTeacherName) {
        this.courseTeacherName = courseTeacherName;
    }

    public CourseTableInfo courseCode(String courseCode) {
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

    public CourseTableInfo schoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
        return this;
    }

    /**
     * 学年
     *
     * @return schoolYear
     **/
    @ApiModelProperty(value = "学年")


    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public CourseTableInfo term(Integer term) {
        this.term = term;
        return this;
    }

    /**
     * 学期
     *
     * @return term
     **/
    @ApiModelProperty(value = "学期")


    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public CourseTableInfo source(Integer source) {
        this.source = source;
        return this;
    }

    /**
     * 来源
     *
     * @return source
     **/
    @ApiModelProperty(value = "来源")


    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public CourseTableInfo weekType(Integer weekType) {
        this.weekType = weekType;
        return this;
    }

    /**
     * 周类型
     *
     * @return weekType
     **/
    @ApiModelProperty(value = "周类型")


    public Integer getWeekType() {
        return weekType;
    }

    public void setWeekType(Integer weekType) {
        this.weekType = weekType;
    }

    public CourseTableInfo groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     * 组id
     *
     * @return groupId
     **/
    @ApiModelProperty(value = "组id")


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public CourseTableInfo groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    /**
     * 组名称
     *
     * @return groupName
     **/
    @ApiModelProperty(value = "组名称")


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public CourseTableInfo groupNo(String groupNo) {
        this.groupNo = groupNo;
        return this;
    }

    /**
     * 组编号
     *
     * @return groupNo
     **/
    @ApiModelProperty(value = "组编号")


    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public CourseTableInfo collegeId(String collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    /**
     * 学院id
     *
     * @return collegeId
     **/
    @ApiModelProperty(value = "学院id")


    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }


    public CourseTableInfo collegeName(String collegeName) {
        this.collegeName = collegeName;
        return this;
    }

    /**
     * 学院名称
     *
     * @return collegeName
     **/
    @ApiModelProperty(value = "学院名称")


    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public CourseTableInfo courseCategoryId(String courseCategoryId) {
        this.courseCategoryId = courseCategoryId;
        return this;
    }

    /**
     * 课程类别Id
     *
     * @return courseCategoryId
     **/
    @ApiModelProperty(value = "课程类别Id")


    public String getCourseCategoryId() {
        return courseCategoryId;
    }

    public void setCourseCategoryId(String courseCategoryId) {
        this.courseCategoryId = courseCategoryId;
    }

    public CourseTableInfo courseCategory(String courseCategory) {
        this.courseCategory = courseCategory;
        return this;
    }

    /**
     * 课程类别
     *
     * @return courseCategory
     **/
    @ApiModelProperty(value = "课程类别")


    public String getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(String courseCategory) {
        this.courseCategory = courseCategory;
    }


    public CourseTableInfo courseTypeId(Integer courseTypeId) {
        this.courseTypeId = courseTypeId;
        return this;
    }

    /**
     * 课程类型id
     *
     * @return courseTypeId
     **/
    @ApiModelProperty(value = "课程类型id")


    public Integer getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Integer courseTypeId) {
        this.courseTypeId = courseTypeId;
    }


    public CourseTableInfo courseTypeName(String courseTypeName) {
        this.courseTypeName = courseTypeName;
        return this;
    }

    /**
     * 课程类型名称
     *
     * @return courseTypeName
     **/
    @ApiModelProperty(value = "课程类型名称")


    public String getCourseTypeName() {
        return courseTypeName;
    }

    public void setCourseTypeName(String courseTypeName) {
        this.courseTypeName = courseTypeName;
    }


    public CourseTableInfo courseTableDetails(List<CourseTableDetailInfo> courseTableDetails) {
        this.courseTableDetails = courseTableDetails;
        return this;
    }

    public CourseTableInfo addCourseTableDetailsItem(CourseTableDetailInfo courseTableDetailsItem) {
        if (this.courseTableDetails == null) {
            this.courseTableDetails = new ArrayList<CourseTableDetailInfo>();
        }
        this.courseTableDetails.add(courseTableDetailsItem);
        return this;
    }

    /**
     * 课表明细列表
     *
     * @return courseTableDetails
     **/
    @ApiModelProperty(value = "课表明细列表")

    @Valid

    public List<CourseTableDetailInfo> getCourseTableDetails() {
        return courseTableDetails;
    }

    public void setCourseTableDetails(List<CourseTableDetailInfo> courseTableDetails) {
        this.courseTableDetails = courseTableDetails;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableInfo courseTableInfo = (CourseTableInfo) o;
        return Objects.equals(this.id, courseTableInfo.id) && Objects.equals(this.courseId, courseTableInfo.courseId)
                && Objects.equals(this.courseName, courseTableInfo.courseName)
                && Objects.equals(this.studentType, courseTableInfo.studentType)
                && Objects.equals(this.courseTeacherName, courseTableInfo.courseTeacherName)
                && Objects.equals(this.courseCode, courseTableInfo.courseCode)
                && Objects.equals(this.schoolYear, courseTableInfo.schoolYear)
                && Objects.equals(this.term, courseTableInfo.term) && Objects.equals(this.source, courseTableInfo.source)
                && Objects.equals(this.weekType, courseTableInfo.weekType) && Objects.equals(this.groupId, courseTableInfo.groupId)
                && Objects.equals(this.groupName, courseTableInfo.groupName) && Objects.equals(this.groupNo, courseTableInfo.groupNo)
                && Objects.equals(this.collegeId, courseTableInfo.collegeId) && Objects.equals(this.collegeName, courseTableInfo.collegeName)
                && Objects.equals(this.courseCategoryId, courseTableInfo.courseCategoryId)
                && Objects.equals(this.courseCategory, courseTableInfo.courseCategory)
                && Objects.equals(this.courseTypeId, courseTableInfo.courseTypeId)
                && Objects.equals(this.courseTypeName, courseTableInfo.courseTypeName)
                && Objects.equals(this.courseTableDetails, courseTableInfo.courseTableDetails);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, courseName, studentType, courseTeacherName, courseCode, schoolYear,
                term, source, weekType, groupId, groupNo, groupName, collegeId, collegeName, courseCategoryId, courseCategory,
                courseTypeId, courseTypeName, courseTableDetails);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableInfo {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
        sb.append("    studentType: ").append(toIndentedString(studentType)).append("\n");
        sb.append("    courseTeacherName: ").append(toIndentedString(courseTeacherName)).append("\n");
        sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
        sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
        sb.append("    term: ").append(toIndentedString(term)).append("\n");
        sb.append("    source: ").append(toIndentedString(source)).append("\n");
        sb.append("    weekType: ").append(toIndentedString(weekType)).append("\n");
        sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
        sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
        sb.append("    groupNo: ").append(toIndentedString(groupNo)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    courseCategoryId: ").append(toIndentedString(courseCategoryId)).append("\n");
        sb.append("    courseCategory: ").append(toIndentedString(courseCategory)).append("\n");
        sb.append("    courseTypeId: ").append(toIndentedString(courseTypeId)).append("\n");
        sb.append("    courseTypeName: ").append(toIndentedString(courseTypeName)).append("\n");
        sb.append("    courseTableDetails: ").append(toIndentedString(courseTableDetails)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}


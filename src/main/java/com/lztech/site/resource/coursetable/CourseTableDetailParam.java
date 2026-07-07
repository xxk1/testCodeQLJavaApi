package com.lztech.site.resource.coursetable;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * CourseTableDetailParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-12-16T08:50:22.885Z")


public class CourseTableDetailParam   {
  @JsonProperty("studentType")
  private Integer studentType = null;

  @JsonProperty("buildingId")
  private String buildingId = null;

  @JsonProperty("collegeId")
  private String collegeId = null;

  @JsonProperty("segment")
  private Integer segment = null;

  @JsonProperty("courseDate")
  private String courseDate = null;

  @JsonProperty("teacherNameOrCourseName")
  private String teacherNameOrCourseName = null;

  @JsonProperty("schoolYear")
  private String schoolYear = null;

  @JsonProperty("term")
  private Integer term = null;

  @JsonProperty("week")
  private Integer week = null;

  @JsonProperty("weekNum")
  private Integer weekNum = null;

  @JsonProperty("page")
  private Integer page = null;

  @JsonProperty("row")
  private Integer row = null;

  @JsonProperty("classRooms")
  @Valid
  private List<String> classRooms = null;

  public CourseTableDetailParam studentType(Integer studentType) {
    this.studentType = studentType;
    return this;
  }

  /**
   * 开课类型
   * @return studentType
  **/
  @ApiModelProperty(value = "开课类型")


  public Integer getStudentType() {
    return studentType;
  }

  public void setStudentType(Integer studentType) {
    this.studentType = studentType;
  }

  public CourseTableDetailParam buildingId(String buildingId) {
    this.buildingId = buildingId;
    return this;
  }

  /**
   * buildingId
   * @return buildingId
  **/
  @ApiModelProperty(value = "buildingId")


  public String getBuildingId() {
    return buildingId;
  }

  public void setBuildingId(String buildingId) {
    this.buildingId = buildingId;
  }

  public CourseTableDetailParam collegeId(String collegeId) {
    this.collegeId = collegeId;
    return this;
  }

  /**
   * 学院ID
   * @return collegeId
  **/
  @ApiModelProperty(value = "学院ID")


  public String getCollegeId() {
    return collegeId;
  }

  public void setCollegeId(String collegeId) {
    this.collegeId = collegeId;
  }

  public CourseTableDetailParam segment(Integer segment) {
    this.segment = segment;
    return this;
  }

  /**
   * 节次
   * @return segment
  **/
  @ApiModelProperty(value = "节次")


  public Integer getSegment() {
    return segment;
  }

  public void setSegment(Integer segment) {
    this.segment = segment;
  }

  public CourseTableDetailParam courseDate(String courseDate) {
    this.courseDate = courseDate;
    return this;
  }

  /**
   * 日期
   * @return courseDate
  **/
  @ApiModelProperty(value = "日期")


  public String getCourseDate() {
    return courseDate;
  }

  public void setCourseDate(String courseDate) {
    this.courseDate = courseDate;
  }

  public CourseTableDetailParam teacherNameOrCourseName(String teacherNameOrCourseName) {
    this.teacherNameOrCourseName = teacherNameOrCourseName;
    return this;
  }

  /**
   * 老师名称/课程名称
   * @return teacherNameOrCourseName
  **/
  @ApiModelProperty(value = "老师名称/课程名称")


  public String getTeacherNameOrCourseName() {
    return teacherNameOrCourseName;
  }

  public void setTeacherNameOrCourseName(String teacherNameOrCourseName) {
    this.teacherNameOrCourseName = teacherNameOrCourseName;
  }

  public CourseTableDetailParam schoolYear(String schoolYear) {
    this.schoolYear = schoolYear;
    return this;
  }

  /**
   * 学年
   * @return schoolYear
  **/
  @ApiModelProperty(value = "学年")


  public String getSchoolYear() {
    return schoolYear;
  }

  public void setSchoolYear(String schoolYear) {
    this.schoolYear = schoolYear;
  }

  public CourseTableDetailParam term(Integer term) {
    this.term = term;
    return this;
  }

  /**
   * 学期
   * @return term
  **/
  @ApiModelProperty(value = "学期")


  public Integer getTerm() {
    return term;
  }

  public void setTerm(Integer term) {
    this.term = term;
  }

  public CourseTableDetailParam week(Integer week) {
    this.week = week;
    return this;
  }

  /**
   * 周次
   * @return week
  **/
  @ApiModelProperty(value = "周次")


  public Integer getWeek() {
    return week;
  }

  public void setWeek(Integer week) {
    this.week = week;
  }

  public CourseTableDetailParam weekNum(Integer weekNum) {
    this.weekNum = weekNum;
    return this;
  }

  /**
   * 周几
   * @return weekNum
  **/
  @ApiModelProperty(value = "周几")


  public Integer getWeekNum() {
    return weekNum;
  }

  public void setWeekNum(Integer weekNum) {
    this.weekNum = weekNum;
  }

  public CourseTableDetailParam page(Integer page) {
    this.page = page;
    return this;
  }

  /**
   * 当前页
   * @return page
  **/
  @ApiModelProperty(value = "当前页")


  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public CourseTableDetailParam row(Integer row) {
    this.row = row;
    return this;
  }

  /**
   * 每页个数
   * @return row
  **/
  @ApiModelProperty(value = "每页个数")


  public Integer getRow() {
    return row;
  }

  public void setRow(Integer row) {
    this.row = row;
  }

  public CourseTableDetailParam classRooms(List<String> classRooms) {
    this.classRooms = classRooms;
    return this;
  }

  public CourseTableDetailParam addClassRoomsItem(String classRoomsItem) {
    if (this.classRooms == null) {
      this.classRooms = new ArrayList<String>();
    }
    this.classRooms.add(classRoomsItem);
    return this;
  }

  /**
   * Get classRooms
   * @return classRooms
  **/
  @ApiModelProperty(value = "")


  public List<String> getClassRooms() {
    return classRooms;
  }

  public void setClassRooms(List<String> classRooms) {
    this.classRooms = classRooms;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseTableDetailParam courseTableDetailParam = (CourseTableDetailParam) o;
    return Objects.equals(this.studentType, courseTableDetailParam.studentType) &&
        Objects.equals(this.buildingId, courseTableDetailParam.buildingId) &&
        Objects.equals(this.collegeId, courseTableDetailParam.collegeId) &&
        Objects.equals(this.segment, courseTableDetailParam.segment) &&
        Objects.equals(this.courseDate, courseTableDetailParam.courseDate) &&
        Objects.equals(this.teacherNameOrCourseName, courseTableDetailParam.teacherNameOrCourseName) &&
        Objects.equals(this.schoolYear, courseTableDetailParam.schoolYear) &&
        Objects.equals(this.term, courseTableDetailParam.term) &&
        Objects.equals(this.week, courseTableDetailParam.week) &&
        Objects.equals(this.weekNum, courseTableDetailParam.weekNum) &&
        Objects.equals(this.page, courseTableDetailParam.page) &&
        Objects.equals(this.row, courseTableDetailParam.row) &&
        Objects.equals(this.classRooms, courseTableDetailParam.classRooms);
  }

  @Override
  public int hashCode() {
    return Objects.hash(studentType, buildingId, collegeId, segment, courseDate, teacherNameOrCourseName,
            schoolYear, term, week, weekNum, page, row, classRooms);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CourseTableDetailParam {\n");
    
    sb.append("    studentType: ").append(toIndentedString(studentType)).append("\n");
    sb.append("    buildingId: ").append(toIndentedString(buildingId)).append("\n");
    sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
    sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
    sb.append("    courseDate: ").append(toIndentedString(courseDate)).append("\n");
    sb.append("    teacherNameOrCourseName: ").append(toIndentedString(teacherNameOrCourseName)).append("\n");
    sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
    sb.append("    term: ").append(toIndentedString(term)).append("\n");
    sb.append("    week: ").append(toIndentedString(week)).append("\n");
    sb.append("    weekNum: ").append(toIndentedString(weekNum)).append("\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    row: ").append(toIndentedString(row)).append("\n");
    sb.append("    classRooms: ").append(toIndentedString(classRooms)).append("\n");
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


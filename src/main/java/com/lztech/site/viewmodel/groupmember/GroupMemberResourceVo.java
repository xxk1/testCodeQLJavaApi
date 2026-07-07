package com.lztech.site.viewmodel.groupmember;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * GroupMemberResourceVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-01-06T02:45:37.693Z")


public class GroupMemberResourceVo   {
  @JsonProperty("groupMemberId")
  private String groupMemberId = null;

  @JsonProperty("schoolYear")
  private String schoolYear = null;

  @JsonProperty("term")
  private Integer term = null;

  @JsonProperty("courseCode")
  private String courseCode = null;

  @JsonProperty("courseName")
  private String courseName = null;

  @JsonProperty("groupNo")
  private String groupNo = null;

  @JsonProperty("groupName")
  private String groupName = null;

  @JsonProperty("studentNo")
  private String studentNo = null;

  @JsonProperty("studentName")
  private String studentName = null;

  @JsonProperty("source")
  private String source = null;

  @JsonProperty("schoolYearTermNickName")
  private String schoolYearTermNickName = null;

  public GroupMemberResourceVo schoolYearTermNickName(String schoolYearTermNickName) {
      this.schoolYearTermNickName = schoolYearTermNickName;
      return this;
  }

    /**
     * 学年学期昵称
     *
     * @return schoolYearTermNickName
     **/
    @ApiModelProperty(value = "学年学期昵称")


    public String getSchoolYearTermNickName() {
        return schoolYearTermNickName;
    }

    public void setSchoolYearTermNickName(String schoolYearTermNickName) {
        this.schoolYearTermNickName = schoolYearTermNickName;
    }

  public GroupMemberResourceVo groupMemberId(String groupMemberId) {
    this.groupMemberId = groupMemberId;
    return this;
  }

  /**
   * 班级人员id
   * @return groupMemberId
  **/
  @ApiModelProperty(value = "班级人员id")


  public String getGroupMemberId() {
    return groupMemberId;
  }

  public void setGroupMemberId(String groupMemberId) {
    this.groupMemberId = groupMemberId;
  }

  public GroupMemberResourceVo schoolYear(String schoolYear) {
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

  public GroupMemberResourceVo term(Integer term) {
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

  public GroupMemberResourceVo courseCode(String courseCode) {
    this.courseCode = courseCode;
    return this;
  }

  /**
   * 课程编码
   * @return courseCode
  **/
  @ApiModelProperty(value = "课程编码")


  public String getCourseCode() {
    return courseCode;
  }

  public void setCourseCode(String courseCode) {
    this.courseCode = courseCode;
  }

  public GroupMemberResourceVo courseName(String courseName) {
    this.courseName = courseName;
    return this;
  }

  /**
   * 课程名称
   * @return courseName
  **/
  @ApiModelProperty(value = "课程名称")


  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public GroupMemberResourceVo groupNo(String groupNo) {
    this.groupNo = groupNo;
    return this;
  }

  /**
   * 班级编号
   * @return groupNo
  **/
  @ApiModelProperty(value = "班级编号")


  public String getGroupNo() {
    return groupNo;
  }

  public void setGroupNo(String groupNo) {
    this.groupNo = groupNo;
  }

  public GroupMemberResourceVo groupName(String groupName) {
    this.groupName = groupName;
    return this;
  }

  /**
   * 班级名称
   * @return groupName
  **/
  @ApiModelProperty(value = "班级名称")


  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public GroupMemberResourceVo studentNo(String studentNo) {
    this.studentNo = studentNo;
    return this;
  }

  /**
   * 学生学号
   * @return studentNo
  **/
  @ApiModelProperty(value = "学生学号")


  public String getStudentNo() {
    return studentNo;
  }

  public void setStudentNo(String studentNo) {
    this.studentNo = studentNo;
  }

  public GroupMemberResourceVo studentName(String studentName) {
    this.studentName = studentName;
    return this;
  }

  /**
   * 学生姓名
   * @return studentName
  **/
  @ApiModelProperty(value = "学生姓名")


  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }

  public GroupMemberResourceVo source(String source) {
    this.source = source;
    return this;
  }

  /**
   * 数据来源
   * @return source
  **/
  @ApiModelProperty(value = "数据来源")


  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GroupMemberResourceVo groupMemberResourceVo = (GroupMemberResourceVo) o;
    return Objects.equals(this.groupMemberId, groupMemberResourceVo.groupMemberId) &&
        Objects.equals(this.schoolYear, groupMemberResourceVo.schoolYear) &&
        Objects.equals(this.term, groupMemberResourceVo.term) &&
        Objects.equals(this.schoolYearTermNickName, groupMemberResourceVo.schoolYearTermNickName) &&
        Objects.equals(this.courseCode, groupMemberResourceVo.courseCode) &&
        Objects.equals(this.courseName, groupMemberResourceVo.courseName) &&
        Objects.equals(this.groupNo, groupMemberResourceVo.groupNo) &&
        Objects.equals(this.groupName, groupMemberResourceVo.groupName) &&
        Objects.equals(this.studentNo, groupMemberResourceVo.studentNo) &&
        Objects.equals(this.studentName, groupMemberResourceVo.studentName) &&
        Objects.equals(this.source, groupMemberResourceVo.source);
  }

  @Override
  public int hashCode() {
    return Objects.hash(groupMemberId, schoolYear, term, schoolYearTermNickName, courseCode,
            courseName, groupNo, groupName, studentNo, studentName, source);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GroupMemberResourceVo {\n");
    
    sb.append("    groupMemberId: ").append(toIndentedString(groupMemberId)).append("\n");
    sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
    sb.append("    term: ").append(toIndentedString(term)).append("\n");
    sb.append("    schoolYearTermNickName: ").append(toIndentedString(schoolYearTermNickName)).append("\n");
    sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
    sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
    sb.append("    groupNo: ").append(toIndentedString(groupNo)).append("\n");
    sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
    sb.append("    studentNo: ").append(toIndentedString(studentNo)).append("\n");
    sb.append("    studentName: ").append(toIndentedString(studentName)).append("\n");
    sb.append("    source: ").append(toIndentedString(source)).append("\n");
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


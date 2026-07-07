package com.lztech.site.viewmodel.teachingreport;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * OfferingCourseRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2023-12-01T02:54:25.418Z")


public class OfferingCourseRequest   {
  @JsonProperty("schoolYear")
  private String schoolYear = null;

  @JsonProperty("term")
  private Integer term = null;

  @JsonProperty("startDate")
  private String startDate = null;

  @JsonProperty("endDate")
  private String endDate = null;
  @JsonProperty("studentType")
  private String studentType = null;
  @JsonProperty("colleges")
  @Valid
  private List<OfferingCourseCollegeRequest> colleges = null;

  public OfferingCourseRequest schoolYear(String schoolYear) {
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

  public OfferingCourseRequest term(Integer term) {
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

  public OfferingCourseRequest startDate(String startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * 开始日期（格式：yyyy-MM-dd）
   * @return startDate
  **/
  @ApiModelProperty(value = "开始日期（格式：yyyy-MM-dd）")


  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public OfferingCourseRequest studentType(String studentType) {
    this.studentType = studentType;
    return this;
  }

  /**
   * 学生类型：不填为全部，0=本科生，1=研究生
   * @return studentType
   **/
  @ApiModelProperty(value = "学生类型：不填为全部，0=本科生，1=研究生")


  public String getStudentType() {
    return studentType;
  }

  public void setStudentType(String studentType) {
    this.studentType = studentType;
  }

  public OfferingCourseRequest endDate(String endDate) {
    this.endDate = endDate;
    return this;
  }

  /**
   * 结束日期（格式：yyyy-MM-dd）
   * @return endDate
  **/
  @ApiModelProperty(value = "结束日期（格式：yyyy-MM-dd）")


  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public OfferingCourseRequest colleges(List<OfferingCourseCollegeRequest> colleges) {
    this.colleges = colleges;
    return this;
  }

  public OfferingCourseRequest addCollegesItem(OfferingCourseCollegeRequest collegesItem) {
    if (this.colleges == null) {
      this.colleges = new ArrayList<OfferingCourseCollegeRequest>();
    }
    this.colleges.add(collegesItem);
    return this;
  }

  /**
   * Get colleges
   * @return colleges
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<OfferingCourseCollegeRequest> getColleges() {
    return colleges;
  }

  public void setColleges(List<OfferingCourseCollegeRequest> colleges) {
    this.colleges = colleges;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OfferingCourseRequest offeringCourseRequest = (OfferingCourseRequest) o;
    return Objects.equals(this.schoolYear, offeringCourseRequest.schoolYear) &&
        Objects.equals(this.term, offeringCourseRequest.term) &&
        Objects.equals(this.startDate, offeringCourseRequest.startDate) &&
        Objects.equals(this.endDate, offeringCourseRequest.endDate) &&
        Objects.equals(this.studentType, offeringCourseRequest.studentType) &&
        Objects.equals(this.colleges, offeringCourseRequest.colleges);
  }

  @Override
  public int hashCode() {
    return Objects.hash(schoolYear, term, startDate, endDate,studentType, colleges);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OfferingCourseRequest {\n");
    
    sb.append("    schoolYear: ").append(toIndentedString(schoolYear)).append("\n");
    sb.append("    term: ").append(toIndentedString(term)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    studentType: ").append(toIndentedString(studentType)).append("\n");
    sb.append("    colleges: ").append(toIndentedString(colleges)).append("\n");
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


package com.lztech.site.viewmodel.evaluation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * PolymerizeCourseInfoParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-10-17T05:44:39.370Z")


public class PolymerizeCourseInfoParam   {
  @JsonProperty("termBaseModelList")
  @Valid
  private List<TermBaseModel> termBaseModelList = null;

  @JsonProperty("collegeIds")
  private String collegeIds = null;

  @JsonProperty("studentTypes")
  private String studentTypes = null;

  public PolymerizeCourseInfoParam termBaseModelList(List<TermBaseModel> termBaseModelList) {
    this.termBaseModelList = termBaseModelList;
    return this;
  }

  public PolymerizeCourseInfoParam addTermBaseModelListItem(TermBaseModel termBaseModelListItem) {
    if (this.termBaseModelList == null) {
      this.termBaseModelList = new ArrayList<TermBaseModel>();
    }
    this.termBaseModelList.add(termBaseModelListItem);
    return this;
  }

  /**
   * 学期基本模型列表
   * @return termBaseModelList
  **/
  @ApiModelProperty(value = "学期基本模型列表")

  @Valid

  public List<TermBaseModel> getTermBaseModelList() {
    return termBaseModelList;
  }

  public void setTermBaseModelList(List<TermBaseModel> termBaseModelList) {
    this.termBaseModelList = termBaseModelList;
  }

  public PolymerizeCourseInfoParam collegeIds(String collegeIds) {
    this.collegeIds = collegeIds;
    return this;
  }

  /**
   * 学院ids(逗号分隔)
   * @return collegeIds
  **/
  @ApiModelProperty(value = "学院ids(逗号分隔)")


  public String getCollegeIds() {
    return collegeIds;
  }

  public void setCollegeIds(String collegeIds) {
    this.collegeIds = collegeIds;
  }

  public PolymerizeCourseInfoParam studentTypes(String studentTypes) {
    this.studentTypes = studentTypes;
    return this;
  }

  /**
   * 学生类型（学生类型拼接(逗号分隔,0-本科生，1-研究生),'' or null-全部）
   * @return studentTypes
  **/
  @ApiModelProperty(value = "学生类型（学生类型拼接(逗号分隔,0-本科生，1-研究生),'' or null-全部）")


  public String getStudentTypes() {
    return studentTypes;
  }

  public void setStudentTypes(String studentTypes) {
    this.studentTypes = studentTypes;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PolymerizeCourseInfoParam polymerizeCourseInfoParam = (PolymerizeCourseInfoParam) o;
    return Objects.equals(this.termBaseModelList, polymerizeCourseInfoParam.termBaseModelList) &&
        Objects.equals(this.collegeIds, polymerizeCourseInfoParam.collegeIds) &&
        Objects.equals(this.studentTypes, polymerizeCourseInfoParam.studentTypes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(termBaseModelList, collegeIds, studentTypes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PolymerizeCourseInfoParam {\n");
    
    sb.append("    termBaseModelList: ").append(toIndentedString(termBaseModelList)).append("\n");
    sb.append("    collegeIds: ").append(toIndentedString(collegeIds)).append("\n");
    sb.append("    studentTypes: ").append(toIndentedString(studentTypes)).append("\n");
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


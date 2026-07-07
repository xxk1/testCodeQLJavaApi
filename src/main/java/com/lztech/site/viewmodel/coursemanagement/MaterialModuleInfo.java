package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * MaterialModuleInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-01-13T08:06:36.802Z")




public class MaterialModuleInfo   {
  @JsonProperty("courseId")
  private String courseId = null;

  @JsonProperty("courseCode")
  private String courseCode = null;

  @JsonProperty("courseName")
  private String courseName = null;

  @JsonProperty("moduleName")
  private String moduleName = null;

  @JsonProperty("moduleStates")
  private Integer moduleStates = null;

  @JsonProperty("moduleContent")
  private String moduleContent = null;

  public MaterialModuleInfo courseId(String courseId) {
    this.courseId = courseId;
    return this;
  }

  /**
   * 课程id
   * @return courseId
  **/
  @ApiModelProperty(value = "课程id")


  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }

  public MaterialModuleInfo courseCode(String courseCode) {
    this.courseCode = courseCode;
    return this;
  }

  /**
   * 课程编号
   * @return courseCode
  **/
  @ApiModelProperty(value = "课程编号")


  public String getCourseCode() {
    return courseCode;
  }

  public void setCourseCode(String courseCode) {
    this.courseCode = courseCode;
  }

  public MaterialModuleInfo courseName(String courseName) {
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

  public MaterialModuleInfo moduleName(String moduleName) {
    this.moduleName = moduleName;
    return this;
  }

  /**
   * 模块名称
   * @return moduleName
  **/
  @ApiModelProperty(value = "模块名称")


  public String getModuleName() {
    return moduleName;
  }

  public void setModuleName(String moduleName) {
    this.moduleName = moduleName;
  }

  public MaterialModuleInfo moduleStates(Integer moduleStates) {
    this.moduleStates = moduleStates;
    return this;
  }

  /**
   * 模块状态：0 未完成；1 完成
   * @return moduleStates
  **/
  @ApiModelProperty(value = "模块状态：0 未完成；1 完成")


  public Integer getModuleStates() {
    return moduleStates;
  }

  public void setModuleStates(Integer moduleStates) {
    this.moduleStates = moduleStates;
  }

  public MaterialModuleInfo moduleContent(String moduleContent) {
    this.moduleContent = moduleContent;
    return this;
  }

  /**
   * 模块内容
   * @return moduleContent
  **/
  @ApiModelProperty(value = "模块内容")


  public String getModuleContent() {
    return moduleContent;
  }

  public void setModuleContent(String moduleContent) {
    this.moduleContent = moduleContent;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MaterialModuleInfo materialModuleInfo = (MaterialModuleInfo) o;
    return Objects.equals(this.courseId, materialModuleInfo.courseId) &&
        Objects.equals(this.courseCode, materialModuleInfo.courseCode) &&
        Objects.equals(this.courseName, materialModuleInfo.courseName) &&
        Objects.equals(this.moduleName, materialModuleInfo.moduleName) &&
        Objects.equals(this.moduleStates, materialModuleInfo.moduleStates) &&
        Objects.equals(this.moduleContent, materialModuleInfo.moduleContent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseId, courseCode, courseName, moduleName, moduleStates, moduleContent);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MaterialModuleInfo {\n");
    
    sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
    sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
    sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
    sb.append("    moduleName: ").append(toIndentedString(moduleName)).append("\n");
    sb.append("    moduleStates: ").append(toIndentedString(moduleStates)).append("\n");
    sb.append("    moduleContent: ").append(toIndentedString(moduleContent)).append("\n");
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


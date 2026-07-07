package com.lztech.site.viewmodel.projectclassification;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * ClassificationResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-10-16T02:15:08.542Z")




public class ClassificationResource   {
  @JsonProperty("userId")
  private String userId = null;

  @JsonProperty("userName")
  private String userName = null;

  @JsonProperty("classificationName")
  private String classificationName = null;

  public ClassificationResource userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * 用户id
   * @return userId
  **/
  @ApiModelProperty(value = "用户id")


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public ClassificationResource userName(String userName) {
    this.userName = userName;
    return this;
  }

  /**
   * 用户姓名
   * @return userName
  **/
  @ApiModelProperty(value = "用户姓名")


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public ClassificationResource classificationName(String classificationName) {
    this.classificationName = classificationName;
    return this;
  }

  /**
   * 分类名称
   * @return classificationName
  **/
  @ApiModelProperty(value = "分类名称")


  public String getClassificationName() {
    return classificationName;
  }

  public void setClassificationName(String classificationName) {
    this.classificationName = classificationName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClassificationResource classificationResource = (ClassificationResource) o;
    return Objects.equals(this.userId, classificationResource.userId) &&
        Objects.equals(this.userName, classificationResource.userName) &&
        Objects.equals(this.classificationName, classificationResource.classificationName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, userName, classificationName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClassificationResource {\n");
    
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    classificationName: ").append(toIndentedString(classificationName)).append("\n");
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


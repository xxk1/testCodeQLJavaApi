package com.lztech.site.viewmodel.questionlibrary;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lztech.site.viewmodel.courseknowledgegraph.QuestionFileResource;
import com.lztech.site.viewmodel.courseknowledgegraph.QuestionOptionResource;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * QuestionLibraryResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-03-19T02:58:49.024Z")


public class QuestionLibraryResource   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("questionStem")
  private String questionStem = null;

  @JsonProperty("questionStemText")
  private String questionStemText = null;

  @JsonProperty("questionTypeName")
  private String questionTypeName = null;

  @JsonProperty("questionTypeIndex")
  private Integer questionTypeIndex = null;

  @JsonProperty("questionComplexityName")
  private String questionComplexityName = null;

  @JsonProperty("questionComplexityIndex")
  private Integer questionComplexityIndex = null;

  @JsonProperty("questionAnalysis")
  private String questionAnalysis = null;

  @JsonProperty("creatorId")
  private String creatorId = null;

  @JsonProperty("creatorName")
  private String creatorName = null;

  @JsonProperty("courseId")
  private String courseId = null;

  @JsonProperty("courseName")
  private String courseName = null;

  @JsonProperty("courseCode")
  private String courseCode = null;

  @JsonProperty("courseStructureId")
  private String courseStructureId = null;

  @JsonProperty("checkQuestionSort")
  private Boolean checkQuestionSort = null;

  @JsonProperty("checkTextTransform")
  private Boolean checkTextTransform = null;

  @JsonProperty("checkFuzzyMatching")
  private Boolean checkFuzzyMatching = null;

  @JsonProperty("questionFileList")
  @Valid
  private List<QuestionFileResource> questionFileList = null;

  @JsonProperty("optionList")
  @Valid
  private List<QuestionOptionResource> optionList = null;

  @JsonProperty("showOrder")
  private Integer showOrder = null;

  public QuestionLibraryResource id(String id) {
    this.id = id;
    return this;
  }

  /**
   * 题目id
   * @return id
  **/
  @ApiModelProperty(value = "题目id")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public QuestionLibraryResource questionStem(String questionStem) {
    this.questionStem = questionStem;
    return this;
  }

  /**
   * 题干
   * @return questionStem
  **/
  @ApiModelProperty(value = "题干")


  public String getQuestionStem() {
    return questionStem;
  }

  public void setQuestionStem(String questionStem) {
    this.questionStem = questionStem;
  }

  public QuestionLibraryResource questionStemText(String questionStemText) {
    this.questionStemText = questionStemText;
    return this;
  }

  /**
   * 题干（不包含富文本样式）
   * @return questionStemText
  **/
  @ApiModelProperty(value = "题干（不包含富文本样式）")


  public String getQuestionStemText() {
    return questionStemText;
  }

  public void setQuestionStemText(String questionStemText) {
    this.questionStemText = questionStemText;
  }

  public QuestionLibraryResource questionTypeName(String questionTypeName) {
    this.questionTypeName = questionTypeName;
    return this;
  }

  /**
   * 题目类型名称 
   * @return questionTypeName
  **/
  @ApiModelProperty(value = "题目类型名称 ")


  public String getQuestionTypeName() {
    return questionTypeName;
  }

  public void setQuestionTypeName(String questionTypeName) {
    this.questionTypeName = questionTypeName;
  }

  public QuestionLibraryResource questionTypeIndex(Integer questionTypeIndex) {
    this.questionTypeIndex = questionTypeIndex;
    return this;
  }

  /**
   * 题目类型 0：单选；1：多选；2：判断；3：填空；4：简答
   * @return questionTypeIndex
  **/
  @ApiModelProperty(value = "题目类型 0：单选；1：多选；2：判断；3：填空；4：简答")


  public Integer getQuestionTypeIndex() {
    return questionTypeIndex;
  }

  public void setQuestionTypeIndex(Integer questionTypeIndex) {
    this.questionTypeIndex = questionTypeIndex;
  }

  public QuestionLibraryResource questionComplexityName(String questionComplexityName) {
    this.questionComplexityName = questionComplexityName;
    return this;
  }

  /**
   * 题目难易度名称
   * @return questionComplexityName
  **/
  @ApiModelProperty(value = "题目难易度名称")


  public String getQuestionComplexityName() {
    return questionComplexityName;
  }

  public void setQuestionComplexityName(String questionComplexityName) {
    this.questionComplexityName = questionComplexityName;
  }

  public QuestionLibraryResource questionComplexityIndex(Integer questionComplexityIndex) {
    this.questionComplexityIndex = questionComplexityIndex;
    return this;
  }

  /**
   * 题目难易度 0：简单；1：一般；2：困难
   * @return questionComplexityIndex
  **/
  @ApiModelProperty(value = "题目难易度 0：简单；1：一般；2：困难")


  public Integer getQuestionComplexityIndex() {
    return questionComplexityIndex;
  }

  public void setQuestionComplexityIndex(Integer questionComplexityIndex) {
    this.questionComplexityIndex = questionComplexityIndex;
  }

  public QuestionLibraryResource questionAnalysis(String questionAnalysis) {
    this.questionAnalysis = questionAnalysis;
    return this;
  }

  /**
   * 题目解析
   * @return questionAnalysis
  **/
  @ApiModelProperty(value = "题目解析")


  public String getQuestionAnalysis() {
    return questionAnalysis;
  }

  public void setQuestionAnalysis(String questionAnalysis) {
    this.questionAnalysis = questionAnalysis;
  }

  public QuestionLibraryResource creatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  /**
   * 创建人id
   * @return creatorId
  **/
  @ApiModelProperty(value = "创建人id")


  public String getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(String creatorId) {
    this.creatorId = creatorId;
  }

  public QuestionLibraryResource creatorName(String creatorName) {
    this.creatorName = creatorName;
    return this;
  }

  /**
   * 创建人姓名
   * @return creatorName
  **/
  @ApiModelProperty(value = "创建人姓名")


  public String getCreatorName() {
    return creatorName;
  }

  public void setCreatorName(String creatorName) {
    this.creatorName = creatorName;
  }

  public QuestionLibraryResource courseId(String courseId) {
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

  public QuestionLibraryResource courseName(String courseName) {
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

  public QuestionLibraryResource courseCode(String courseCode) {
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

  public QuestionLibraryResource courseStructureId(String courseStructureId) {
    this.courseStructureId = courseStructureId;
    return this;
  }

  /**
   * 章节id
   * @return courseStructureId
  **/
  @ApiModelProperty(value = "章节id")


  public String getCourseStructureId() {
    return courseStructureId;
  }

  public void setCourseStructureId(String courseStructureId) {
    this.courseStructureId = courseStructureId;
  }

  public QuestionLibraryResource checkQuestionSort(Boolean checkQuestionSort) {
    this.checkQuestionSort = checkQuestionSort;
    return this;
  }

  /**
   * 是否区分答案先后顺序
   * @return checkQuestionSort
  **/
  @ApiModelProperty(value = "是否区分答案先后顺序")


  public Boolean isCheckQuestionSort() {
    return checkQuestionSort;
  }

  public void setCheckQuestionSort(Boolean checkQuestionSort) {
    this.checkQuestionSort = checkQuestionSort;
  }

  public QuestionLibraryResource checkTextTransform(Boolean checkTextTransform) {
    this.checkTextTransform = checkTextTransform;
    return this;
  }

  /**
   * 是否区分大小写字段
   * @return checkTextTransform
  **/
  @ApiModelProperty(value = "是否区分大小写字段")


  public Boolean isCheckTextTransform() {
    return checkTextTransform;
  }

  public void setCheckTextTransform(Boolean checkTextTransform) {
    this.checkTextTransform = checkTextTransform;
  }

  public QuestionLibraryResource checkFuzzyMatching(Boolean checkFuzzyMatching) {
    this.checkFuzzyMatching = checkFuzzyMatching;
    return this;
  }

  /**
   * 答案是否模糊匹配
   * @return checkFuzzyMatching
  **/
  @ApiModelProperty(value = "答案是否模糊匹配")


  public Boolean isCheckFuzzyMatching() {
    return checkFuzzyMatching;
  }

  public void setCheckFuzzyMatching(Boolean checkFuzzyMatching) {
    this.checkFuzzyMatching = checkFuzzyMatching;
  }

  public QuestionLibraryResource questionFileList(List<QuestionFileResource> questionFileList) {
    this.questionFileList = questionFileList;
    return this;
  }

  public QuestionLibraryResource addQuestionFileListItem(QuestionFileResource questionFileListItem) {
    if (this.questionFileList == null) {
      this.questionFileList = new ArrayList<QuestionFileResource>();
    }
    this.questionFileList.add(questionFileListItem);
    return this;
  }

  /**
   * Get questionFileList
   * @return questionFileList
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<QuestionFileResource> getQuestionFileList() {
    return questionFileList;
  }

  public void setQuestionFileList(List<QuestionFileResource> questionFileList) {
    this.questionFileList = questionFileList;
  }

  public QuestionLibraryResource optionList(List<QuestionOptionResource> optionList) {
    this.optionList = optionList;
    return this;
  }

  public QuestionLibraryResource addOptionListItem(QuestionOptionResource optionListItem) {
    if (this.optionList == null) {
      this.optionList = new ArrayList<QuestionOptionResource>();
    }
    this.optionList.add(optionListItem);
    return this;
  }

  /**
   * Get optionList
   * @return optionList
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<QuestionOptionResource> getOptionList() {
    return optionList;
  }

  public void setOptionList(List<QuestionOptionResource> optionList) {
    this.optionList = optionList;
  }

  public QuestionLibraryResource showOrder(Integer showOrder) {
    this.showOrder = showOrder;
    return this;
  }

  /**
   * 展示顺序
   * @return showOrder
  **/
  @ApiModelProperty(value = "展示顺序")


  public Integer getShowOrder() {
    return showOrder;
  }

  public void setShowOrder(Integer showOrder) {
    this.showOrder = showOrder;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuestionLibraryResource questionLibraryResource = (QuestionLibraryResource) o;
    return Objects.equals(this.id, questionLibraryResource.id) &&
        Objects.equals(this.questionStem, questionLibraryResource.questionStem) &&
        Objects.equals(this.questionStemText, questionLibraryResource.questionStemText) &&
        Objects.equals(this.questionTypeName, questionLibraryResource.questionTypeName) &&
        Objects.equals(this.questionTypeIndex, questionLibraryResource.questionTypeIndex) &&
        Objects.equals(this.questionComplexityName, questionLibraryResource.questionComplexityName) &&
        Objects.equals(this.questionComplexityIndex, questionLibraryResource.questionComplexityIndex) &&
        Objects.equals(this.questionAnalysis, questionLibraryResource.questionAnalysis) &&
        Objects.equals(this.creatorId, questionLibraryResource.creatorId) &&
        Objects.equals(this.creatorName, questionLibraryResource.creatorName) &&
        Objects.equals(this.courseId, questionLibraryResource.courseId) &&
        Objects.equals(this.courseName, questionLibraryResource.courseName) &&
        Objects.equals(this.courseCode, questionLibraryResource.courseCode) &&
        Objects.equals(this.courseStructureId, questionLibraryResource.courseStructureId) &&
        Objects.equals(this.checkQuestionSort, questionLibraryResource.checkQuestionSort) &&
        Objects.equals(this.checkTextTransform, questionLibraryResource.checkTextTransform) &&
        Objects.equals(this.checkFuzzyMatching, questionLibraryResource.checkFuzzyMatching) &&
        Objects.equals(this.questionFileList, questionLibraryResource.questionFileList) &&
        Objects.equals(this.optionList, questionLibraryResource.optionList) &&
        Objects.equals(this.showOrder, questionLibraryResource.showOrder);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, questionStem, questionStemText, questionTypeName, questionTypeIndex,
            questionComplexityName, questionComplexityIndex, questionAnalysis, creatorId,
            creatorName, courseId, courseName, courseCode, courseStructureId, checkQuestionSort,
            checkTextTransform, checkFuzzyMatching, questionFileList, optionList, showOrder);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuestionLibraryResource {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    questionStem: ").append(toIndentedString(questionStem)).append("\n");
    sb.append("    questionStemText: ").append(toIndentedString(questionStemText)).append("\n");
    sb.append("    questionTypeName: ").append(toIndentedString(questionTypeName)).append("\n");
    sb.append("    questionTypeIndex: ").append(toIndentedString(questionTypeIndex)).append("\n");
    sb.append("    questionComplexityName: ").append(toIndentedString(questionComplexityName)).append("\n");
    sb.append("    questionComplexityIndex: ").append(toIndentedString(questionComplexityIndex)).append("\n");
    sb.append("    questionAnalysis: ").append(toIndentedString(questionAnalysis)).append("\n");
    sb.append("    creatorId: ").append(toIndentedString(creatorId)).append("\n");
    sb.append("    creatorName: ").append(toIndentedString(creatorName)).append("\n");
    sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
    sb.append("    courseName: ").append(toIndentedString(courseName)).append("\n");
    sb.append("    courseCode: ").append(toIndentedString(courseCode)).append("\n");
    sb.append("    courseStructureId: ").append(toIndentedString(courseStructureId)).append("\n");
    sb.append("    checkQuestionSort: ").append(toIndentedString(checkQuestionSort)).append("\n");
    sb.append("    checkTextTransform: ").append(toIndentedString(checkTextTransform)).append("\n");
    sb.append("    checkFuzzyMatching: ").append(toIndentedString(checkFuzzyMatching)).append("\n");
    sb.append("    questionFileList: ").append(toIndentedString(questionFileList)).append("\n");
    sb.append("    optionList: ").append(toIndentedString(optionList)).append("\n");
    sb.append("    showOrder: ").append(toIndentedString(showOrder)).append("\n");
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


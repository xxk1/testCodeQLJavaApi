package com.lztech.site.viewmodel.wisdomcourse;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * WisdomCourseParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-08-13T15:53:10.736+08:00")

public class WisdomCourseParam {
    @JsonProperty("courseId")
    private String courseId = null;

    @JsonProperty("operatorId")
    private String operatorId = null;

    @JsonProperty("operatorName")
    private String operatorName = null;

    @JsonProperty("operatorNo")
    private String operatorNo = null;

    @JsonProperty("historyTermList")
    @Valid
    private List<WisdomCourseHistoryTermParam> historyTermList = null;

    public WisdomCourseParam courseId(String courseId) {
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

    public WisdomCourseParam operatorId(String operatorId) {
        this.operatorId = operatorId;
        return this;
    }

    /**
     * 操作人id
     *
     * @return operatorId
     **/
    @ApiModelProperty(value = "操作人id")


    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public WisdomCourseParam operatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }

    /**
     * 操作人名称
     *
     * @return operatorName
     **/
    @ApiModelProperty(value = "操作人名称")


    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public WisdomCourseParam operatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
        return this;
    }

    /**
     * 操作人编号
     *
     * @return operatorNo
     **/
    @ApiModelProperty(value = "操作人编号")


    public String getOperatorNo() {
        return operatorNo;
    }

    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
    }

    public WisdomCourseParam historyTermList(List<WisdomCourseHistoryTermParam> historyTermList) {
        this.historyTermList = historyTermList;
        return this;
    }

    public WisdomCourseParam addHistoryTermListItem(WisdomCourseHistoryTermParam historyTermListItem) {
        if (this.historyTermList == null) {
            this.historyTermList = new ArrayList<WisdomCourseHistoryTermParam>();
        }
        this.historyTermList.add(historyTermListItem);
        return this;
    }

    /**
     * Get historyTermList
     *
     * @return historyTermList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<WisdomCourseHistoryTermParam> getHistoryTermList() {
        return historyTermList;
    }

    public void setHistoryTermList(List<WisdomCourseHistoryTermParam> historyTermList) {
        this.historyTermList = historyTermList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WisdomCourseParam wisdomCourseParam = (WisdomCourseParam) o;
        return Objects.equals(this.courseId, wisdomCourseParam.courseId) &&
                Objects.equals(this.operatorId, wisdomCourseParam.operatorId) &&
                Objects.equals(this.operatorName, wisdomCourseParam.operatorName) &&
                Objects.equals(this.operatorNo, wisdomCourseParam.operatorNo) &&
                Objects.equals(this.historyTermList, wisdomCourseParam.historyTermList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, operatorId, operatorName, operatorNo, historyTermList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WisdomCourseParam {\n");

        sb.append("    courseId: ").append(toIndentedString(courseId)).append("\n");
        sb.append("    operatorId: ").append(toIndentedString(operatorId)).append("\n");
        sb.append("    operatorName: ").append(toIndentedString(operatorName)).append("\n");
        sb.append("    operatorNo: ").append(toIndentedString(operatorNo)).append("\n");
        sb.append("    historyTermList: ").append(toIndentedString(historyTermList)).append("\n");
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


package com.lztech.site.viewmodel.courseknowledgegraph;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * KnowledgePointCourseResourceVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-06-18T14:06:31.240+08:00")

public class KnowledgePointCourseResourceVo {
    @JsonProperty("userId")
    private String userId = null;

    @JsonProperty("userName")
    private String userName = null;

    @JsonProperty("knowledgePointId")
    private String knowledgePointId = null;

    @JsonProperty("courseKnowledgeGraphId")
    private String courseKnowledgeGraphId = null;

    @JsonProperty("courseResourceIdList")
    @Valid
    private List<String> courseResourceIdList = null;

    public String getCourseKnowledgeGraphId() {
        return courseKnowledgeGraphId;
    }

    public void setCourseKnowledgeGraphId(String courseKnowledgeGraphId) {
        this.courseKnowledgeGraphId = courseKnowledgeGraphId;
    }

    public KnowledgePointCourseResourceVo userId(String userId) {
        this.userId = userId;
        return this;
    }

    /**
     * 操作人id
     *
     * @return userId
     **/
    @ApiModelProperty(value = "操作人id")


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public KnowledgePointCourseResourceVo userName(String userName) {
        this.userName = userName;
        return this;
    }

    /**
     * 操作人姓名
     *
     * @return userName
     **/
    @ApiModelProperty(value = "操作人姓名")


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public KnowledgePointCourseResourceVo knowledgePointId(String knowledgePointId) {
        this.knowledgePointId = knowledgePointId;
        return this;
    }

    /**
     * 资源id
     *
     * @return knowledgePointId
     **/
    @ApiModelProperty(value = "资源id")


    public String getKnowledgePointId() {
        return knowledgePointId;
    }

    public void setKnowledgePointId(String knowledgePointId) {
        this.knowledgePointId = knowledgePointId;
    }

    public KnowledgePointCourseResourceVo courseResourceIdList(List<String> courseResourceIdList) {
        this.courseResourceIdList = courseResourceIdList;
        return this;
    }

    public KnowledgePointCourseResourceVo addCourseResourceIdListItem(String courseResourceIdListItem) {
        if (this.courseResourceIdList == null) {
            this.courseResourceIdList = new ArrayList<String>();
        }
        this.courseResourceIdList.add(courseResourceIdListItem);
        return this;
    }

    /**
     * Get courseResourceIdList
     *
     * @return courseResourceIdList
     **/
    @ApiModelProperty(value = "")


    public List<String> getCourseResourceIdList() {
        return courseResourceIdList;
    }

    public void setCourseResourceIdList(List<String> courseResourceIdList) {
        this.courseResourceIdList = courseResourceIdList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KnowledgePointCourseResourceVo knowledgePointCourseResourceVo = (KnowledgePointCourseResourceVo) o;
        return Objects.equals(this.userId, knowledgePointCourseResourceVo.userId) &&
                Objects.equals(this.userName, knowledgePointCourseResourceVo.userName) &&
                Objects.equals(this.knowledgePointId, knowledgePointCourseResourceVo.knowledgePointId) &&
                Objects.equals(this.courseResourceIdList, knowledgePointCourseResourceVo.courseResourceIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, knowledgePointId, courseResourceIdList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class KnowledgePointCourseResourceVo {\n");

        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
        sb.append("    knowledgePointId: ").append(toIndentedString(knowledgePointId)).append("\n");
        sb.append("    courseResourceIdList: ").append(toIndentedString(courseResourceIdList)).append("\n");
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


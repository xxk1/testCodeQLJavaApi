package com.lztech.site.viewmodel.courseconstruction;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseResourceKnowledgePointVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-26T06:22:31.237Z")


public class CourseResourceKnowledgePointVo {
    @JsonProperty("userId")
    private String userId = null;

    @JsonProperty("userName")
    private String userName = null;

    @JsonProperty("resourceId")
    private String resourceId = null;

    @JsonProperty("courseKnowledgeGraphId")
    private String courseKnowledgeGraphId = null;

    @JsonProperty("pointIdList")
    @Valid
    private List<String> pointIdList = null;

    public String getCourseKnowledgeGraphId() {
        return courseKnowledgeGraphId;
    }

    public void setCourseKnowledgeGraphId(String courseKnowledgeGraphId) {
        this.courseKnowledgeGraphId = courseKnowledgeGraphId;
    }

    public CourseResourceKnowledgePointVo userId(String userId) {
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

    public CourseResourceKnowledgePointVo userName(String userName) {
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

    public CourseResourceKnowledgePointVo resourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    /**
     * 资源id
     *
     * @return resourceId
     **/
    @ApiModelProperty(value = "资源id")


    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public CourseResourceKnowledgePointVo pointIdList(List<String> pointIdList) {
        this.pointIdList = pointIdList;
        return this;
    }

    public CourseResourceKnowledgePointVo addPointIdListItem(String pointIdListItem) {
        if (this.pointIdList == null) {
            this.pointIdList = new ArrayList<String>();
        }
        this.pointIdList.add(pointIdListItem);
        return this;
    }

    /**
     * Get pointIdList
     *
     * @return pointIdList
     **/
    @ApiModelProperty(value = "")


    public List<String> getPointIdList() {
        return pointIdList;
    }

    public void setPointIdList(List<String> pointIdList) {
        this.pointIdList = pointIdList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseResourceKnowledgePointVo courseResourceKnowledgePointVo = (CourseResourceKnowledgePointVo) o;
        return Objects.equals(this.userId, courseResourceKnowledgePointVo.userId) &&
                Objects.equals(this.userName, courseResourceKnowledgePointVo.userName) &&
                Objects.equals(this.resourceId, courseResourceKnowledgePointVo.resourceId) &&
                Objects.equals(this.pointIdList, courseResourceKnowledgePointVo.pointIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, resourceId, pointIdList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseResourceKnowledgePointVo {\n");

        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
        sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
        sb.append("    pointIdList: ").append(toIndentedString(pointIdList)).append("\n");
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


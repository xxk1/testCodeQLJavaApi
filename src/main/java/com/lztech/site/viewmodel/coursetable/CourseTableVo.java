package com.lztech.site.viewmodel.coursetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseTableVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-08-28T08:44:58.096Z")


public class CourseTableVo {
    @JsonProperty("groupId")
    private String groupId = null;

    @JsonProperty("groupName")
    private String groupName = null;

    @JsonProperty("groupMember")
    private Integer groupMember = null;

    @JsonProperty("collegeId")
    private String collegeId = null;

    @JsonProperty("collegeName")
    private String collegeName = null;

    @JsonProperty("courseCategory")
    private Integer courseCategory = null;

    @JsonProperty("courseCategoryName")
    private String courseCategoryName = null;

    public CourseTableVo groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     * 组Id
     *
     * @return groupId
     **/
    @ApiModelProperty(value = "组Id")


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public CourseTableVo groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    /**
     * 组名称
     *
     * @return groupName
     **/
    @ApiModelProperty(value = "组名称")


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public CourseTableVo groupMember(Integer groupMember) {
        this.groupMember = groupMember;
        return this;
    }

    /**
     * 学生人数
     *
     * @return groupMember
     **/
    @ApiModelProperty(value = "学生人数")


    public Integer getGroupMember() {
        return groupMember;
    }

    public void setGroupMember(Integer groupMember) {
        this.groupMember = groupMember;
    }

    public CourseTableVo collegeId(String collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    /**
     * 学院Id
     *
     * @return collegeId
     **/
    @ApiModelProperty(value = "学院Id")


    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public CourseTableVo collegeName(String collegeName) {
        this.collegeName = collegeName;
        return this;
    }

    /**
     * 学院名称
     *
     * @return collegeName
     **/
    @ApiModelProperty(value = "学院名称")


    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public CourseTableVo courseCategory(Integer courseCategory) {
        this.courseCategory = courseCategory;
        return this;
    }

    /**
     * 课程类别 0:必修 1:选修 2: 公选 3:指选 4:国际课 5:双学位 6:其他
     *
     * @return courseCategory
     **/
    @ApiModelProperty(value = "课程类别 0:必修 1:选修 2: 公选 3:指选 4:国际课 5:双学位 6:其他")


    public Integer getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(Integer courseCategory) {
        this.courseCategory = courseCategory;
    }

    public CourseTableVo courseCategoryName(String courseCategoryName) {
        this.courseCategoryName = courseCategoryName;
        return this;
    }

    /**
     * 课程类别 0:必修 1:选修 2: 公选 3:指选 4:国际课 5:双学位 6:其他
     *
     * @return courseCategoryName
     **/
    @ApiModelProperty(value = "课程类别 0:必修 1:选修 2: 公选 3:指选 4:国际课 5:双学位 6:其他")


    public String getCourseCategoryName() {
        return courseCategoryName;
    }

    public void setCourseCategoryName(String courseCategoryName) {
        this.courseCategoryName = courseCategoryName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableVo courseTableVo = (CourseTableVo) o;
        return Objects.equals(this.groupId, courseTableVo.groupId) &&
                Objects.equals(this.groupName, courseTableVo.groupName) &&
                Objects.equals(this.groupMember, courseTableVo.groupMember) &&
                Objects.equals(this.collegeId, courseTableVo.collegeId) &&
                Objects.equals(this.collegeName, courseTableVo.collegeName) &&
                Objects.equals(this.courseCategory, courseTableVo.courseCategory) &&
                Objects.equals(this.courseCategoryName, courseTableVo.courseCategoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, groupName, groupMember, collegeId, collegeName, courseCategory, courseCategoryName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseTableVo {\n");

        sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
        sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
        sb.append("    groupMember: ").append(toIndentedString(groupMember)).append("\n");
        sb.append("    collegeId: ").append(toIndentedString(collegeId)).append("\n");
        sb.append("    collegeName: ").append(toIndentedString(collegeName)).append("\n");
        sb.append("    courseCategory: ").append(toIndentedString(courseCategory)).append("\n");
        sb.append("    courseCategoryName: ").append(toIndentedString(courseCategoryName)).append("\n");
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


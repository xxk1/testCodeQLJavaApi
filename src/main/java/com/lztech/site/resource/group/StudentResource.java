package com.lztech.site.resource.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * StudentResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2026-01-19T09:36:51.039+08:00")

public class StudentResource {
    @JsonProperty("userId")
    private String userId = null;

    @JsonProperty("openId")
    private String openId = null;

    @JsonProperty("userName")
    private String userName = null;

    @JsonProperty("userNo")
    private String userNo = null;

    @JsonProperty("classId")
    private String classId = null;

    @JsonProperty("className")
    private String className = null;

    @JsonProperty("classNickName")
    private String classNickName = null;

    @JsonProperty("source")
    private Integer source = null;

    @JsonProperty("createTime")
    private String createTime = null;

    @JsonProperty("administrativeClassName")
    private String administrativeClassName = null;

    @JsonProperty("studentCollegeId")
    private String studentCollegeId = null;

    @JsonProperty("studentCollegeName")
    private String studentCollegeName = null;

    public StudentResource userId(String userId) {
        this.userId = userId;
        return this;
    }

    /**
     * 学生id
     *
     * @return userId
     **/
    @ApiModelProperty(value = "学生id")


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public StudentResource openId(String openId) {
        this.openId = openId;
        return this;
    }

    /**
     * openId
     *
     * @return openId
     **/
    @ApiModelProperty(value = "openId")


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public StudentResource userName(String userName) {
        this.userName = userName;
        return this;
    }

    /**
     * 学生姓名
     *
     * @return userName
     **/
    @ApiModelProperty(value = "学生姓名")


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public StudentResource userNo(String userNo) {
        this.userNo = userNo;
        return this;
    }

    /**
     * 学生学号
     *
     * @return userNo
     **/
    @ApiModelProperty(value = "学生学号")


    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public StudentResource classId(String classId) {
        this.classId = classId;
        return this;
    }

    /**
     * 所属班级Id
     *
     * @return classId
     **/
    @ApiModelProperty(value = "所属班级Id")


    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public StudentResource className(String className) {
        this.className = className;
        return this;
    }

    /**
     * 所属班级名称
     *
     * @return className
     **/
    @ApiModelProperty(value = "所属班级名称")


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public StudentResource classNickName(String classNickName) {
        this.classNickName = classNickName;
        return this;
    }

    /**
     * 班级昵称
     *
     * @return classNickName
     **/
    @ApiModelProperty(value = "班级昵称")


    public String getClassNickName() {
        return classNickName;
    }

    public void setClassNickName(String classNickName) {
        this.classNickName = classNickName;
    }

    public StudentResource source(Integer source) {
        this.source = source;
        return this;
    }

    /**
     * 班级来源（数据对接,0；系统录入,1；外部导入,2；自主开班,3；）
     *
     * @return source
     **/
    @ApiModelProperty(value = "班级来源（数据对接,0；系统录入,1；外部导入,2；自主开班,3；）")


    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public StudentResource createTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    /**
     * 创建时间；加入班级时间
     *
     * @return createTime
     **/
    @ApiModelProperty(value = "创建时间；加入班级时间")


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public StudentResource administrativeClassName(String administrativeClassName) {
        this.administrativeClassName = administrativeClassName;
        return this;
    }

    /**
     * 行政班名称
     *
     * @return administrativeClassName
     **/
    @ApiModelProperty(value = "行政班名称")


    public String getAdministrativeClassName() {
        return administrativeClassName;
    }

    public void setAdministrativeClassName(String administrativeClassName) {
        this.administrativeClassName = administrativeClassName;
    }

    public StudentResource studentCollegeId(String studentCollegeId) {
        this.studentCollegeId = studentCollegeId;
        return this;
    }

    /**
     * 学生学院id
     *
     * @return studentCollegeId
     **/
    @ApiModelProperty(value = "学生学院id")


    public String getStudentCollegeId() {
        return studentCollegeId;
    }

    public void setStudentCollegeId(String studentCollegeId) {
        this.studentCollegeId = studentCollegeId;
    }

    public StudentResource studentCollegeName(String studentCollegeName) {
        this.studentCollegeName = studentCollegeName;
        return this;
    }

    /**
     * 学生学院名称
     *
     * @return studentCollegeName
     **/
    @ApiModelProperty(value = "学生学院名称")


    public String getStudentCollegeName() {
        return studentCollegeName;
    }

    public void setStudentCollegeName(String studentCollegeName) {
        this.studentCollegeName = studentCollegeName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StudentResource studentResource = (StudentResource) o;
        return Objects.equals(this.userId, studentResource.userId) &&
                Objects.equals(this.openId, studentResource.openId) &&
                Objects.equals(this.userName, studentResource.userName) &&
                Objects.equals(this.userNo, studentResource.userNo) &&
                Objects.equals(this.classId, studentResource.classId) &&
                Objects.equals(this.className, studentResource.className) &&
                Objects.equals(this.classNickName, studentResource.classNickName) &&
                Objects.equals(this.source, studentResource.source) &&
                Objects.equals(this.createTime, studentResource.createTime) &&
                Objects.equals(this.administrativeClassName, studentResource.administrativeClassName) &&
                Objects.equals(this.studentCollegeId, studentResource.studentCollegeId) &&
                Objects.equals(this.studentCollegeName, studentResource.studentCollegeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, openId, userName, userNo, classId, className, classNickName, source, createTime, administrativeClassName, studentCollegeId, studentCollegeName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StudentResource {\n");

        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    openId: ").append(toIndentedString(openId)).append("\n");
        sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
        sb.append("    userNo: ").append(toIndentedString(userNo)).append("\n");
        sb.append("    classId: ").append(toIndentedString(classId)).append("\n");
        sb.append("    className: ").append(toIndentedString(className)).append("\n");
        sb.append("    classNickName: ").append(toIndentedString(classNickName)).append("\n");
        sb.append("    source: ").append(toIndentedString(source)).append("\n");
        sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
        sb.append("    administrativeClassName: ").append(toIndentedString(administrativeClassName)).append("\n");
        sb.append("    studentCollegeId: ").append(toIndentedString(studentCollegeId)).append("\n");
        sb.append("    studentCollegeName: ").append(toIndentedString(studentCollegeName)).append("\n");
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


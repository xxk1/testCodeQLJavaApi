package com.lztech.site.viewmodel.coursemanagement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * OrgStatisticsResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-02-21T07:43:39.378Z")


public class OrgStatisticsResource {
    @JsonProperty("orgName")
    private String orgName = null;

    @JsonProperty("teacherNum")
    private Integer teacherNum = null;

    public OrgStatisticsResource orgName(String orgName) {
        this.orgName = orgName;
        return this;
    }

    /**
     * 组织单位名称
     *
     * @return orgName
     **/
    @ApiModelProperty(value = "组织单位名称")


    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public OrgStatisticsResource teacherNum(Integer teacherNum) {
        this.teacherNum = teacherNum;
        return this;
    }

    /**
     * 教师数量
     *
     * @return teacherNum
     **/
    @ApiModelProperty(value = "教师数量")


    public Integer getTeacherNum() {
        return teacherNum;
    }

    public void setTeacherNum(Integer teacherNum) {
        this.teacherNum = teacherNum;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrgStatisticsResource orgStatisticsResource = (OrgStatisticsResource) o;
        return Objects.equals(this.orgName, orgStatisticsResource.orgName) &&
                Objects.equals(this.teacherNum, orgStatisticsResource.teacherNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orgName, teacherNum);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class OrgStatisticsResource {\n");

        sb.append("    orgName: ").append(toIndentedString(orgName)).append("\n");
        sb.append("    teacherNum: ").append(toIndentedString(teacherNum)).append("\n");
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


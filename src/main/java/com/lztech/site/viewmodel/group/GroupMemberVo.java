package com.lztech.site.viewmodel.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * GroupMemberVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-10-19T11:14:39.905Z")


public class GroupMemberVo {
    @JsonProperty("studentCount")
    private Integer studentCount = null;

    public GroupMemberVo studentCount(Integer studentCount) {
        this.studentCount = studentCount;
        return this;
    }

    /**
     * 学生人数
     *
     * @return studentCount
     **/
    @ApiModelProperty(value = "学生人数")


    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GroupMemberVo groupMemberVo = (GroupMemberVo) o;
        return Objects.equals(this.studentCount, groupMemberVo.studentCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentCount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GroupMemberVo {\n");

        sb.append("    studentCount: ").append(toIndentedString(studentCount)).append("\n");
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


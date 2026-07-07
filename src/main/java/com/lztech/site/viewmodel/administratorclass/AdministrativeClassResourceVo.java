package com.lztech.site.viewmodel.administratorclass;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * AdministrativeClassResourceVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-04-28T03:29:48.199Z")


public class AdministrativeClassResourceVo {
    @JsonProperty("className")
    private String className = null;

    @JsonProperty("classId")
    private String classId = null;

    public AdministrativeClassResourceVo className(String className) {
        this.className = className;
        return this;
    }

    /**
     * 行政班名称
     *
     * @return className
     **/
    @ApiModelProperty(value = "行政班名称")


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public AdministrativeClassResourceVo classId(String classId) {
        this.classId = classId;
        return this;
    }

    /**
     * 行政班Id
     *
     * @return classId
     **/
    @ApiModelProperty(value = "行政班Id")


    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AdministrativeClassResourceVo administrativeClassResourceVo = (AdministrativeClassResourceVo) o;
        return Objects.equals(this.className, administrativeClassResourceVo.className) &&
                Objects.equals(this.classId, administrativeClassResourceVo.classId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(className, classId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AdministrativeClassResourceVo {\n");

        sb.append("    className: ").append(toIndentedString(className)).append("\n");
        sb.append("    classId: ").append(toIndentedString(classId)).append("\n");
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


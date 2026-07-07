package com.lztech.site.resource.college;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CollegeVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-16T02:32:51.667Z")

public class CollegeVo {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("checked")
    private Boolean checked = null;

    public CollegeVo id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 开课学院ID
     *
     * @return id
     **/
    @ApiModelProperty(value = "开课学院ID")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CollegeVo name(String name) {
        this.name = name;
        return this;
    }

    /**
     * 开课学院名称
     *
     * @return name
     **/
    @ApiModelProperty(value = "开课学院名称")


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CollegeVo checked(Boolean checked) {
        this.checked = checked;
        return this;
    }

    /**
     * 是否选择
     *
     * @return checked
     **/
    @ApiModelProperty(value = "是否选择")


    public Boolean isChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CollegeVo collegeVo = (CollegeVo) o;
        return Objects.equals(this.id, collegeVo.id) &&
                Objects.equals(this.name, collegeVo.name) &&
                Objects.equals(this.checked, collegeVo.checked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, checked);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CollegeVo {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    checked: ").append(toIndentedString(checked)).append("\n");
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


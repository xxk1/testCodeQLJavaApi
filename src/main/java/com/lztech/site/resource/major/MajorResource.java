package com.lztech.site.resource.major;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * MajorResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-07-16T07:32:49.445Z")

public class MajorResource {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("majorName")
    private String majorName = null;

    @JsonProperty("checked")
    private Boolean checked = null;

    public MajorResource id(String id) {
        this.id = id;
        return this;
    }

    /**
     * 专业id
     *
     * @return id
     **/
    @ApiModelProperty(value = "专业id")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MajorResource majorName(String majorName) {
        this.majorName = majorName;
        return this;
    }

    /**
     * 专业名称
     *
     * @return majorName
     **/
    @ApiModelProperty(value = "专业名称")


    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public MajorResource checked(Boolean checked) {
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
        MajorResource majorResource = (MajorResource) o;
        return Objects.equals(this.id, majorResource.id) &&
                Objects.equals(this.majorName, majorResource.majorName) &&
                Objects.equals(this.checked, majorResource.checked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, majorName, checked);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class MajorResource {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    majorName: ").append(toIndentedString(majorName)).append("\n");
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


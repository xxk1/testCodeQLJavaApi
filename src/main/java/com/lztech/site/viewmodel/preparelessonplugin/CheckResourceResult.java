package com.lztech.site.viewmodel.preparelessonplugin;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * CheckResourceResult
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-04-14T11:10:50.724Z")

public class CheckResourceResult   {
    @JsonProperty("hasSameNameFile")
    private Integer hasSameNameFile = null;

    public CheckResourceResult hasSameNameFile(Integer hasSameNameFile) {
        this.hasSameNameFile = hasSameNameFile;
        return this;
    }

    /**
     * 0:有重名文件，1：没有重名文件
     * @return hasSameNameFile
     **/
    @ApiModelProperty(value = "0:有重名文件，1：没有重名文件")


    public Integer getHasSameNameFile() {
        return hasSameNameFile;
    }

    public void setHasSameNameFile(Integer hasSameNameFile) {
        this.hasSameNameFile = hasSameNameFile;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CheckResourceResult checkResourceResult = (CheckResourceResult) o;
        return Objects.equals(this.hasSameNameFile, checkResourceResult.hasSameNameFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hasSameNameFile);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CheckResourceResult {\n");

        sb.append("    hasSameNameFile: ").append(toIndentedString(hasSameNameFile)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
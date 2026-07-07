package com.lztech.site.viewmodel.coursestatistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CourseResourceDetailStatisticsVo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-09-28T06:46:23.329Z")


public class CourseResourceDetailStatisticsVo {
    @JsonProperty("totalNum")
    private Integer totalNum = null;

    @JsonProperty("typeList")
    @Valid
    private List<ResourceTypeDetailStatisticsVo> typeList = null;

    public CourseResourceDetailStatisticsVo totalNum(Integer totalNum) {
        this.totalNum = totalNum;
        return this;
    }

    /**
     * 资源累计总数
     *
     * @return totalNum
     **/
    @ApiModelProperty(value = "资源累计总数")


    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public CourseResourceDetailStatisticsVo typeList(List<ResourceTypeDetailStatisticsVo> typeList) {
        this.typeList = typeList;
        return this;
    }

    public CourseResourceDetailStatisticsVo addTypeListItem(ResourceTypeDetailStatisticsVo typeListItem) {
        if (this.typeList == null) {
            this.typeList = new ArrayList<ResourceTypeDetailStatisticsVo>();
        }
        this.typeList.add(typeListItem);
        return this;
    }

    /**
     * Get typeList
     *
     * @return typeList
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<ResourceTypeDetailStatisticsVo> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<ResourceTypeDetailStatisticsVo> typeList) {
        this.typeList = typeList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseResourceDetailStatisticsVo courseResourceDetailStatisticsVo = (CourseResourceDetailStatisticsVo) o;
        return Objects.equals(this.totalNum, courseResourceDetailStatisticsVo.totalNum) &&
                Objects.equals(this.typeList, courseResourceDetailStatisticsVo.typeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalNum, typeList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseResourceDetailStatisticsVo {\n");

        sb.append("    totalNum: ").append(toIndentedString(totalNum)).append("\n");
        sb.append("    typeList: ").append(toIndentedString(typeList)).append("\n");
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


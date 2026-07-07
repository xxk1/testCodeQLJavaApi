package com.lztech.site.viewmodel.coursestatistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CourseResourceUseStatisticsResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-09-29T11:24:29.252Z")


public class CourseResourceUseStatisticsResource {
    @JsonProperty("resourceBrowsingNum")
    private Integer resourceBrowsingNum = null;

    @JsonProperty("resourceDownloadNum")
    private Integer resourceDownloadNum = null;

    @JsonProperty("resourceQuoteNum")
    private Integer resourceQuoteNum = null;

    public CourseResourceUseStatisticsResource resourceBrowsingNum(Integer resourceBrowsingNum) {
        this.resourceBrowsingNum = resourceBrowsingNum;
        return this;
    }

    /**
     * 资源浏览量
     *
     * @return resourceBrowsingNum
     **/
    @ApiModelProperty(value = "资源浏览量")


    public Integer getResourceBrowsingNum() {
        return resourceBrowsingNum;
    }

    public void setResourceBrowsingNum(Integer resourceBrowsingNum) {
        this.resourceBrowsingNum = resourceBrowsingNum;
    }

    public CourseResourceUseStatisticsResource resourceDownloadNum(Integer resourceDownloadNum) {
        this.resourceDownloadNum = resourceDownloadNum;
        return this;
    }

    /**
     * resourceDownloadNum
     *
     * @return resourceDownloadNum
     **/
    @ApiModelProperty(value = "resourceDownloadNum")


    public Integer getResourceDownloadNum() {
        return resourceDownloadNum;
    }

    public void setResourceDownloadNum(Integer resourceDownloadNum) {
        this.resourceDownloadNum = resourceDownloadNum;
    }

    public CourseResourceUseStatisticsResource resourceQuoteNum(Integer resourceQuoteNum) {
        this.resourceQuoteNum = resourceQuoteNum;
        return this;
    }

    /**
     * 资源引用数量
     *
     * @return resourceQuoteNum
     **/
    @ApiModelProperty(value = "资源引用数量")


    public Integer getResourceQuoteNum() {
        return resourceQuoteNum;
    }

    public void setResourceQuoteNum(Integer resourceQuoteNum) {
        this.resourceQuoteNum = resourceQuoteNum;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseResourceUseStatisticsResource courseResourceUseStatisticsResource = (CourseResourceUseStatisticsResource) o;
        return Objects.equals(this.resourceBrowsingNum, courseResourceUseStatisticsResource.resourceBrowsingNum) &&
                Objects.equals(this.resourceDownloadNum, courseResourceUseStatisticsResource.resourceDownloadNum) &&
                Objects.equals(this.resourceQuoteNum, courseResourceUseStatisticsResource.resourceQuoteNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceBrowsingNum, resourceDownloadNum, resourceQuoteNum);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CourseResourceUseStatisticsResource {\n");

        sb.append("    resourceBrowsingNum: ").append(toIndentedString(resourceBrowsingNum)).append("\n");
        sb.append("    resourceDownloadNum: ").append(toIndentedString(resourceDownloadNum)).append("\n");
        sb.append("    resourceQuoteNum: ").append(toIndentedString(resourceQuoteNum)).append("\n");
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


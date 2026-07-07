package com.lztech.site.resource.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * GroupStudentPageResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-18T08:52:58.896Z")

public class GroupStudentPageResource {
    @JsonProperty("total")
    private Integer total = null;

    @JsonProperty("page")
    private Integer page = null;

    @JsonProperty("pageSize")
    private Integer pageSize = null;

    @JsonProperty("students")
    @Valid
    private List<StudentResource> students = null;

    public GroupStudentPageResource total(Integer total) {
        this.total = total;
        return this;
    }

    /**
     * 总页数
     *
     * @return total
     **/
    @ApiModelProperty(example = "10", value = "总页数")


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public GroupStudentPageResource page(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * 当前页
     *
     * @return page
     **/
    @ApiModelProperty(example = "10", value = "当前页")


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public GroupStudentPageResource pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 每页个数
     *
     * @return pageSize
     **/
    @ApiModelProperty(example = "10", value = "每页个数")


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public GroupStudentPageResource students(List<StudentResource> students) {
        this.students = students;
        return this;
    }

    public GroupStudentPageResource addStudentsItem(StudentResource studentsItem) {
        if (this.students == null) {
            this.students = new ArrayList<StudentResource>();
        }
        this.students.add(studentsItem);
        return this;
    }

    /**
     * Get students
     *
     * @return students
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<StudentResource> getStudents() {
        return students;
    }

    public void setStudents(List<StudentResource> students) {
        this.students = students;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GroupStudentPageResource groupStudentPageResource = (GroupStudentPageResource) o;
        return Objects.equals(this.total, groupStudentPageResource.total) &&
                Objects.equals(this.page, groupStudentPageResource.page) &&
                Objects.equals(this.pageSize, groupStudentPageResource.pageSize) &&
                Objects.equals(this.students, groupStudentPageResource.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, page, pageSize, students);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GroupStudentPageResource {\n");

        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    students: ").append(toIndentedString(students)).append("\n");
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


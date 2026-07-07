package com.lztech.site.viewmodel.coursetabledetail;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class CourseTableSuperviseModel {
    private String courseId;
    private String courseCode;
    private String courseName;
    private String groupId;
    private String groupNo;
    private String groupName;
    private String courseTableId;
    private String teacherCollegeIds;
    private String teacherCollegeNames;

    private Integer studentType;

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseTableSuperviseModel that = (CourseTableSuperviseModel) o;
        return Objects.equals(courseId, that.courseId)
                && Objects.equals(courseCode, that.courseCode)
                && Objects.equals(courseName, that.courseName)
                && Objects.equals(groupId, that.groupId)
                && Objects.equals(groupNo, that.groupNo)
                && Objects.equals(groupName, that.groupName)
                && Objects.equals(courseTableId, that.courseTableId)
                && Objects.equals(teacherCollegeIds, that.teacherCollegeIds)
                && Objects.equals(teacherCollegeNames, that.teacherCollegeNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseCode, courseName, groupId, groupNo,
                groupName, courseTableId, teacherCollegeIds, teacherCollegeNames);
    }

    @Override
    public String toString() {
        return "CourseTableSuperviseModel{" +
                "courseId='" + courseId + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", groupId='" + groupId + '\'' +
                ", groupNo='" + groupNo + '\'' +
                ", groupName='" + groupName + '\'' +
                ", courseTableId='" + courseTableId + '\'' +
                ", teacherCollegeIds='" + teacherCollegeIds + '\'' +
                ", teacherCollegeNames='" + teacherCollegeNames + '\'' +
                '}';
    }


}

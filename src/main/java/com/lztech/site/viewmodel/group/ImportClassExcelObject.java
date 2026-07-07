package com.lztech.site.viewmodel.group;


import com.lztech.site.constants.Constant;
import com.lztech.site.service.group.annotation.ExcelCheckCollegeCodeIsExist;
import com.lztech.site.service.group.annotation.ExcelCheckCourseCodeIsExist;
import com.lztech.site.service.group.annotation.ExcelCheckIsNull;
import com.lztech.site.service.group.annotation.ExcelCheckIsUniqueClassCode;
import com.lztech.site.service.group.annotation.ExcelCheckTeacherIsExist;
import com.lztech.site.service.group.annotation.ExcelCheckTermOfSemesterIsExist;
import com.lztech.site.service.group.annotation.ExcelMaxLength;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode
public class ImportClassExcelObject {

    /**
     * 所属学期
     */
    @ExcelCheckIsNull(name = Constant.TERM_OF_SEMESTER)
    @ExcelCheckTermOfSemesterIsExist(message =Constant.TERM_OF_SEMESTER)
    private String termOfSemester;

    /**
     * 班级编号
     */
    @ExcelCheckIsNull(name = Constant.CLASS_NO)
    @ExcelCheckIsUniqueClassCode(message = Constant.CLASS_NO)
    @ExcelMaxLength(name = Constant.CLASS_NO,value = "30")
    private String classNo;

    /**
     * 班级名称
     */
    @ExcelCheckIsNull(name = Constant.CLASS_NAME)
    @ExcelMaxLength(name = Constant.CLASS_NAME,value = "20")
    private String className;

    /**
     * 班级属性
     */
    @ExcelCheckIsNull(name = Constant.CLASS_ATTRIBUTE)
    private String classAttribute;

    /**
     * 授课课程编号
     */
    @ExcelCheckIsNull(name = Constant.COURSE_CODE)
    @ExcelCheckCourseCodeIsExist(message = Constant.COURSE_CODE )
    private String courseCode;

    /**
     * 授课课程名称
     */
    @ExcelCheckIsNull(name = Constant.COURSE_NAME)
    @ExcelMaxLength(name = Constant.COURSE_NAME,value = "40")
    private String courseName;

    /**
     * 开课学院编号
     */
    @ExcelCheckIsNull(name = Constant.OPEN_COLLEGE_CODE)
    @ExcelCheckCollegeCodeIsExist(message = Constant.OPEN_COLLEGE)
    private String openCollegeCode;

    /**
     * 开课学院名称
     */
    @ExcelCheckIsNull(name = Constant.OPEN_COLLEGE_NAME)
    @ExcelMaxLength(name = Constant.OPEN_COLLEGE_NAME,value = "30")
    private String openCollegeName;

    /**
     * 课程类型
     */
    @ExcelCheckIsNull(name = Constant.COURSE_CATEGORY)
    private String  courseCategory;

    /**
     * 授课老师
     */
    @ExcelCheckIsNull(name = Constant.COURSE_TEACHER_CODES)
    @ExcelCheckTeacherIsExist(message = Constant.COURSE_TEACHER_CODES)
    private String courseTeacherCodes;




}

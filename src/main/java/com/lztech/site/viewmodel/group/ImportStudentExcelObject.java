package com.lztech.site.viewmodel.group;


import com.lztech.site.constants.Constant;
import com.lztech.site.service.group.annotation.ExcelCheckIsNull;
import com.lztech.site.service.group.annotation.ExcelMaxLength;
import com.lztech.site.service.groupmember.annotation.ExcelCheckClassCodeIsExist;
import com.lztech.site.service.groupmember.annotation.ExcelCheckStudentNoIsExist;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode
public class ImportStudentExcelObject {


    /**
     * 班级编号
     */
    @ExcelCheckIsNull(name = Constant.CLASS_NO)
    @ExcelCheckClassCodeIsExist(message = Constant.CLASS_NO)
    private String classNo;

    /**
     * 班级名称
     */
    @ExcelMaxLength(name = Constant.CLASS_NAME, value = "20")
    private String className;

    /**
     * 学号
     */
    @ExcelCheckIsNull(name = Constant.STUDENT_NO)
    @ExcelCheckStudentNoIsExist(message = Constant.STUDENT_NO)
    private String studentCode;

    /**
     * 姓名
     */
    @ExcelMaxLength(name = Constant.STUDENT_NAME, value = "30")
    private String studentName;


}

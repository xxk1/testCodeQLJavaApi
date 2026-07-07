package com.lztech.site.viewmodel.experimentschedule;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.*;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@ExcelIgnoreUnannotated //忽视无注解的属性
@ContentRowHeight(20) //文本高度
@HeadRowHeight(22) //标题高度
@Data
public class ExperimentScheduleCourseTableDetailsExcelVo {
    @ColumnWidth(20) //列宽
    @ContentFontStyle(fontHeightInPoints = 12)
    @ExcelProperty(value = "上课周次", index = 0)
    @ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER)
    @JsonProperty("weekNum")
    private String weekNum;

    @ColumnWidth(20) //列宽
    @ContentFontStyle(fontHeightInPoints = 12)
    @ExcelProperty(value = "开课学院", index = 1)
    @JsonProperty("collegeName")
    private String collegeName;

    @ColumnWidth(20) //列宽
    @ContentFontStyle(fontHeightInPoints = 12)
    @ExcelProperty(value = "上课班级", index = 2)
    @JsonProperty("className")
    private String className = null;

    @ColumnWidth(20) //列宽
    @ContentFontStyle(fontHeightInPoints = 12)
    @ExcelProperty(value = "课程名称", index = 3)
    @JsonProperty("courseName")
    private String courseName = null;

    @ColumnWidth(20) //列宽
    @ContentFontStyle(fontHeightInPoints = 12)
    @ExcelProperty(value = "实验项目", index = 4)
    @JsonProperty("projectName")
    private String projectName = null;

    @ColumnWidth(20) //列宽
    @ContentFontStyle(fontHeightInPoints = 12)
    @ExcelProperty(value = "授课教师", index = 5)
    @JsonProperty("teacherName")
    private String teacherName = null;

    @ColumnWidth(20) //列宽
    @ContentFontStyle(fontHeightInPoints = 12)
    @ExcelProperty(value = "上课日期", index = 6)
    @ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER)
    @JsonProperty("courseDate")
    private String courseDate = null;

    @ColumnWidth(20) //列宽
    @ContentFontStyle(fontHeightInPoints = 12)
    @ExcelProperty(value = "上课星期", index = 7)
    @ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER)
    @JsonProperty("week")
    private String week = null;

    @ColumnWidth(20) //列宽
    @ContentFontStyle(fontHeightInPoints = 12)
    @ExcelProperty(value = "上课节次", index = 8)
    @ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER)
    @JsonProperty("segment")
    private String segment = null;

    @ColumnWidth(20) //列宽
    @ContentFontStyle(fontHeightInPoints = 12)
    @ExcelProperty(value = "实验室", index = 9)
    @JsonProperty("roomName")
    private String roomName = null;
}

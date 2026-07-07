package com.lztech.site.service.group.annotation;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.lztech.site.constants.Constant;
import com.lztech.site.service.group.CacheUtil;
import com.lztech.site.service.groupmember.annotation.ExcelCheckClassCodeIsExist;
import com.lztech.site.service.groupmember.annotation.ExcelCheckStudentNoIsExist;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Slf4j
public class AnnotationValidator {


    public static ArrayList<String> valid(Object obj,Integer rowIndex) {
        ArrayList<String> messageList = new ArrayList<>();
        Field[] filds = obj.getClass().getDeclaredFields();
        for (Field field : filds) {
            field.setAccessible(true);
            Object fieldValue = null;
            try {
                fieldValue = field.get(obj);


                // 判断是否为空
                String excelCheckIsNullMessage = excelCheckIsNull(field, fieldValue,rowIndex);
                if (StrUtil.isNotBlank(excelCheckIsNullMessage)) {
                    messageList.add(excelCheckIsNullMessage);
                    continue;
                }

                // 判断输入的所属学期是否存在
                String excelCheckTermOfSemesterIsExistMessage = excelCheckTermOfSemesterIsExist(field, fieldValue,rowIndex);
                if (StrUtil.isNotBlank(excelCheckTermOfSemesterIsExistMessage)) {
                    messageList.add(excelCheckTermOfSemesterIsExistMessage);
                }

                // 判断班级编号是否重复
                String excelCheckIsUniqueClassCodeMessage = excelCheckIsUniqueClassCode(field, fieldValue, rowIndex);
                if (StrUtil.isNotBlank(excelCheckIsUniqueClassCodeMessage))
                {
                    messageList.add(excelCheckIsUniqueClassCodeMessage);
                }
                // 判断是否大于需求的字符长度
                String excelMaxLengthMessage = excelMaxLength(field, fieldValue, rowIndex);
                if (StrUtil.isNotBlank(excelMaxLengthMessage)) {
                    messageList.add(excelMaxLengthMessage);
                }

                // 判断输入的课程编号是否存在
                String excelCheckCourseCodeIsExist = excelCheckCourseCodeIsExist(field, fieldValue, rowIndex);
                if (StrUtil.isNotBlank(excelCheckCourseCodeIsExist)) {
                    messageList.add(excelCheckCourseCodeIsExist);
                }

                // 判断输入的开课学院是否存在
                String excelCheckCollegeCodeIsExist = excelCheckCollegeCodeIsExist(field, fieldValue, rowIndex);
                if (StrUtil.isNotBlank(excelCheckCollegeCodeIsExist)) {
                    messageList.add(excelCheckCollegeCodeIsExist);
                }

                // 判断老师是否存在
                String excelCheckTeacherIsExist = excelCheckTeacherIsExist(field, fieldValue, rowIndex);
                if (StrUtil.isNotBlank(excelCheckTeacherIsExist)) {
                    messageList.add(excelCheckTeacherIsExist);
                }

                // 学生学号
                String excelCheckStudentNoIsExist = excelCheckStudentNoIsExist(field, fieldValue, rowIndex);
                if (StrUtil.isNotBlank(excelCheckStudentNoIsExist)) {
                    messageList.add(excelCheckStudentNoIsExist);
                }

                // 班级编号是否存在
                String excelCheckClassCodeIsExist = excelCheckClassCodeIsExist(field, fieldValue, rowIndex);
                if (StrUtil.isNotBlank(excelCheckClassCodeIsExist)) {
                    messageList.add(excelCheckClassCodeIsExist);
                }


            } catch (Exception e) {
                log.info("Error->{}", JSON.toJSONString(e));
            }

        }

        return messageList;

    }

    private static String excelCheckClassCodeIsExist(Field field, Object fieldValue, Integer rowIndex) {
        boolean isExcelValid = field.isAnnotationPresent(ExcelCheckClassCodeIsExist.class);
        if (isExcelValid) {
            if (CacheUtil.isContainKey(Constant.GROUP_NO_LIST)) {

                if (((List<String>) CacheUtil.get(Constant.GROUP_NO_LIST)).stream().noneMatch(e -> e.equals(fieldValue))) {
                    String message = field.getAnnotation(ExcelCheckClassCodeIsExist.class).message();
                    return StrUtil.format("第{}行{}不存在!", rowIndex, message);
                }
                return null;

            }
        }

        return null;
    }

    private static String excelCheckStudentNoIsExist(Field field, Object fieldValue, Integer rowIndex) {
        boolean isExcelValid = field.isAnnotationPresent(ExcelCheckStudentNoIsExist.class);
        if (isExcelValid) {
            Object finalFieldValue = fieldValue;
            if (CacheUtil.isContainKey(Constant.ALL_STUDENT_NO)) {
                if (!((List<String>) CacheUtil.get(Constant.ALL_STUDENT_NO))
                        .stream().anyMatch(e -> e.equals(finalFieldValue))) {
                    String message = field.getAnnotation(ExcelCheckStudentNoIsExist.class).message();
                    return StrUtil.format("第{}行{}不存在!", rowIndex, message);
                } else {
                    return null;
                }
            } else {
                String message = field.getAnnotation(ExcelCheckStudentNoIsExist.class).message();
                return StrUtil.format("第{}行{}不存在!", rowIndex, message);
            }
        }

        return null;
    }

    private static String excelCheckCollegeCodeIsExist(Field field, Object fieldValue, Integer rowIndex) {
        boolean isExcelValid = field.isAnnotationPresent(ExcelCheckCollegeCodeIsExist.class);
        if (isExcelValid) {
            Object finalFieldValue = fieldValue;
            if (CacheUtil.isContainKey(Constant.COLLEGE_CODE_AND_COLLEGE_NAME_MAP)) {
                if (!((Map<String,String>) CacheUtil.get(Constant.COLLEGE_CODE_AND_COLLEGE_NAME_MAP)).containsKey(finalFieldValue))
                {
                    String message = field.getAnnotation(ExcelCheckCollegeCodeIsExist.class).message();
                    return StrUtil.format("第{}行{}不存在!", rowIndex, message);
                }else
                {
                    return null;
                }
            }else{
                String message = field.getAnnotation(ExcelCheckCollegeCodeIsExist.class).message();
                return StrUtil.format("第{}行{}不存在!", rowIndex, message);
            }
        }

        return null;
    }

    private static String excelCheckTeacherIsExist(Field field, Object fieldValue, Integer rowIndex) {
        boolean isExcelValid = field.isAnnotationPresent(ExcelCheckTeacherIsExist.class);
        if (isExcelValid)
        {
            StringBuilder sb = new StringBuilder();
            String finalFieldValue = (String) fieldValue;

            if (CacheUtil.isContainKey(Constant.ALL_TEACHER_NO))
            {
                List<String> finalFieldValueTeacherNosList = Arrays.asList(StrUtil.split(finalFieldValue, ","));
                List<String> allTeacherNosFromDb = (List<String>) CacheUtil.get(Constant.ALL_TEACHER_NO);
                finalFieldValueTeacherNosList.stream().forEach(item->{
                    if (!allTeacherNosFromDb.contains(item))
                    {
                        sb.append(item);
                        sb.append(",");
                    }
                });

                String message = field.getAnnotation(ExcelCheckTeacherIsExist.class).message();
                if (StrUtil.isNotBlank(sb.toString()))
                {
                    String sbString = sb.deleteCharAt(sb.length() - 1).toString();
                    return StrUtil.format("第{}行{}{}不存在!", rowIndex, message,sbString);
                }else {
                    return null;
                }

            }else {
                String message = field.getAnnotation(ExcelCheckTeacherIsExist.class).message();
                return StrUtil.format("第{}行{}{}不存在!", rowIndex, message,finalFieldValue);
            }
        }

        return null;
    }

    private static String excelCheckCourseCodeIsExist(Field field, Object fieldValue, Integer rowIndex) {
        boolean isExcelValid = field.isAnnotationPresent(ExcelCheckCourseCodeIsExist.class);
        if (isExcelValid)
        {
            Object finalFieldValue = fieldValue;
            if (CacheUtil.isContainKey(Constant.COURSE_CODE_AND_COURSE_NAME_MAP))
            {

                if (!((Map<String,String>) CacheUtil.get(Constant.COURSE_CODE_AND_COURSE_NAME_MAP)).containsKey(finalFieldValue))
                {
                    String message = field.getAnnotation(ExcelCheckCourseCodeIsExist.class).message();
                    return StrUtil.format("第{}行{}不存在!", rowIndex, message);
                }else
                {
                    return null;
                }
            }else{
                String message = field.getAnnotation(ExcelCheckCourseCodeIsExist.class).message();
                return StrUtil.format("第{}行{}不存在!", rowIndex, message);
            }
        }

        return null;
    }
    private static String excelMaxLength(Field field, Object fieldValue, Integer rowIndex) {
        boolean isExcelValid = field.isAnnotationPresent(ExcelMaxLength.class);
        if (isExcelValid&&(Integer.parseInt(field.getAnnotation(ExcelMaxLength.class).value())<((String)fieldValue).length()))
        {
            String name = field.getAnnotation(ExcelMaxLength.class).name();
            String value = field.getAnnotation(ExcelMaxLength.class).value();
            return StrUtil.format("第{}行{}不能超过{}个字符!", rowIndex,name, value);
        }

        return null;
    }

    private static String excelCheckTermOfSemesterIsExist(Field field, Object fieldValue, Integer rowIndex) {
        // 判断输入所属学期时间是否存在
        boolean isExcelValid = field.isAnnotationPresent(ExcelCheckTermOfSemesterIsExist.class);
        if (isExcelValid)
        {
            Object finalFieldValue = fieldValue;
            if ((CacheUtil.isContainKey(Constant.TERM_RESOURCE_ID_LIST)))
            {
                if (!((List<String>) CacheUtil.get(Constant.TERM_RESOURCE_ID_LIST))
                        .stream().anyMatch(e -> e.equals(finalFieldValue)))
                {
                    String message = field.getAnnotation(ExcelCheckTermOfSemesterIsExist.class).message();
                    return StrUtil.format("第{}行{}不存在!", rowIndex, message);
                }else{
                    return null;
                }
            }else
            {
                String message = field.getAnnotation(ExcelCheckTermOfSemesterIsExist.class).message();
                return StrUtil.format("第{}行{}不存在!", rowIndex, message);
            }
        }

        return null;
    }

    private static String excelCheckIsNull(Field field, Object fieldValue, Integer rowIndex) {
        boolean isExcelValid = field.isAnnotationPresent(ExcelCheckIsNull.class);
        if (isExcelValid && Objects.isNull(fieldValue)) {
            String message = field.getAnnotation(ExcelCheckIsNull.class).name();
            return StrUtil.format("第{}行{}字段不能为空!",rowIndex,message);
        }
        return null;
    }
    public static boolean isContainsChinese(String str) {
        if (str == null) { return false; }
        Pattern p = Pattern.compile(Constant.HAS_CHINESE);
        Matcher m = p.matcher(str);
        return m.find();
    }
    private static String excelCheckIsUniqueClassCode(Field field, Object fieldValue, Integer rowIndex)
    {
        boolean isExcelValid = field.isAnnotationPresent(ExcelCheckIsUniqueClassCode.class);
        if (isExcelValid)
        {
            if (isContainsChinese((String) fieldValue)) {
                String message = field.getAnnotation(ExcelCheckIsUniqueClassCode.class).message();
                return StrUtil.format("第{}行{}不能为中文!",rowIndex,message);
            }

            if (CacheUtil.isContainKey(Constant.GROUP_NO_LIST))
            {

                if (((List<String>) CacheUtil.get(Constant.GROUP_NO_LIST)).stream().anyMatch(e -> e.equals(fieldValue)))
                {
                    String message = field.getAnnotation(ExcelCheckIsUniqueClassCode.class).message();
                    return StrUtil.format("第{}行{}重复!",rowIndex,message);
                }else {
                    ((ArrayList<String>) CacheUtil.get(Constant.GROUP_NO_LIST)).add((String)fieldValue);
                    return null;
                }
            }else {
                    ArrayList<String> stringArrayList = new ArrayList<>();
                    stringArrayList.add((String)fieldValue);
                    CacheUtil.put(Constant.GROUP_NO_LIST,stringArrayList) ;
            }

        }

        return null;
    }

}

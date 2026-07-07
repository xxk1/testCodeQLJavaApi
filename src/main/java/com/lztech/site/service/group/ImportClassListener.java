package com.lztech.site.service.group;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;
import com.lztech.domain.model.college.College;
import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.coursecategory.CourseCategory;
import com.lztech.domain.model.coursetable.CourseTable;
import com.lztech.domain.model.coursetable.enums.Source;
import com.lztech.domain.model.coursetable.enums.StudentType;
import com.lztech.domain.model.coursetable.enums.WeekType;
import com.lztech.domain.model.group.Group;
import com.lztech.domain.model.group.enums.GroupAttribute;
import com.lztech.domain.model.group.enums.GroupStatus;
import com.lztech.persistence.repositories.college.CollegeRepository;
import com.lztech.persistence.repositories.course.CourseRepository;
import com.lztech.persistence.repositories.coursecategory.CourseCategoryRepository;
import com.lztech.persistence.repositories.group.GroupRepository;
import com.lztech.site.constants.Constant;
import com.lztech.site.service.coursetabledetail.CourseTableDetailService;
import com.lztech.site.service.group.annotation.AnnotationValidator;
import com.lztech.site.viewmodel.authorityapi.TeacherVo;
import com.lztech.site.viewmodel.group.ImportClassExcelObject;
import com.lztech.site.viewmodel.group.ImportClassVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static com.lztech.site.constants.Constant.EMPTY_TABLE_ERROR_CODE;
import static com.lztech.site.constants.Constant.TABLE_UPPER_LIMIT_ERROR_CODE;

@Slf4j
@Component
public class ImportClassListener implements ReadListener<ImportClassExcelObject> {


    private final CourseRepository courseRepository;

    private final CourseCategoryRepository courseCategoryRepository;

    private final GroupRepository groupRepository;


    private final CollegeRepository collegeRepository;

    private final CourseTableDetailService courseTableDetailService;

    public ImportClassListener(CourseRepository courseRepository,
                               CourseCategoryRepository courseCategoryRepository,
                               GroupRepository groupRepository,
                               CollegeRepository collegeRepository,
                               CourseTableDetailService courseTableDetailService) {

        this.courseRepository = courseRepository;
        this.courseCategoryRepository = courseCategoryRepository;
        this.groupRepository = groupRepository;
        this.collegeRepository = collegeRepository;
        this.courseTableDetailService = courseTableDetailService;
    }

    private List<ImportClassExcelObject> cachedDataList = ListUtils.newArrayListWithExpectedSize(Constant.BATCH_COUNT);

    @Override
    public void invoke(ImportClassExcelObject data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        ArrayList<String> everyRowErrorMessages = AnnotationValidator.valid(data, context.readRowHolder().getRowIndex());
        // 跳过空行
        if (StrUtil.isAllEmpty(
                data.getTermOfSemester(),
                data.getClassNo(),
                data.getClassName(),
                data.getClassAttribute(),
                data.getCourseCode(),
                data.getCourseName(),
                data.getOpenCollegeCode(),
                data.getOpenCollegeName(),
                data.getCourseCategory(),
                data.getCourseTeacherCodes())) {
            return;
        }

        // 课程编号与课程名称是否匹配
        if (StrUtil.isNotBlank(data.getCourseName()) && StrUtil.isNotBlank(data.getCourseCode())) {
            Map<String, String> courseCodeAndCourseNameMap = (Map<String, String>) CacheUtil.get(Constant.COURSE_CODE_AND_COURSE_NAME_MAP);
            if (MapUtil.isNotEmpty(courseCodeAndCourseNameMap)) {
                if (!data.getCourseName().equals(courseCodeAndCourseNameMap.get(data.getCourseCode()))) {
                    everyRowErrorMessages.add(StrUtil.format("第{}行课程编号与课程名称不匹配", context.readRowHolder().getRowIndex()));
                }
            }
        }

        // 开课学院与开课学院编号是否匹配
        if (StrUtil.isNotBlank(data.getOpenCollegeCode()) && StrUtil.isNotBlank(data.getOpenCollegeName())) {
            Map<String, String> collegeCodeAndCollegeNameMap = (Map<String, String>) CacheUtil.get(Constant.COLLEGE_CODE_AND_COLLEGE_NAME_MAP);
            if (MapUtil.isNotEmpty(collegeCodeAndCollegeNameMap)) {
                if (!data.getOpenCollegeName().equals(collegeCodeAndCollegeNameMap.get(data.getOpenCollegeCode()))) {
                    everyRowErrorMessages.add(StrUtil.format("第{}行开课学院与开课学院编号不匹配", context.readRowHolder().getRowIndex()));
                }
            }
        }


        if (!CacheUtil.isContainKey(Constant.ROW_INDEX)) {
            CacheUtil.put(Constant.ROW_INDEX, String.valueOf(Constant.ROW_INDEX_VALUE_INIT + 1));
        } else {
            CacheUtil.put(Constant.ROW_INDEX, String.valueOf(Integer.parseInt((String) CacheUtil.get(Constant.ROW_INDEX)) + 1));
        }

        if (!CacheUtil.isContainKey(Constant.EVERY_ROW_ERROR_MESSAGES)) {
            CacheUtil.put(Constant.EVERY_ROW_ERROR_MESSAGES, everyRowErrorMessages);
        } else {
            ((ArrayList<String>) CacheUtil.get(Constant.EVERY_ROW_ERROR_MESSAGES)).addAll(everyRowErrorMessages);
        }
        if (ObjectUtils.isEmpty(CacheUtil.get(Constant.EVERY_ROW_ERROR_MESSAGES))) {
            cachedDataList.add(data);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        ImportClassVo importClassVo = new ImportClassVo();
        if (!CacheUtil.isContainKey(Constant.ROW_INDEX)) {
            importClassVo.setSuccessRows(Constant.ROW_INDEX_VALUE_INIT);
        } else {
            importClassVo.setSuccessRows(Integer.parseInt((String) CacheUtil.get(Constant.ROW_INDEX)));
        }

        ArrayList<String> errorMessagesList = (ArrayList<String>) CacheUtil.get(Constant.EVERY_ROW_ERROR_MESSAGES);
        if (ObjectUtils.isEmpty(errorMessagesList)) {
            CacheUtil.put(Constant.EVERY_ROW_ERROR_MESSAGES, new ArrayList<String>());
        }
        errorMessagesList = (ArrayList<String>) CacheUtil.get(Constant.EVERY_ROW_ERROR_MESSAGES);

        if (ObjectUtils.isEmpty(CacheUtil.get(Constant.ROW_INDEX))) {
            errorMessagesList.clear();
            errorMessagesList.add(EMPTY_TABLE_ERROR_CODE);
            importClassVo.setErrorInfo(errorMessagesList);
            CacheUtil.put(Constant.IMPORT_CLASS_VO, importClassVo);
            return;
        }

        int count = Integer.parseInt((String) CacheUtil.get(Constant.ROW_INDEX));
        if (count >= Constant.ROW_INDEX_MAX_VALUE) {
            errorMessagesList.clear();
            errorMessagesList.add(TABLE_UPPER_LIMIT_ERROR_CODE);
            importClassVo.setErrorInfo(errorMessagesList);
            CacheUtil.put(Constant.IMPORT_CLASS_VO, importClassVo);
            return;
        }

        if (CollectionUtils.isNotEmpty(errorMessagesList)) {
            importClassVo.setErrorInfo(errorMessagesList);
            importClassVo.setErrorRows(errorMessagesList.size());

        }
        CacheUtil.put(Constant.IMPORT_CLASS_VO, importClassVo);
        if (CollectionUtils.isEmpty(errorMessagesList)) {
            savaData(cachedDataList);
        }
    }

    public void savaData(List<ImportClassExcelObject> cachedDataList) {
        String operatorId = (String) CacheUtil.get(Constant.OPERATOR_ID);
        String operatorName = (String) CacheUtil.get(Constant.OPERATOR_NAME);
        Date nowDate = new Date();
        List<String> courseCodeList = cachedDataList.stream().map(ImportClassExcelObject::getCourseCode).collect(Collectors.toList());
        List<String> courseCategoryNameList = cachedDataList.stream().map(ImportClassExcelObject::getCourseCategory).collect(Collectors.toList());
        List<Course> byCourseCodeIn = courseRepository.findByCourseCodeIn(courseCodeList);
        List<CourseCategory> allByCourseCategoryNameIn = courseCategoryRepository.findAllByCourseCategoryNameIn(courseCategoryNameList);
        Map<String, TeacherVo> teacherVoMap = (Map<String, TeacherVo>) CacheUtil.get(Constant.ALL_TEACHER_MODEL);

        cachedDataList.stream().forEach(importClassExcelObject -> {

            Group group = new Group();
            group.setId(IdUtil.simpleUUID());
            ArrayList<CourseTable> courseTableArrayList = new ArrayList<>();
            CourseTable courseTable = new CourseTable();
            groupSetBaseInfo(operatorId, operatorName, nowDate, group);
            group.setGroupNo(importClassExcelObject.getClassNo());
            group.setGroupName(importClassExcelObject.getClassName());
            group.setClassNickName(importClassExcelObject.getClassName());
            group.setGroupAttribute(GroupAttribute.getGroupAttributeName(importClassExcelObject.getClassAttribute()));
            group.setSource(Source.SYSTEM_ENTRY);
            group.setSort(0);
            group.setGroupStatus(GroupStatus.NORMAL);
            courseTable.setCreatorId(operatorId);
            courseTable.setCreatorName(operatorName);
            courseTable.setCreateTime(nowDate);
            courseTable.setSource(Source.SYSTEM_ENTRY);
            if (ObjectUtils.isNotEmpty(byCourseCodeIn)) {
                Course course = byCourseCodeIn.stream()
                        .filter(p -> importClassExcelObject.getCourseCode().equals(p.getCourseCode()))
                        .findFirst().orElse(null);
                if (ObjectUtils.isNotEmpty(course)) {
                    courseTable.setCourse(course);
                    courseTable.setCourseName(course.getCourseName());
                }

            }
            courseTable.setGroup(group);
            courseTable.setWeekType(WeekType.ONE_OR_TWO_WEEKS);
            if (ObjectUtils.isNotEmpty(allByCourseCategoryNameIn)) {
                List<CourseCategory> courseCategories = allByCourseCategoryNameIn.stream()
                        .filter(p -> importClassExcelObject.getCourseCategory().equals(p.getCourseCategoryName()))
                        .collect(Collectors.toList());
                if (ObjectUtils.isNotEmpty(courseCategories)) {
                    courseTable.setCourseCategory(courseCategories.get(0));
                }
            }
            List<String> finalFieldValueTeacherNosList = Arrays.asList(StrUtil.split(importClassExcelObject
                    .getCourseTeacherCodes(), ","));
            List<TeacherVo> currentTeacherVoList = new ArrayList<>();
            finalFieldValueTeacherNosList.forEach(teacherNo -> {
                TeacherVo teacherVo = teacherVoMap.get(teacherNo);
                currentTeacherVoList.add(teacherVo);
            });
            courseTable.setTeacherName(currentTeacherVoList.stream().map(TeacherVo::getTeacherName)
                    .distinct().collect(Collectors.joining(",")));
            courseTable.setStudentType(StudentType.UNDERGRADUATE);
            String termOfSemester = importClassExcelObject.getTermOfSemester();
            courseTable.setSchoolYear(termOfSemester.substring(0, termOfSemester.lastIndexOf("-")));
            courseTable.setTerm(Integer.parseInt(termOfSemester.substring(termOfSemester.lastIndexOf("-") + 1)));
            College college = collegeRepository.findByCollegeCode(importClassExcelObject.getOpenCollegeCode());
            courseTable.setCollegeId(college.getId());
            courseTable.setCollegeName(college.getCollegeName());
            courseTableArrayList.add(courseTable);
            group.setCourseTableList(courseTableArrayList);
            group = groupRepository.saveAndFlush(group);
            courseTableDetailService.saveCourseTableDetail(group.getCourseTableList().get(0),
                    operatorId, operatorName, currentTeacherVoList);
        });
    }


    private void groupSetBaseInfo(String operatorId, String operatorName, Date nowDate, Group group) {
        group.setCreatorId(operatorId);
        group.setModifierId(operatorId);
        group.setCreatorName(operatorName);
        group.setModifierName(operatorName);
        group.setCreateTime(nowDate);
        group.setModifyTime(nowDate);
    }
}

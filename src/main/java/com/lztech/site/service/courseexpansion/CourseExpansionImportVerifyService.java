package com.lztech.site.service.courseexpansion;

import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.course.CourseExpansion;
import com.lztech.persistence.repositories.course.CourseRepository;
import com.lztech.persistence.repositories.courseexpansion.CourseExpansionRepository;
import com.lztech.site.viewmodel.courseexpansion.CourseExpansionImportFileModel;
import com.lztech.site.viewmodel.courseexpansion.CourseExpansionImportValidateResource;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseExpansionImportVerifyService {

    private static final int MAX_VARCHAR_LENGTH = 255;

    private static final Map<Integer, String> COLUMN_MAPPING = new LinkedHashMap<>();

    static {
        COLUMN_MAPPING.put(0, "课程编号");
        COLUMN_MAPPING.put(1, "课程名称");
        COLUMN_MAPPING.put(2, "听课类型");
    }

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseExpansionRepository courseExpansionRepository;

    public CourseExpansionImportValidateResource validateAndParseExcel(MultipartFile importFile, Map<String, String> listeningTypeNameToKey)
            throws Exception {
        CourseExpansionImportValidateResource validateResource = new CourseExpansionImportValidateResource();
        validateFileType(importFile, validateResource);
        if (ObjectUtils.isNotEmpty(validateResource.getStatus()) && validateResource.getStatus() == 0) {
            return validateResource;
        }

        Set<String> courseCodeSet = new HashSet<>();
        List<RowParseTemp> rowParseTemps = new ArrayList<>();
        try (InputStream inputStream = importFile.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            validateHeaders(headerRow, validateResource);
            if (ObjectUtils.isNotEmpty(validateResource.getStatus()) && validateResource.getStatus() == 0) {
                return validateResource;
            }

            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }

                String courseCode = getCellValueAsString(row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL));
                String courseName = getCellValueAsString(row.getCell(1, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL));
                String listeningTypeName = getCellValueAsString(row.getCell(2, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL));

                if (StringUtils.isAllBlank(StringUtils.trimToEmpty(courseCode), StringUtils.trimToEmpty(courseName), StringUtils.trimToEmpty(listeningTypeName))) {
                    continue;
                }

                int excelRowIndex = rowNum + 1;

                courseCode = StringUtils.trimToNull(courseCode);
                courseName = StringUtils.trimToNull(courseName);
                listeningTypeName = StringUtils.trimToNull(listeningTypeName);

                if (StringUtils.isBlank(courseCode)) {
                    validateResource.setStatus(0);
                    validateResource.setMessage(String.format("第%d行，课程编号未填写", excelRowIndex));
                    return validateResource;
                }
                if (StringUtils.isBlank(courseName)) {
                    validateResource.setStatus(0);
                    validateResource.setMessage(String.format("第%d行，课程名称未填写", excelRowIndex));
                    return validateResource;
                }
                if (StringUtils.isBlank(listeningTypeName)) {
                    validateResource.setStatus(0);
                    validateResource.setMessage(String.format("第%d行，听课类型未填写", excelRowIndex));
                    return validateResource;
                }

                if (courseCode.length() > MAX_VARCHAR_LENGTH) {
                    validateResource.setStatus(0);
                    validateResource.setMessage(String.format("第%d行，课程编号字符长度超出限制", excelRowIndex));
                    return validateResource;
                }
                if (courseName.length() > MAX_VARCHAR_LENGTH) {
                    validateResource.setStatus(0);
                    validateResource.setMessage(String.format("第%d行，课程名称字符长度超出限制", excelRowIndex));
                    return validateResource;
                }
                if (listeningTypeName.length() > MAX_VARCHAR_LENGTH) {
                    validateResource.setStatus(0);
                    validateResource.setMessage(String.format("第%d行，听课类型字符长度超出限制", excelRowIndex));
                    return validateResource;
                }

                if (courseCodeSet.contains(courseCode)) {
                    validateResource.setStatus(0);
                    validateResource.setMessage(String.format("%s文档中有重复", courseCode));
                    return validateResource;
                }
                courseCodeSet.add(courseCode);

                String expansionKey = listeningTypeNameToKey.get(listeningTypeName);
                if (StringUtils.isBlank(expansionKey)) {
                    validateResource.setStatus(0);
                    validateResource.setMessage(String.format("第%d行，听课类型不存在", excelRowIndex));
                    return validateResource;
                }
                RowParseTemp rowParseTemp = new RowParseTemp();
                rowParseTemp.setExcelRowIndex(excelRowIndex);
                rowParseTemp.setCourseCode(courseCode);
                rowParseTemp.setCourseName(courseName);
                rowParseTemp.setListeningTypeName(listeningTypeName);
                rowParseTemp.setExpansionKey(expansionKey);
                rowParseTemps.add(rowParseTemp);
            }
        }

        if (!rowParseTemps.isEmpty()) {
            List<String> courseCodeList = rowParseTemps.stream()
                    .map(RowParseTemp::getCourseCode)
                    .filter(StringUtils::isNotBlank)
                    .collect(Collectors.toList());
            List<Course> courseList = courseRepository.findByCourseCodeInAndUseState(courseCodeList, true);
            Map<String, Course> courseCodeToCourse = courseList == null ? new LinkedHashMap<>() :
                    courseList.stream()
                            .filter(Objects::nonNull)
                            .collect(Collectors.toMap(Course::getCourseCode, c -> c, (a, b) -> a));

            List<String> courseIdList = courseCodeToCourse.values().stream()
                    .filter(Objects::nonNull)
                    .map(Course::getId)
                    .filter(StringUtils::isNotBlank)
                    .collect(Collectors.toList());
            Set<String> existedCourseIdSet = new HashSet<>();
            if (!courseIdList.isEmpty()) {
                List<CourseExpansion> existedExpansionList = courseExpansionRepository.findByCourseIdIn(courseIdList);
                if (existedExpansionList != null) {
                    existedCourseIdSet = existedExpansionList.stream()
                            .filter(Objects::nonNull)
                            .map(e -> StringUtils.trimToNull(e.getCourseId()))
                            .filter(Objects::nonNull)
                            .collect(Collectors.toSet());
                }
            }

            for (RowParseTemp rowParseTemp : rowParseTemps) {
                Course course = courseCodeToCourse.get(rowParseTemp.getCourseCode());
                if (ObjectUtils.isEmpty(course)) {
                    validateResource.setStatus(0);
                    validateResource.setMessage(String.format("第%d行，课程编号不存在", rowParseTemp.getExcelRowIndex()));
                    return validateResource;
                }
                if (!StringUtils.equals(StringUtils.trimToEmpty(course.getCourseName()), rowParseTemp.getCourseName())) {
                    validateResource.setStatus(0);
                    validateResource.setMessage(String.format("第%d行，课程编号与课程名称不匹配", rowParseTemp.getExcelRowIndex()));
                    return validateResource;
                }
                if (existedCourseIdSet.contains(course.getId())) {
                    validateResource.setStatus(0);
                    validateResource.setMessage(String.format("%s已录入过，请勿重复导入。", rowParseTemp.getCourseCode()));
                    return validateResource;
                }

                CourseExpansionImportFileModel model = new CourseExpansionImportFileModel();
                model.setCourseId(course.getId());
                model.setCourseCode(rowParseTemp.getCourseCode());
                model.setCourseName(rowParseTemp.getCourseName());
                model.setListeningTypeName(rowParseTemp.getListeningTypeName());
                model.setExpansionKey(rowParseTemp.getExpansionKey());
                validateResource.getCourseExpansionImportFileModelList().add(model);
            }
        }

        validateResource.setStatus(1);
        return validateResource;
    }

    private void validateHeaders(Row headerRow, CourseExpansionImportValidateResource validateResource) {
        if (headerRow == null) {
            validateResource.setStatus(0);
            validateResource.setMessage("您导入的模板不正确，请下载模板按格式导入");
            return;
        }
        for (int i = 0; i < COLUMN_MAPPING.size(); i++) {
            Cell cell = headerRow.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            String actualHeader = getCellValueAsString(cell);
            if (ObjectUtils.isNotEmpty(actualHeader)) {
                actualHeader = actualHeader.replaceAll("\\*", "").trim();
            }
            String expectedHeader = COLUMN_MAPPING.get(i);
            if (!expectedHeader.equals(actualHeader)) {
                validateResource.setStatus(0);
                validateResource.setMessage("您导入的模板不正确，请下载模板按格式导入");
                return;
            }
        }
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return null;
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    double numericValue = cell.getNumericCellValue();
                    if (numericValue == Math.floor(numericValue)) {
                        return String.valueOf((long) numericValue);
                    } else {
                        return String.valueOf(numericValue);
                    }
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
        }
    }

    private void validateFileType(MultipartFile file, CourseExpansionImportValidateResource validateResource) {
        if (file == null || file.isEmpty()) {
            validateResource.setStatus(0);
            validateResource.setMessage("上传文件为空");
            return;
        }
        String fileName = file.getOriginalFilename();
        if (fileName == null || !fileName.toLowerCase().endsWith(".xlsx")) {
            validateResource.setStatus(0);
            validateResource.setMessage("上传的文件个数不为xlsx");
        }
    }

    private static class RowParseTemp {
        private Integer excelRowIndex;
        private String courseCode;
        private String courseName;
        private String listeningTypeName;
        private String expansionKey;

        public Integer getExcelRowIndex() {
            return excelRowIndex;
        }

        public void setExcelRowIndex(Integer excelRowIndex) {
            this.excelRowIndex = excelRowIndex;
        }

        public String getCourseCode() {
            return courseCode;
        }

        public void setCourseCode(String courseCode) {
            this.courseCode = courseCode;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getListeningTypeName() {
            return listeningTypeName;
        }

        public void setListeningTypeName(String listeningTypeName) {
            this.listeningTypeName = listeningTypeName;
        }

        public String getExpansionKey() {
            return expansionKey;
        }

        public void setExpansionKey(String expansionKey) {
            this.expansionKey = expansionKey;
        }
    }
}

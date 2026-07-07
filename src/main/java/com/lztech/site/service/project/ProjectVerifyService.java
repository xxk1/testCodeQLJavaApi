package com.lztech.site.service.project;

import com.lztech.domain.model.course.Course;
import com.lztech.domain.model.project.enums.*;
import com.lztech.domain.model.projectclassification.ProjectClassification;
import com.lztech.persistence.repositories.course.CourseRepository;
import com.lztech.persistence.repositories.projectclassification.ProjectClassificationRepository;
import com.lztech.site.constants.Constant;
import com.lztech.site.viewmodel.project.ProjectFileModel;
import com.lztech.site.viewmodel.project.ProjectValidateResource;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;

@Service
public class ProjectVerifyService {

    @Autowired
    private ProjectClassificationRepository projectClassificationRepository;
    @Autowired
    private CourseRepository courseRepository;


    // 列映射关系
    private static final Map<Integer, String> COLUMN_MAPPING = new LinkedHashMap<>();
    static {
        COLUMN_MAPPING.put(0, "项目名称");
        COLUMN_MAPPING.put(1, "关联课程");
        COLUMN_MAPPING.put(Constant.TWO, "所属课程编号");
        COLUMN_MAPPING.put(Constant.THREE, "项目类别");
        COLUMN_MAPPING.put(Constant.FOUR, "项目种类");
        COLUMN_MAPPING.put(Constant.FIVE, "项目要求");
        COLUMN_MAPPING.put(Constant.SIX, "项目类型");
        COLUMN_MAPPING.put(Constant.SEVEN, "项目分类");
        COLUMN_MAPPING.put(Constant.EIGHT, "项目内容");
    }

    public ProjectValidateResource validateAndParseExcel(MultipartFile projectFile)
            throws Exception {
        List<ProjectClassification> projectClassificationList =  projectClassificationRepository.findAll();

        Set<String> existingClassificationList = new HashSet<>();
        for (ProjectClassification projectClassification : projectClassificationList) {
            existingClassificationList.add(projectClassification.getClassificationName());
        }

        ProjectValidateResource projectValidateResource = new ProjectValidateResource();
        // 1. 校验文件类型
        validateFileType(projectFile,projectValidateResource);
        if (ObjectUtils.isNotEmpty(projectValidateResource.getStatus()) && projectValidateResource.getStatus() == 0){
            return projectValidateResource;
        }
        List<ProjectFileModel> projectModels = new ArrayList<>();
        try (InputStream inputStream = projectFile.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            // 2. 校验列名称
            validateHeaders(headerRow,projectValidateResource);
            if (ObjectUtils.isNotEmpty(projectValidateResource.getStatus()) && projectValidateResource.getStatus() == 0){
                return projectValidateResource;
            }
            // 逐行校验数据
            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }

                // 创建一个ProjectFileModel实例
                ProjectFileModel projectModel = new ProjectFileModel();

                // 为每个字段赋值
                for (int colIndex = 0; colIndex < COLUMN_MAPPING.size(); colIndex++) {
                    Cell cell = row.getCell(colIndex, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    String cellValue = getCellValueAsString(cell);
                    String fieldName = COLUMN_MAPPING.get(colIndex);

                    // 设置对应的属性值
                    setModelProperty(projectModel, fieldName, cellValue);
                }

                // 3. 校验必填字段
                validateRequiredFields(projectModel, rowNum + 1,projectValidateResource);
                if (ObjectUtils.isNotEmpty(projectValidateResource.getStatus()) && projectValidateResource.getStatus() == 0){
                    return projectValidateResource;
                }
                // 4. 校验字段长度
                validateFieldLength(projectModel, rowNum + 1,projectValidateResource);
                if (ObjectUtils.isNotEmpty(projectValidateResource.getStatus()) && projectValidateResource.getStatus() == 0){
                    return projectValidateResource;
                }
                // 5. 校验枚举字段
                validateEnumFields(projectModel, rowNum + 1,projectValidateResource);
                if (ObjectUtils.isNotEmpty(projectValidateResource.getStatus()) && projectValidateResource.getStatus() == 0){
                    return projectValidateResource;
                }
                // 6. 校验项目分类
                validateClassification(projectModel, rowNum + 1, existingClassificationList,projectValidateResource);
                if (ObjectUtils.isNotEmpty(projectValidateResource.getStatus()) && projectValidateResource.getStatus() == 0){
                    return projectValidateResource;
                }
                // 7. 校验所属课程编号(关联课程:是)
                validateCourseCode(projectModel, rowNum + 1,projectValidateResource);
                if (ObjectUtils.isNotEmpty(projectValidateResource.getStatus()) && projectValidateResource.getStatus() == 0){
                    return projectValidateResource;
                }
                projectModels.add(projectModel);
            }
        }
        projectValidateResource.setStatus(1);
        projectValidateResource.setProjectFileModelList(projectModels);
        return projectValidateResource;
    }
    private void validateCourseCode(ProjectFileModel projectModel, int rowNum,ProjectValidateResource projectValidateResource) {

        String courseCode = projectModel.getCourseCode();

        if (courseCode != null && !courseCode.trim().isEmpty() && projectModel.getWhetherAssociateCoursesName().equals("是")) {
            Course filterCourse =  courseRepository.findByCourseCode(courseCode.trim());
            if (ObjectUtils.isEmpty(filterCourse)){
                projectValidateResource.setStatus(0);
                projectValidateResource.setMessage(String.format("第%d行，所属课程编号不存在", rowNum));
            }
        }
    }
    private void validateClassification(ProjectFileModel projectModel, int rowNum,
                                        Set<String> existingClassifications,ProjectValidateResource projectValidateResource) {
        String classification = projectModel.getClassificationName();
        if (classification != null && !classification.trim().isEmpty() &&
                !existingClassifications.contains(classification.trim())) {
            projectValidateResource.setStatus(0);
            projectValidateResource.setMessage(String.format("第%d行，项目分类与填写要求不一致，请按模板示例填写", rowNum));
        }
    }
    private void validateEnumFields(ProjectFileModel projectModel, int rowNum, ProjectValidateResource projectValidateResource) {

        if (projectModel.getWhetherAssociateCoursesName() != null && !projectModel.getWhetherAssociateCoursesName().trim().isEmpty() &&
                ObjectUtils.isEmpty(WhetherAssociateCourses.getWhetherAssociateCourses(projectModel.getWhetherAssociateCoursesName().trim()))) {
            projectValidateResource.setStatus(0);
            projectValidateResource.setMessage(String.format("第%d行，关联课程与填写要求不一致，请按模板示例填写", rowNum));
            return;
        }

        if (projectModel.getProjectCategoryName() != null && !projectModel.getProjectCategoryName().trim().isEmpty() &&
                ObjectUtils.isEmpty(ProjectCategory.getProjectCategory(projectModel.getProjectCategoryName().trim()))) {
            projectValidateResource.setStatus(0);
            projectValidateResource.setMessage(String.format("第%d行，项目类别与填写要求不一致，请按模板示例填写", rowNum));
            return;
        }

        if (projectModel.getProjectTypeName() != null && !projectModel.getProjectTypeName().trim().isEmpty() &&
                ObjectUtils.isEmpty(ProjectType.getProjectType(projectModel.getProjectTypeName().trim()))) {
            projectValidateResource.setStatus(0);
            projectValidateResource.setMessage(String.format("第%d行，项目种类与填写要求不一致，请按模板示例填写", rowNum));
            return;
        }

        if (projectModel.getProjectClaimName() != null && !projectModel.getProjectClaimName().trim().isEmpty() &&
                ObjectUtils.isEmpty(ProjectClaim.getProjectClaim(projectModel.getProjectClaimName().trim()))) {
            projectValidateResource.setStatus(0);
            projectValidateResource.setMessage(String.format("第%d行，项目要求与填写要求不一致，请按模板示例填写", rowNum));
            return;
        }

        if (projectModel.getProjectGenreName() != null && !projectModel.getProjectGenreName().trim().isEmpty() &&
                ObjectUtils.isEmpty(ProjectGenre.getProjectGenre(projectModel.getProjectGenreName().trim()))) {
            projectValidateResource.setStatus(0);
            projectValidateResource.setMessage(String.format("第%d行，项目类型与填写要求不一致，请按模板示例填写", rowNum));
            return;
        }
    }

    private void validateFieldLength(ProjectFileModel projectModel, int rowNum,ProjectValidateResource projectValidateResource) {
        if (projectModel.getProjectName() != null
                && projectModel.getProjectName().length() < Constant.TWO) {
            projectValidateResource.setStatus(0);
            projectValidateResource.setMessage(String.format("第%d行，项目名称字符长度小于最低限制", rowNum));
            return;
        }
        if (projectModel.getProjectName() != null
                && projectModel.getProjectName().length() > Constant.ONE_HUNDRED) {
            projectValidateResource.setStatus(0);
            projectValidateResource.setMessage(String.format("第%d行，项目名称字符长度超出限制", rowNum));
            return;
        }

        if (projectModel.getProjectDescription() != null
                && projectModel.getProjectDescription().length() > Constant.MAGIC_TWO_HUNDRED) {
            projectValidateResource.setStatus(0);
            projectValidateResource.setMessage(String.format("第%d行，项目内容字符长度超出限制", rowNum));
            return;
        }
    }
    private void validateRequiredFields(ProjectFileModel projectModel, int rowNum, ProjectValidateResource projectValidateResource) {
        if (isNullOrEmpty(projectModel.getProjectName())) {
            projectValidateResource.setStatus(0);
            projectValidateResource.setMessage(String.format("第%d行，项目名称未填写", rowNum));
            return;
        }

        if (isNullOrEmpty(projectModel.getWhetherAssociateCoursesName())) {
            projectValidateResource.setStatus(0);
            projectValidateResource.setMessage(String.format("第%d行，关联课程未填写", rowNum));
            return;
        }

        if (projectModel.getWhetherAssociateCoursesName().equals("是") && isNullOrEmpty(projectModel.getCourseCode())) {
            projectValidateResource.setStatus(0);
            projectValidateResource.setMessage(String.format("第%d行，所属课程编号未填写", rowNum));
            return;
        }

        if (isNullOrEmpty(projectModel.getProjectCategoryName())) {
            projectValidateResource.setStatus(0);
            projectValidateResource.setMessage(String.format("第%d行，项目类别未填写", rowNum));
            return;
        }

        if (isNullOrEmpty(projectModel.getProjectTypeName())) {
            projectValidateResource.setStatus(0);
            projectValidateResource.setMessage(String.format("第%d行，项目种类未填写", rowNum));
            return;
        }

        if (isNullOrEmpty(projectModel.getProjectClaimName())) {
            projectValidateResource.setStatus(0);
            projectValidateResource.setMessage(String.format("第%d行，项目要求未填写", rowNum));
            return;
        }

        if (isNullOrEmpty(projectModel.getProjectGenreName())) {
            projectValidateResource.setStatus(0);
            projectValidateResource.setMessage(String.format("第%d行，项目类型未填写", rowNum));
            return;
        }
    }
    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
    private void setModelProperty(ProjectFileModel projectModel, String fieldName, String value) {
        switch (fieldName) {
            case "项目名称":
                projectModel.setProjectName(value);
                break;
            case "关联课程":
                projectModel.setWhetherAssociateCoursesName(value);
                break;
            case "所属课程编号":
                projectModel.setCourseCode(value);
                break;
            case "项目类别":
                projectModel.setProjectCategoryName(value);
                break;
            case "项目种类":
                projectModel.setProjectTypeName(value);
                break;
            case "项目要求":
                projectModel.setProjectClaimName(value);
                break;
            case "项目类型":
                projectModel.setProjectGenreName(value);
                break;
            case "项目分类":
                projectModel.setClassificationName(value);
                break;
            case "项目内容":
                projectModel.setProjectDescription(value);
                break;
            default:
                break;
        }
    }
    private void validateHeaders(Row headerRow,ProjectValidateResource projectValidateResource) {
        if (headerRow == null) {
            projectValidateResource.setStatus(0);
            projectValidateResource.setMessage("您导入的模板不正确，请下载模板按格式导入");
        }
        for (int i = 0; i < COLUMN_MAPPING.size(); i++) {
            Cell cell = headerRow.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            String actualHeader = getCellValueAsString(cell);
            // 移除实际表头中的*号
            if (ObjectUtils.isNotEmpty(actualHeader)) {
                actualHeader = actualHeader.replaceAll("\\*", "").trim();
            }
            String expectedHeader = COLUMN_MAPPING.get(i);
            if (!expectedHeader.equals(actualHeader)) {
                projectValidateResource.setStatus(0);
                projectValidateResource.setMessage("您导入的模板不正确，请下载模板按格式导入");
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
                    // 检查是否为整数
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
    private void validateFileType(MultipartFile file, ProjectValidateResource projectValidateResource) {
        if (file == null || file.isEmpty()) {
            projectValidateResource.setStatus(0);
            projectValidateResource.setMessage("上传文件为空");
        }
        String fileName = file.getOriginalFilename();
        if (fileName == null || !fileName.toLowerCase().endsWith(".xlsx")) {
            projectValidateResource.setStatus(0);
            projectValidateResource.setMessage("上传的文件个数不为xlsx");
        }
    }
}

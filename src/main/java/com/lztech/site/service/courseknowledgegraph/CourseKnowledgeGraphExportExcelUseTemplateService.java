package com.lztech.site.service.courseknowledgegraph;

import com.lztech.domain.model.knowledgegraph.CourseKnowledgeGraphNodeRelationship;
import com.lztech.domain.model.knowledgegraph.enums.LevelType;
import com.lztech.site.until.Neo4jUtil;
import com.lztech.site.viewmodel.courseknowledgegraph.CourseKnowledgeGraphNodeDetailTreeModel;
import com.lztech.site.viewmodel.courseknowledgegraph.CourseKnowledgeGraphNodeRelationshipResource;
import com.lztech.site.viewmodel.courseknowledgegraph.CourseKnowledgeGraphNodeVo;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseKnowledgeGraphExportExcelUseTemplateService {

    @Autowired
    private Neo4jUtil neo4jUtil;

    // 内部类用于处理节点信息（包含额外字段）
    private static class EnhancedFlatNode {
        private final List<String> path;
        private final CourseKnowledgeGraphNodeDetailTreeModel node;
        private String preNode;        // 前置知识点
        private String postNode;        // 后置知识点
        private String relatedNode;     // 关联知识点
        private String importance;      // 重要层级
        private String courseKnowledgeGraphDomainId;

        public EnhancedFlatNode(List<String> path, CourseKnowledgeGraphNodeDetailTreeModel node, String courseKnowledgeGraphDomainId) {
            this.path = path;
            this.node = node;
            this.courseKnowledgeGraphDomainId = courseKnowledgeGraphDomainId;
        }

        public String getCourseKnowledgeGraphDomainId() {
            return courseKnowledgeGraphDomainId;
        }

    }

    public void exportData(HttpServletResponse response, CourseKnowledgeGraphNodeDetailTreeModel detailTreeModel,
                           String courseKnowledgeGraphDomainId) throws Exception {

        ClassPathResource templateResource = new ClassPathResource("templates/knowledgeImportTemplate.xlsx");
        try (InputStream templateInputStream = templateResource.getInputStream();


             Workbook workbook = new XSSFWorkbook(templateInputStream);
             OutputStream output = response.getOutputStream()) {

            Sheet sheet = workbook.getSheetAt(0); // 获取第一个工作表
            // 2. 获取模板中的边框样式（从第一行数据单元格中获取）
            Row sampleRow = sheet.getRow(2); // 模板中第一行数据行（索引2）
            CellStyle borderStyle = null;
            if (sampleRow != null && sampleRow.getCell(0) != null) {
                borderStyle = sampleRow.getCell(0).getCellStyle();
            }

            // 3. 如果模板中没有预设样式，创建一个默认边框样式
            if (borderStyle == null) {
                borderStyle = createBorderStyle(workbook);
            }

            // 2. 准备要导出的数据
            List<CourseKnowledgeGraphNodeDetailTreeModel> detailTreeModelList = detailTreeModel.getSubsetCourseKnowledgeGraphNodeTreeList();
            List<EnhancedFlatNode> flatNodes = flattenTree(detailTreeModelList, courseKnowledgeGraphDomainId);
            List<Map<String, String>> displayData = processForDisplay(flatNodes);
            // 3. 从模板的第3行开始填充数据（跳过说明行和表头行）
            int rowIndex = 2; // Excel行索引从0开始，第3行为索引2
            for (Map<String, String> rowData : displayData) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) {
                    row = sheet.createRow(rowIndex);
                }

                // 填充各列数据
                for (int i = 0; i < 15; i++) {
                    Cell cell = row.getCell(i);
                    if (cell == null) {
                        cell = row.createCell(i);
                    }
                    cell.setCellValue(rowData.getOrDefault(getColumnKey(i), ""));
                    // 确保所有单元格应用边框样式
                    cell.setCellStyle(borderStyle);
                }
                rowIndex++;
            }

            // 4. 设置响应头
            response.setHeader("Content-disposition", "attachment;filename=" + new String("知识图谱".getBytes("gb2312"), "ISO-8859-1") + ".xlsx");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            workbook.write(output);
        }
    }

    // 创建边框样式（确保所有单元格都有边框）
    private CellStyle createBorderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();

        // 设置细边框
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);

        // 设置垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        return style;
    }

    // 递归展平树形结构
    // 递归展平树形结构并增强节点数据
    private List<EnhancedFlatNode> flattenTree(List<CourseKnowledgeGraphNodeDetailTreeModel> nodes, String courseKnowledgeGraphDomainId) {
        return flattenTree(nodes, new ArrayList<>(), courseKnowledgeGraphDomainId);
    }


    private List<EnhancedFlatNode> flattenTree(List<CourseKnowledgeGraphNodeDetailTreeModel> nodes,
                                               List<String> parentPath, String courseKnowledgeGraphDomainId) {
        List<EnhancedFlatNode> result = new ArrayList<>();
        for (CourseKnowledgeGraphNodeDetailTreeModel node : nodes) {
            List<String> currentPath = new ArrayList<>(parentPath);
            currentPath.add(node.getKnowledgeNodeName());

            // 创建增强节点
            EnhancedFlatNode flatNode = new EnhancedFlatNode(currentPath, node, courseKnowledgeGraphDomainId);

            // 获取节点关系数据（前置、后置、关联知识点）
            fetchNodeRelationships(flatNode);

            // 处理重要层级（根据levelType转换）
            flatNode.importance = convertLevelType(node.getLevelType());

            result.add(flatNode);

            // 递归处理子节点
            if (node.getSubsetCourseKnowledgeGraphNodeTreeList() != null) {
                result.addAll(flattenTree(node.getSubsetCourseKnowledgeGraphNodeTreeList(), currentPath, courseKnowledgeGraphDomainId));
            }
        }
        return result;
    }

    private void fetchNodeRelationships(EnhancedFlatNode flatNode) {
        // 调用您提供的关系获取方法
        CourseKnowledgeGraphNodeRelationshipResource relationships =
                getCourseKnowledgeGraphNodeRelationshipList(
                        flatNode.getCourseKnowledgeGraphDomainId(),
                        flatNode.node.getId()
                );

        // 处理前置知识点
        flatNode.preNode = relationships.getPreposeKnowledgeGraphNodeList().stream()
                .map(CourseKnowledgeGraphNodeVo::getKnowledgeNodeName)
                .collect(Collectors.joining(";"));

        // 处理后置知识点
        flatNode.postNode = relationships.getPostposeKnowledgeGraphNodeList().stream()
                .map(CourseKnowledgeGraphNodeVo::getKnowledgeNodeName)
                .collect(Collectors.joining(";"));

        // 处理关联知识点
        flatNode.relatedNode = relationships.getRelevanceKnowledgeGraphNodeList().stream()
                .map(CourseKnowledgeGraphNodeVo::getKnowledgeNodeName)
                .collect(Collectors.joining(";"));
    }

    // 将levelType转换为重要层级文本
    private String convertLevelType(Integer levelType) {
        if (levelType == null) {
            return "";
        }

        LevelType levelTypeEnum = LevelType.getLevelType(levelType);
        if (levelTypeEnum == null) {
            return "";
        }
        return levelTypeEnum.getName();
    }

    // 处理显示数据（关键：实现父节点只在第一个子节点显示）
    // 处理显示数据（包含所有字段）
    private List<Map<String, String>> processForDisplay(List<EnhancedFlatNode> flatNodes) {
        List<Map<String, String>> displayData = new ArrayList<>();
        List<String> prevPath = new ArrayList<>();

        for (int i = 0; i < flatNodes.size(); i++) {
            EnhancedFlatNode flatNode = flatNodes.get(i);
            List<String> currentPath = flatNode.path;
            Map<String, String> row = new LinkedHashMap<>();

            // 处理层级显示
            for (int level = 0; level < 10; level++) {
                String levelKey = "level" + (level + 1);

                if (level < currentPath.size() && shouldDisplayLevel(currentPath, prevPath, level, i)) {
                    row.put(levelKey, currentPath.get(level));
                } else {
                    row.put(levelKey, "");
                }
            }
            // 添加其他字段
            row.put("preNode", flatNode.preNode);              // 前置知识点
            row.put("postNode", flatNode.postNode);            // 后置知识点
            row.put("relatedNode", flatNode.relatedNode);       // 关联知识点
            row.put("importance", flatNode.importance);        // 重要层级
            row.put("content", handleContentDetail(flatNode.node.getContentDetail())); // 知识点内容详情
            displayData.add(row);
            prevPath = currentPath;
        }

        return displayData;
    }

    private String handleContentDetail(String contentDetail) {
        if (StringUtils.isBlank(contentDetail) || "null".equals(contentDetail)) {
            return "";
        }
        return convertHtml(contentDetail);
    }

    public String convertHtml(String htmlContent) {
        Document document = Jsoup.parse(htmlContent);
        Elements elementList = document.getElementsByTag("img");
        int imgNum = 0;
        int formulaNum = 0;
        for (Element element : elementList) {
            String className = element.attr("class");
            if (StringUtils.isNotBlank(className)) {
                formulaNum = formulaNum + 1;
                String formulaName = "[公式" + formulaNum + "]";
                // 创建一个新的Element
                Element newElement = document.createElement("span");
                newElement.text(formulaName);
                newElement.attr("class", "formula_" + formulaNum);
                element.replaceWith(newElement);
            } else {
                imgNum = imgNum + 1;
                String imgName = "[图" + imgNum + "]";
                // 创建一个新的Element
                Element newElement = document.createElement("span");
                newElement.text(imgName);
                newElement.attr("class", "img_" + imgName);
                element.replaceWith(newElement);
            }
        }
        htmlContent = Jsoup.clean(document.outerHtml(), Whitelist.none()).replace("&nbsp;", "");
        return StringEscapeUtils.unescapeHtml4(htmlContent);
    }

    // 智能显示层级算法
    private boolean shouldDisplayLevel(List<String> currentPath,
                                       List<String> prevPath,
                                       int level,
                                       int rowIndex) {
        if (rowIndex == 0) {
            return true;
        }
        if (level >= prevPath.size()) {
            return true;
        }
        if (!currentPath.get(level).equals(prevPath.get(level))) {
            return true;
        }

        for (int i = 0; i < level; i++) {
            if (i < currentPath.size() && i < prevPath.size() &&
                    !currentPath.get(i).equals(prevPath.get(i))) {
                return true;
            }
        }

        return false;
    }

    // 创建说明行
    private void createInstructionRow(Sheet sheet, Workbook workbook) {
        Row instructionRow = sheet.createRow(0);
        Cell instructionCell = instructionRow.createCell(0);

        // 设置说明文本
        String instructionText = "说明：\n" +
                "1、知识点的层级关系，每行只能填写一个知识点\n" +
                "2、除一级知识点外，其余层级知识点若已填写必须有上级知识点\n" +
                "3、若知识图谱中已经存在导入知识点，将不会重复导入该知识点\n" +
                "4、知识点的前置、后置和关联知识点，多个知识点之间用英文分号\";\"隔开\n" +
                "5、任意两个知识点之间只能存在一种关系（前置、后置或关联），新导入的关系将覆盖旧关系\n" +
                "6、重要层级分为：次要、一般、重要、极其重要四个等级，每个知识点仅可填写一个（可不填）\n" +
                "7、知识点说明仅支持输入文本，暂不支持图片、公式等";

        instructionCell.setCellValue(instructionText);

        // 设置自动换行样式
        CellStyle instructionStyle = workbook.createCellStyle();
        instructionStyle.setWrapText(true);
        instructionCell.setCellStyle(instructionStyle);

        // 合并说明行
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 14));
    }

    // 创建表头行
    private void createHeaderRow(Sheet sheet, CellStyle headerStyle) {
        Row headerRow = sheet.createRow(1); // 第2行
        String[] headers = {
                "一级知识点", "二级知识点", "三级知识点", "四级知识点", "五级知识点",
                "六级知识点", "七级知识点", "八级知识点", "九级知识点", "十级知识点",
                "前置知识点", "后置知识点", "关联知识点", "重要层级", "知识点内容详情"
        };

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }
    }

    // 获取列键值
    private String getColumnKey(int index) {
        if (index < 10) {
            return "level" + (index + 1);
        }
        switch (index) {
            case 10:
                return "preNode";
            case 11:
                return "postNode";
            case 12:
                return "relatedNode";
            case 13:
                return "importance";
            case 14:
                return "content";
            default:
                return "";
        }
    }

    // 创建表头样式（适配POI 4.0.1）
    private CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();

        // 设置字体
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        // 设置对齐
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        // 设置边框
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);

        // 设置背景色（4.0.1使用IndexedColors）
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        return style;
    }

    // 创建标题样式（适配POI 4.0.1）
    private CellStyle createTitleStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();

        // 设置字体
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 16);
        style.setFont(font);

        // 设置对齐
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        return style;
    }

    public CourseKnowledgeGraphNodeRelationshipResource getCourseKnowledgeGraphNodeRelationshipList(
            String courseKnowledgeGraphDomainId, String courseKnowledgeGraphNodeId) {
        CourseKnowledgeGraphNodeRelationshipResource resource = new CourseKnowledgeGraphNodeRelationshipResource();

        // 前置关系
        List<CourseKnowledgeGraphNodeVo> preposeKnowledgeGraphNodeList = new ArrayList<>();
        // 后置关系
        List<CourseKnowledgeGraphNodeVo> postposeKnowledgeGraphNodeList = new ArrayList<>();
        // 关联关系
        List<CourseKnowledgeGraphNodeVo> relevanceKnowledgeGraphNodeList = new ArrayList<>();

        // 模拟获取关系数据
        List<CourseKnowledgeGraphNodeRelationship> relationships =
                neo4jUtil.getCourseKnowledgeGraphNodeRelationshipList(courseKnowledgeGraphDomainId, courseKnowledgeGraphNodeId);

        for (CourseKnowledgeGraphNodeRelationship relationship : relationships) {
            if (relationship.getRelationshipType().equals("FRONT_REAR")) {
                //前后置关系
                getCourseKnowledgeGraphNodeVoList(courseKnowledgeGraphNodeId, preposeKnowledgeGraphNodeList,
                        postposeKnowledgeGraphNodeList, relationship);
            } else if (relationship.getRelationshipType().equals("RELEVANCE")) {
                //关联关系
                getCourseKnowledgeGraphNodeVoList(courseKnowledgeGraphNodeId, relevanceKnowledgeGraphNodeList,
                        relevanceKnowledgeGraphNodeList, relationship);
            }
        }

        resource.setPreposeKnowledgeGraphNodeList(preposeKnowledgeGraphNodeList.
                stream().sorted(Comparator.comparing(CourseKnowledgeGraphNodeVo::getRelationshipCreateTimestamp,
                        Comparator.nullsFirst(Comparator.naturalOrder())))
                .collect(Collectors.toList()));
        resource.setPostposeKnowledgeGraphNodeList(postposeKnowledgeGraphNodeList.
                stream().sorted(Comparator.comparing(CourseKnowledgeGraphNodeVo::getRelationshipCreateTimestamp,
                        Comparator.nullsFirst(Comparator.naturalOrder())))
                .collect(Collectors.toList()));
        resource.setRelevanceKnowledgeGraphNodeList(relevanceKnowledgeGraphNodeList.
                stream().sorted(Comparator.comparing(CourseKnowledgeGraphNodeVo::getRelationshipCreateTimestamp,
                        Comparator.nullsFirst(Comparator.naturalOrder())))
                .collect(Collectors.toList()));

        return resource;
    }

    // 创建节点VO对象
    private CourseKnowledgeGraphNodeVo createNodeVo(CourseKnowledgeGraphNodeRelationship relationship, String nodeId) {
        CourseKnowledgeGraphNodeVo nodeVo = new CourseKnowledgeGraphNodeVo();
        nodeVo.setKnowledgeGraphNodeId(nodeId);
        nodeVo.setKnowledgeNodeName("节点名称"); // 实际应从数据库获取
        nodeVo.setRelationshipId(relationship.getRelationshipId());

        return nodeVo;
    }

    private void getCourseKnowledgeGraphNodeVoList(
            String courseKnowledgeGraphNodeId, List<CourseKnowledgeGraphNodeVo> preposeKnowledgeGraphNodeList,
            List<CourseKnowledgeGraphNodeVo> postposeKnowledgeGraphNodeList,
            CourseKnowledgeGraphNodeRelationship courseKnowledgeGraphNodeRelationship) {
        CourseKnowledgeGraphNodeVo courseKnowledgeGraphNodeVo = new CourseKnowledgeGraphNodeVo();
        if (courseKnowledgeGraphNodeRelationship.getEndNodeId().equals(courseKnowledgeGraphNodeId)) {
            courseKnowledgeGraphNodeVo.setKnowledgeGraphNodeId(courseKnowledgeGraphNodeRelationship.getStartNodeId());
            courseKnowledgeGraphNodeVo.setKnowledgeNodeName(courseKnowledgeGraphNodeRelationship.getStartNodeName());
            courseKnowledgeGraphNodeVo.setRelationshipId(courseKnowledgeGraphNodeRelationship.getRelationshipId());
            courseKnowledgeGraphNodeVo.setRelationshipCreateTimestamp(courseKnowledgeGraphNodeRelationship.getRelationshipCreateTimestamp());
            preposeKnowledgeGraphNodeList.add(courseKnowledgeGraphNodeVo);
        } else {
            courseKnowledgeGraphNodeVo.setKnowledgeGraphNodeId(courseKnowledgeGraphNodeRelationship.getEndNodeId());
            courseKnowledgeGraphNodeVo.setKnowledgeNodeName(courseKnowledgeGraphNodeRelationship.getEndNodeName());
            courseKnowledgeGraphNodeVo.setRelationshipId(courseKnowledgeGraphNodeRelationship.getRelationshipId());
            courseKnowledgeGraphNodeVo.setRelationshipCreateTimestamp(courseKnowledgeGraphNodeRelationship.getRelationshipCreateTimestamp());
            postposeKnowledgeGraphNodeList.add(courseKnowledgeGraphNodeVo);
        }
    }

}

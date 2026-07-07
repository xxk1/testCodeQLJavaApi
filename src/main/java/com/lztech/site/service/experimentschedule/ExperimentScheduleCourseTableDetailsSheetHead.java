package com.lztech.site.service.experimentschedule;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import com.lztech.site.constants.Constant;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;


public class ExperimentScheduleCourseTableDetailsSheetHead implements SheetWriteHandler {
    private String title;
    private static final int EIGHT = 9;

    public ExperimentScheduleCourseTableDetailsSheetHead(String title) {
        this.title = title;
    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        Workbook workbook = writeWorkbookHolder.getWorkbook();
        Sheet currentSheet = workbook.getSheetAt(Constant.ZREO);
        Row row1 = currentSheet.createRow(Constant.ZREO);
        row1.setHeight(Short.parseShort("600"));
        Cell cell = row1.createCell(Constant.ZREO);
        //设置标题内容
        cell.setCellValue(title);
        //设置标题样式
        CellStyle headCellStyle = workbook.createCellStyle();
        headCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headCellStyle.setAlignment(HorizontalAlignment.CENTER);
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(Short.parseShort("400"));
        headCellStyle.setFont(font);
        cell.setCellStyle(headCellStyle);

        //设置表头字体样式
        CellStyle tableHeadCellStyle = workbook.createCellStyle();
        tableHeadCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        tableHeadCellStyle.setAlignment(HorizontalAlignment.CENTER);
        tableHeadCellStyle.setBorderBottom(BorderStyle.THIN);
        tableHeadCellStyle.setBorderTop(BorderStyle.THIN);
        tableHeadCellStyle.setBorderLeft(BorderStyle.THIN);
        tableHeadCellStyle.setBorderRight(BorderStyle.THIN);

        Font tableHeadFont = workbook.createFont();
        tableHeadFont.setFontName("宋体");
        tableHeadFont.setBold(true);
        tableHeadFont.setFontHeightInPoints(Short.parseShort("12"));
        tableHeadCellStyle.setFont(tableHeadFont);

        currentSheet.addMergedRegionUnsafe(new CellRangeAddress(Constant.ZREO, Constant.ZREO, Constant.ZREO, EIGHT));

    }
}

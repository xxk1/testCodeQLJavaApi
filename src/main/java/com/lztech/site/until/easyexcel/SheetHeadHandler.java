package com.lztech.site.until.easyexcel;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import com.lztech.site.constants.Constant;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;


public class SheetHeadHandler implements SheetWriteHandler {
    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }

    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        Workbook workbook = writeWorkbookHolder.getWorkbook();
        Sheet sheet = workbook.getSheetAt(0);
        Row row1 = sheet.createRow(0);
        row1.setHeight(Short.parseShort("600"));
        Cell cell = row1.createCell(0);
        //设置单元格内容
        cell.setCellValue("课程素材统计表");
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(Short.parseShort("400"));
        cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);
        sheet.addMergedRegionUnsafe(new CellRangeAddress(Constant.ZREO, Constant.ZREO, Constant.ZREO, Constant.SIX));
    }
}

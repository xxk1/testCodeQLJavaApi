package com.lztech.site.until.easyexcel;

import com.alibaba.excel.write.style.row.AbstractRowHeightStyleStrategy;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.util.Iterator;

public class CustomCellWriteHeightHandler extends AbstractRowHeightStyleStrategy {
    /**
     * 默认高度
     */
    private static final Integer DEFAULT_HEIGHT = 300;

    private static final Integer MAX_COLUMN_LENGTH = 8;

    private static Integer maxHeight = 1;

    @Override
    protected void setHeadColumnHeight(Row row, int relativeRowIndex) {
    }

    @Override
    protected void setContentColumnHeight(Row row, int relativeRowIndex) {
        Iterator<Cell> cellIterator = row.cellIterator();
        if (!cellIterator.hasNext()) {
            return;
        }

        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            if (cell.getCellType() == CellType.STRING) {
                String cellStringValue = cell.getStringCellValue();
                if (cellStringValue.length() > MAX_COLUMN_LENGTH) {
                    maxHeight = (int) Math.ceil(Math.max(maxHeight, cellStringValue.length() / (double) MAX_COLUMN_LENGTH));
                }
            }
        }

        row.setHeight((short) (maxHeight * DEFAULT_HEIGHT));
    }
}
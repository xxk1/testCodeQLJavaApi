package com.lztech.site.until;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListPage {
    public static List<Object> page(List<Map<String, Object>> dataList, int pageSize, int currentPage) {
        List<Object> currentPageList = new ArrayList<>();
        if (dataList != null && dataList.size() > 0) {
            int currIdx = (currentPage > 1 ? (currentPage - 1) * pageSize : 0);
            for (int i = 0; i < pageSize && i < dataList.size() - currIdx; i++) {
                Object data = dataList.get(currIdx + i);
                currentPageList.add(data);
            }
        }
        return currentPageList;
    }

}

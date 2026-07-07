package com.lztech.site.until;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class ListPageHelper {

    /**
     * list分页
     *
     * @param list
     * @param pageNo   页码
     * @param pageSize 每页多少条数据
     * @return
     */
    public static <T> List<T> splitList(List<T> list, int pageNo, int pageSize) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        int totalCount = list.size();
        pageNo = pageNo - 1;
        int fromIndex = pageNo * pageSize;
        // 分页不能大于总数
        if (fromIndex >= totalCount) {
            return new ArrayList<>();
        }
        int toIndex = ((pageNo + 1) * pageSize);
        if (toIndex > totalCount) {
            toIndex = totalCount;
        }
        return list.subList(fromIndex, toIndex);
    }
}

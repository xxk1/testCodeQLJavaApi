package com.lztech.site.constants;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {
    private Object data;
    private ResultStatus status;
    private Object msg;
    private Map<String, Object> resultMap;
    private List<Object> resultList;

    public Result addObj(String key, Object value) {
        if (resultMap == null) {
            resultMap = new HashMap<>();
            this.data = resultMap;
        }
        resultMap.put(key, value);
        return this;
    }

    public Result addObj(Object object) {
        if (resultList == null) {
            resultList = new ArrayList<>();
            this.data = resultList;
        }
        resultList.add(object);
        return this;
    }

    public static Result error(Object msg) {
        Result result = new Result();
        result.setStatus(ResultStatus.ERROR);
        result.setMsg(msg);
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setStatus(ResultStatus.SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result success() {
        Result result = new Result();
        result.setStatus(ResultStatus.SUCCESS);
        return result;
    }

    public ResultStatus getStatus() {
        return status;
    }

    public void setStatus(ResultStatus status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    public Object getMsg() {
        return msg;
    }

    public Result setMsg(Object msg) {
        this.msg = msg;
        return this;
    }
}

package com.lztech.site.component.result;


import java.util.HashMap;
import java.util.Map;

public class ResultData {

    private Status status;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    public ResultData(Status status) {
        this.status = status;
        this.message = status.message;
    }

    public static ResultData create(Status status) {
        return new ResultData(status);
    }

    private ResultData() {

    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public ResultData put(String key, Object value) {
        data.put(key, value);
        return this;
    }

}

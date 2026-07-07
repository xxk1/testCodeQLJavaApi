package com.lztech.site.component.result;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    SUCCESS(2000, "操作成功"),
    SUCCESS_NO_RESULT(2040, "操作成功，但没有需要返回的数据"),
    SUCCESS_MANY_RESULT(3000, "请求成功，但是有多种返回结果"),
    ERROR_PARAM_NULL_OR_FORMAT_ERROR(4000, "参数为空或格式不对"),
    ERROR_NO_ACCESS(4010, "未授权"),
    ERROR_NO_DATA(4040, "未查询到数据"),
    ERROR_VALID(4220, "校验错误"),
    ERROR_SERVER(5000, "服务器异常");
    int code;
    String message;

    Status(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @JsonValue
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

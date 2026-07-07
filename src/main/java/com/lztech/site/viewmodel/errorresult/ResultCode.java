package com.lztech.site.viewmodel.errorresult;

public enum ResultCode implements ErrorCode {
    VALID_CODE_ERROR("B10001", "验证码错误"),
    PARAMETER_INVALID("B10002", "参数不正确"),
    DATA_NOT_EXIST("B10010", "数据不存在"),
    CUSTOM_ERROR("B99999", "自定义错误");

    private String code;
    private String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}

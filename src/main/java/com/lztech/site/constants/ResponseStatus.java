package com.lztech.site.constants;


public enum ResponseStatus {
    FAIL("失败", 0), SUCCESS("成功", 1), VALIDATION_FAIL("验证失败", 2), PARAMETERS_ABNORMAL("参数异常", 3),
    EXCEPTION("接口异常", 4);

    private String message;
    private int index;

    ResponseStatus(String message, int index) {
        this.message = message;
        this.index = index;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

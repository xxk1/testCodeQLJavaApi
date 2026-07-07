package com.lztech.site.viewmodel.errorresult;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ErrorResult
 */

public class ErrorResult {
    @JsonProperty("code")
    private String code = null;

    @JsonProperty("message")
    private String message = null;

    protected ErrorResult() {
    }

    protected ErrorResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorResult customError(String messge) {
        return new ErrorResult(ResultCode.CUSTOM_ERROR.getCode(), messge);
    }

    public static ErrorResult validCodeError() {
        return new ErrorResult(ResultCode.VALID_CODE_ERROR.getCode(), ResultCode.VALID_CODE_ERROR.getMessage());
    }

    public static ErrorResult parameterInvalidError() {
        return new ErrorResult(ResultCode.PARAMETER_INVALID.getCode(), ResultCode.PARAMETER_INVALID.getMessage());
    }

    public static ErrorResult dataNotExistError(String dataName) {
        return new ErrorResult(ResultCode.DATA_NOT_EXIST.getCode(), dataName + ResultCode.DATA_NOT_EXIST.getMessage());
    }

}


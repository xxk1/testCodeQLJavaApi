package com.lztech.site.until;

import com.lztech.site.viewmodel.errorresult.ErrorResult;

public class CustomRuntimeException extends RuntimeException {

    private ErrorResult errorResult;

    public CustomRuntimeException(ErrorResult errorResult) {
        this.errorResult = errorResult;
    }

    public ErrorResult getErrorResult() {
        return errorResult;
    }
}

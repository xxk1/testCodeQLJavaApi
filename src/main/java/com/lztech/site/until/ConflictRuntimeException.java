package com.lztech.site.until;

import com.lztech.site.viewmodel.errorresult.ErrorResult;

public class ConflictRuntimeException extends RuntimeException {

    private ErrorResult errorResult;

    public ConflictRuntimeException(ErrorResult errorResult) {
        this.errorResult = errorResult;
    }

    public ErrorResult getErrorResult() {
        return errorResult;
    }
}

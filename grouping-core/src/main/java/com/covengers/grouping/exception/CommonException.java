package com.covengers.grouping.exception;

import com.covengers.grouping.constant.ResponseCode;

import lombok.Getter;

@Getter
public class CommonException extends RuntimeException {
    private static final long serialVersionUID = -3481703971795114641L;

    private final ResponseCode code;
    private final String errorMessageDetail;

    public CommonException() {
        this(ResponseCode.UNKNOWN_ERROR, "");
    }

    public CommonException(ResponseCode code) {
        this(code, code.getMessage());
    }

    public CommonException(String detailMessage) {
        this(ResponseCode.UNKNOWN_ERROR, detailMessage);
    }

    public CommonException(ResponseCode code, String detailMessage) {
        super(code.getMessage());
        this.code = code;
        errorMessageDetail = detailMessage;
    }

    public CommonException(Throwable e) {
        this(ResponseCode.UNKNOWN_ERROR, "", e);
    }

    public CommonException(ResponseCode code, Throwable e) {
        this(code, code.getMessage(), e);
    }

    public CommonException(String detailMessage, Throwable e) {
        this(ResponseCode.UNKNOWN_ERROR, detailMessage, e);
    }

    public CommonException(ResponseCode code, String detailMessage, Throwable e) {
        super(code.getMessage(), e);
        this.code = code;
        errorMessageDetail = detailMessage;
    }
}

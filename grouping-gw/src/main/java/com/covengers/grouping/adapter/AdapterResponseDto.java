package com.covengers.grouping.adapter;

import com.covengers.grouping.constant.ResponseCode;

public interface AdapterResponseDto {
    boolean isSuccess();

    ResponseCode getErrorResponseCode();

    String getErrorCode();

    String getErrorMessage();
}


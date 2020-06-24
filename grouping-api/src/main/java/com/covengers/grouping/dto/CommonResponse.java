package com.covengers.grouping.dto;

import com.covengers.grouping.constant.ResponseCode;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> {
    private String code;
    private String message;
    private T data;
    private T errorInfo;

    public CommonResponse() {
        setCode(ResponseCode.SUCCESS.getCode());
        setMessage(ResponseCode.SUCCESS.getMessage());
    }

    public CommonResponse(ResponseCode responseCode) {
        setCode(responseCode.getCode());
        setMessage(responseCode.getMessage());
    }

    public CommonResponse(ResponseCode responseCode, T data) {
        setCode(responseCode.getCode());
        setMessage(responseCode.getMessage());
        this.data = data;
    }

    public static <T> CommonResponse<T> of(T data) {
        return new CommonResponse<>(ResponseCode.SUCCESS, data);
    }

    public static <T> CommonResponse<T> of(ResponseCode responseCode, T data) {
        return new CommonResponse<>(responseCode, data);
    }

    public static <T> CommonResponse<Void> fail(ResponseCode responseCode) {
        return new CommonResponse<>(responseCode);
    }
}

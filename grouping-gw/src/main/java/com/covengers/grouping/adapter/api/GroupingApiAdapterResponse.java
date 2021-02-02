package com.covengers.grouping.adapter.api;

import com.covengers.grouping.adapter.AdapterResponse;
import com.covengers.grouping.constant.ResponseCode;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupingApiAdapterResponse<T> implements AdapterResponse {

    private String code;
    private String message;
    private T data;
    private T errorInfo;

    @Override
    public boolean isSuccess() {
        return ResponseCode.SUCCESS.getCode().equals(code);
    }
}

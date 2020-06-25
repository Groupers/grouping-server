package com.covengers.grouping.component;

import org.springframework.stereotype.Component;

import com.covengers.grouping.dto.CommonResponse;
import com.covengers.grouping.constant.ResponseCode;

@Component
public class CommonResponseMaker {
    public <T> CommonResponse<T> makeSucceedCommonResponse(
            T response
    ) {
        final CommonResponse<T> wrappedResponse = new CommonResponse<>();
        wrappedResponse.setData(response);
        return wrappedResponse;
    }

    public CommonResponse<Void> makeEmptyInfoCommonResponse(
            ResponseCode responseCode
    ) {
        final CommonResponse<Void> commonResponse = new CommonResponse<>();
        commonResponse.setCode(responseCode.getCode());
        commonResponse.setMessage(responseCode.getMessage());
        return commonResponse;
    }

    public CommonResponse<Void> makeFailedCommonResponse() {
        return makeEmptyInfoCommonResponse(ResponseCode.UNKNOWN_ERROR);
    }
}

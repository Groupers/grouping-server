package com.covengers.grouping.configuration;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Objects;

import com.covengers.grouping.adapter.AdapterResponseDto;
import com.covengers.grouping.constant.ResponseCode;
import com.covengers.grouping.exception.CommonException;

import feign.Response;
import feign.codec.Decoder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ResponseValidateDecoder implements Decoder {

    private final Decoder delegate;

    @Override
    public Object decode(Response response, Type type) throws IOException {

        final Object responseData = delegate.decode(response, type);

        isValidResponse(responseData);

        isFailResponse(responseData);

        return responseData;
    }

    private void isFailResponse(Object responseData) {
        final AdapterResponseDto responseDto = (AdapterResponseDto) responseData;

        if (!responseDto.isSuccess()) {
            throw new CommonException(ResponseCode.INVALID_API_RESPONSE);
        }
    }

    private void isValidResponse(Object responseData) {
        if (Objects.isNull(responseData) || !(responseData instanceof AdapterResponseDto)) {
            throw new CommonException(ResponseCode.INVALID_API_RESPONSE);
        }
    }
}

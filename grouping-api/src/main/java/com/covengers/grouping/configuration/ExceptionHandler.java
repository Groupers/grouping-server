package com.covengers.grouping.configuration;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.covengers.grouping.dto.CommonResponse;
import com.covengers.grouping.exception.CommonException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(CommonException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse<Void> handleCommonException(CommonException e) {
        return CommonResponse.fail(e.getCode());
    }
}

package com.covengers.grouping.constant;

import com.covengers.grouping.exception.CommonException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    SUCCESS("0000", "Success."),

    // 1xxx is signup error
    EMAIL_ALREADY_EXISTED("1000", "Email is already existed"),
    PHONE_NUMBER_ALREADY_EXISTED("1001", "Phone number is already existed"),

    USER_NOT_EXISTED("1100", "user not existed."),

    INVALID_PHONE_NUMBER("1200", "Invalid phone number"),

    UNKNOWN_ERROR("9999", "Unknown Error");

    private final String code;
    private final String message;

    public static ResponseCode find(String code) {
        for (ResponseCode responseCode : values()) {
            if (responseCode.getCode().equals(code)) {
                return responseCode;
            }
        }
        throw new CommonException(code);
    }
}

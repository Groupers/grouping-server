package com.covengers.grouping.component;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.covengers.grouping.constant.ResponseCode;
import com.covengers.grouping.dto.vo.PhoneNationCodeSeparationVo;
import com.covengers.grouping.exception.CommonException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class PhoneNationCodeClassifier {

    private final List<PhoneNationSeparator> phoneNationSeparatorList;

    public PhoneNationCodeSeparationVo separate(String phoneNumber) {

        if (StringUtils.isEmpty(phoneNumber)) {
            throw new CommonException(ResponseCode.INVALID_PHONE_NUMBER);
        }

        final Optional<PhoneNationSeparator> separatorOptional =
                phoneNationSeparatorList.stream()
                                        .filter(phoneNationSeparator -> phoneNationSeparator
                                                .isValidNation(phoneNumber)).findFirst();

        final PhoneNationSeparator separator = separatorOptional.orElseThrow(
                () -> new CommonException(ResponseCode.INVALID_PHONE_NUMBER));

        return separator.separate(phoneNumber);
    }
}

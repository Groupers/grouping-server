package com.covengers.grouping.component;

import com.covengers.grouping.constant.NationCode;
import com.covengers.grouping.dto.vo.PhoneNationCodeSeparationVo;

public interface PhoneNationSeparator {

    PhoneNationCodeSeparationVo separate(String phoneNumber);

    boolean isValidNation(String phoneNumber);

    NationCode getNationCode();
}

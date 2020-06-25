package com.covengers.grouping.component;

import org.springframework.stereotype.Component;

import com.covengers.grouping.constant.NationCode;
import com.covengers.grouping.vo.PhoneNationCodeSeparationVo;

@Component
public class SouthKoreaPhoneNationSeparator implements PhoneNationSeparator {
    @Override
    public PhoneNationCodeSeparationVo separate(String phoneNumber) {
        String purePhoneNumber = phoneNumber.substring(getNationCode().getPhoneNumberPrefix().length());

        purePhoneNumber = getNationCode().getReplacePrefix() + purePhoneNumber;
        return PhoneNationCodeSeparationVo.builder()
                                          .nationCode(getNationCode())
                                          .purePhoneNumber(purePhoneNumber)
                                          .build();
    }

    @Override
    public boolean isValidNation(String phoneNumber) {
        return phoneNumber.startsWith(getNationCode().getPhoneNumberPrefix());
    }

    @Override
    public NationCode getNationCode() {
        return NationCode.SOUTH_KOREA;
    }
}

package com.covengers.grouping.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.covengers.grouping.component.PhoneNationCodeClassifier;
import com.covengers.grouping.constant.ResponseCode;
import com.covengers.grouping.domain.GroupingUser;
import com.covengers.grouping.exception.CommonException;
import com.covengers.grouping.repository.GroupingUserRepository;
import com.covengers.grouping.vo.PhoneNationCodeSeparationVo;
import com.covengers.grouping.vo.UserPrincipal;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceDecorator implements UserDetailsService {

    private final GroupingUserRepository groupingUserRepository;

    private final PhoneNationCodeClassifier phoneNationCodeClassifier;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String phoneOrEmail) throws UsernameNotFoundException {

        try {
            final PhoneNationCodeSeparationVo phoneNationCodeSeparationVo =
                    phoneNationCodeClassifier.separate(phoneOrEmail);

            final GroupingUser user =
                    groupingUserRepository.findTopByPhoneNumberAndNationCode(
                            phoneNationCodeSeparationVo.getPurePhoneNumber(),
                            phoneNationCodeSeparationVo.getNationCode())
                                          .orElseThrow(
                                                  () -> new CommonException(ResponseCode.USER_NOT_EXISTED));

            return UserPrincipal.createBy(user.toVo());

        } catch (CommonException ignored) {

        }

        final GroupingUser user =
                groupingUserRepository.findTopByEmail(phoneOrEmail)
                                      .orElseThrow(() -> new CommonException(
                                              ResponseCode.USER_NOT_EXISTED));

        return UserPrincipal.createBy(user.toVo());
    }

    @Transactional
    public UserPrincipal loadUserById(Long id) {
        final GroupingUser user =
                groupingUserRepository.findTopById(id)
                                      .orElseThrow(() -> new CommonException(ResponseCode.USER_NOT_EXISTED));

        return UserPrincipal.createBy(user.toVo());
    }
}

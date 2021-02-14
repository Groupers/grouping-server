package com.covengers.grouping.service;

import com.covengers.grouping.component.PhoneNationCodeClassifier;
import com.covengers.grouping.constant.ResponseCode;
import com.covengers.grouping.domain.GroupingUser;
import com.covengers.grouping.exception.CommonException;
import com.covengers.grouping.repository.GroupingUserRepository;
import com.covengers.grouping.vo.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.covengers.grouping.component.JwtTokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final GroupingUserRepository groupingUserRepository;

    private final GroupingUserRepositoryDecorator groupingUserRepositoryDecorator;

    private final PhoneNationCodeClassifier phoneNationCodeClassifier;

    private final JwtTokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public JwtTokenVo completeSignUp(SignUpRequestVo requestVo) {

        final boolean isValidEmail = !groupingUserRepository.findTopByEmail(requestVo.getEmail()).isPresent();

        final PhoneNationCodeSeparationVo phoneNationCodeSeparationVo =
                phoneNationCodeClassifier.separate(requestVo.getPhoneNumber());

        final boolean isValidPhoneNumber = !groupingUserRepository.findTopByPhoneNumberAndNationCode(
                phoneNationCodeSeparationVo.getPurePhoneNumber(),
                phoneNationCodeSeparationVo.getNationCode()).isPresent();

        if (!isValidEmail || !isValidPhoneNumber) {
            throw new CommonException(ResponseCode.SIGN_UP_FAILED_FOR_INVALID_INFO);
        }

        final GroupingUser groupingUser = new GroupingUser(requestVo.getEmail(),
                passwordEncoder.encode(requestVo.getPassword()),
                requestVo.getName(),
                requestVo.getGender(),
                requestVo.getBirthday(),
                phoneNationCodeSeparationVo.getPurePhoneNumber(),
                phoneNationCodeSeparationVo.getNationCode());
        groupingUserRepository.save(groupingUser);

        return generateToken(requestVo.getEmail(), requestVo.getPassword());

    }

    @Transactional(readOnly = true)
    public JwtTokenVo signInWithEmail(SignInWithEmailRequestVo requestVo) {

        final Optional<GroupingUser> groupingUserOptional =
                groupingUserRepository.findTopByEmail(requestVo.getEmail());

        final GroupingUser groupingUser =
                groupingUserOptional.orElseThrow(() -> new CommonException(ResponseCode.USER_NOT_EXISTED));

        if (!passwordEncoder.matches(requestVo.getPassword(), groupingUser.getPassword())) {
            throw new CommonException(ResponseCode.INVALID_PASSWORD);
        }

        return generateToken(requestVo.getEmail(), requestVo.getPassword());
    }

    @Transactional(readOnly = true)
    public JwtTokenVo signInWithPhoneNumber(SignInWithPhoneNumberRequestVo requestVo) {

        final PhoneNationCodeSeparationVo phoneNationCodeSeparationVo =
                phoneNationCodeClassifier.separate(requestVo.getPhoneNumber());

        final Optional<GroupingUser> groupingUserOptional =
                groupingUserRepository.findTopByPhoneNumberAndNationCode(
                        phoneNationCodeSeparationVo.getPurePhoneNumber(),
                        phoneNationCodeSeparationVo.getNationCode());

        final GroupingUser groupingUser =
                groupingUserOptional.orElseThrow(() -> new CommonException(ResponseCode.USER_NOT_EXISTED));

        if (!passwordEncoder.matches(requestVo.getPassword(), groupingUser.getPassword())) {
            throw new CommonException(ResponseCode.INVALID_PASSWORD);
        }

        return generateToken(requestVo.getPhoneNumber(),
                requestVo.getPassword());
    }

    private JwtTokenVo generateToken(String phoneOrEmail, String password) {

        final List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));

        final UserDetails userDetails = groupingUserRepositoryDecorator.loadUserByUsername(phoneOrEmail);

        final Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDetails, password, authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String jwtToken = tokenProvider.generateToken(authentication);

        return JwtTokenVo.builder()
                         .accessToken(jwtToken)
                         .build();
    }

}

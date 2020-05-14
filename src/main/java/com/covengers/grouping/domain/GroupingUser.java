package com.covengers.grouping.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.covengers.grouping.constant.Gender;
import com.covengers.grouping.constant.NationCode;
import com.covengers.grouping.constant.UserStatus;
import com.covengers.grouping.dto.vo.GroupingUserVo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "grouping_user")
public class GroupingUser extends AbstractAuditingEntity {

    private static final long serialVersionUID = -4671344448115493529L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "grouping_user_id")
    private String id;

    @Column(name = "user_status")
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "nation_code")
    private NationCode nationCode;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "user_id")
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "birth_day", columnDefinition = "timestamp")
    private LocalDate birthDay;

    @Column(name = "represent_profile_image")
    private String representProfileImage;

    public GroupingUser(String email) {
        this.email = email;
        userStatus = UserStatus.SIGN_UP_IN_PROGRESS;
    }

    public void updateEmail(String email) {
        this.email = email;
        userStatus = UserStatus.SIGN_UP_IN_PROGRESS;
    }

    public void updatePhoneInfo(String phoneNumber, NationCode nationCode) {
        this.phoneNumber = phoneNumber;
        this.nationCode = nationCode;
        userStatus = UserStatus.SIGN_UP_IN_PROGRESS;
    }

    public void signUpCompleted() {
        userStatus = UserStatus.BEFORE_EXTRA_INFO;
    }

    public void extraInfoCompleted() {
        userStatus = UserStatus.NORMAL;
    }

    public GroupingUserVo toVo() {
        return GroupingUserVo.builder()
                             .id(id)
                             .userStatus(userStatus)
                             .email(email)
                             .nationCode(nationCode)
                             .phoneNumber(phoneNumber)
                             .name(name)
                             .userId(userId)
                             .gender(gender)
                             .birthDay(birthDay)
                             .representProfileImage(representProfileImage)
                             .build();
    }
}

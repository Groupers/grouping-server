package com.covengers.grouping.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.covengers.grouping.constant.Gender;
import com.covengers.grouping.constant.NationCode;
import com.covengers.grouping.constant.UserStatus;
import com.covengers.grouping.vo.GroupVo;
import com.covengers.grouping.vo.GroupingUserVo;
import com.covengers.grouping.vo.KeywordVo;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grouping_user_id")
    private Long id;

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

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "represent_profile_image")
    private String representProfileImage;

    @OneToMany(mappedBy = "groupingUser")
    private List<UserGroupMapping> userGroupMappingList = new ArrayList<>();

    @OneToMany(mappedBy = "groupingUser")
    private List<UserFriendMapping> userFriendMappingList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "grouping_user_id")
    private List<Keyword> searchHistory = new ArrayList<>();

    public GroupingUser(String email,
                        String password,
                        String name,
                        Gender gender,
                        LocalDate birthday,
                        String phoneNumber,
                        NationCode nationCode) {

        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.nationCode = nationCode;
        userStatus = UserStatus.SIGN_UP_COMPLETED;
    }

    public void extraInfoCompleted(String userId, String representProfileImage) {
        this.userId = userId;
        this.representProfileImage = representProfileImage;
        userStatus = UserStatus.NORMAL;
    }

    public List<GroupVo> toGroupList() {
        return userGroupMappingList.stream()
                                   .map(userGroupMapping ->
                                                userGroupMapping.getGroup()
                                                                .toVoForGroupingUser())
                                   .collect(Collectors.toList());
    }

    public List<GroupingUserVo> toFriendList() {
        return userFriendMappingList.stream()
                                    .map(userFriendMapping ->
                                                 userFriendMapping.getFriendUser()
                                                                  .toVo())
                                    .collect(Collectors.toList());
    }

    public List<KeywordVo> toSearchHistoryList() {
        return searchHistory.stream()
                            .map(Keyword::toVo
                            )
                            .collect(Collectors.toList());
    }

    public GroupingUserVo toVo() {
        return GroupingUserVo.builder()
                             .groupingUserId(id)
                             .userStatus(userStatus)
                             .email(email)
                             .nationCode(nationCode)
                             .phoneNumber(phoneNumber)
                             .name(name)
                             .userId(userId)
                             .gender(gender)
                             .birthday(birthday)
                             .representProfileImage(representProfileImage)
                             .password(password)
                             .build();
    }
}

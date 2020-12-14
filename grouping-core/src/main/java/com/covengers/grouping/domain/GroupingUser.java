package com.covengers.grouping.domain;

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
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Column(name = "password", length = 64)
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

    @OneToMany(mappedBy = "groupingUser")
    private List<UserGroupMapping> userGroupMappingList = new ArrayList<>();

    @OneToMany(mappedBy = "groupingUser")
    private List<UserFriendMapping> userFriendMappingList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "grouping_user_id")
    private List<Keyword> searchHistory = new ArrayList<>();

    public GroupingUser(String email,
                        String password,
                        String name,
                        Gender gender,
                        LocalDate birthDay,
                        String phoneNumber,
                        NationCode nationCode) {

        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.nationCode = nationCode;
        userStatus = UserStatus.SIGN_UP_COMPLETED;
    }

    public void extraInfoCompleted(String userId, String representProfileImage) {
        this.userId = userId;
        this.representProfileImage = representProfileImage;
        userStatus = UserStatus.NORMAL;
    }

    public List<GroupVo> toGroupList(){
        return userGroupMappingList.stream()
                .map(userGroupMapping ->
                        userGroupMapping.getGroup()
                                        .toVoForGroupingUser())
                .collect(Collectors.toList());
    }

    public List<GroupingUserVo> toFriendList(){
        return userFriendMappingList.stream()
                .map(userFriendMapping ->
                        userFriendMapping.getFriendUser()
                                .toVo())
                .collect(Collectors.toList());
    }

    public List<KeywordVo> toSearchList() {
        return searchHistory.stream()
                .map(keyword ->
                        keyword.toVo()
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
                             .birthDay(birthDay)
                             .representProfileImage(representProfileImage)
                             .build();
    }
}

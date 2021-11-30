package com.covengers.grouping.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.*;

import com.covengers.grouping.constant.Gender;
import com.covengers.grouping.vo.GroupChatRoomVo;
import com.covengers.grouping.vo.GroupVo;
import com.covengers.grouping.vo.HashtagVo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "crew")
public class Group extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1383129876899942557L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "is_hidden")
    private Boolean isHidden;

    @Column(name = "min_user_age")
    private Integer minUserAge;

    @Column(name = "max_user_age")
    private Integer maxUserAge;

    @Enumerated(EnumType.STRING)
    @Column(name = "available_gender")
    private Gender availableGender;

    @Column(name = "description")
    private String description;

    @Column(name = "point_x")
    private Long pointX;

    @Column(name = "point_y")
    private Long pointY;

    @Column(name = "point_description")
    private String pointDescription;

    @Column(name = "representGroupImage")
    private String representGroupImage;

    @OneToMany(mappedBy = "group")
    private List<GroupHashtagMapping> groupHashtagMappingList = new ArrayList<>();

    @OneToMany(mappedBy = "group")
    private List<UserGroupMapping> userGroupMappingList = new ArrayList<>();

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "group_id")
    private List<GroupChatRoom> groupChatRoomList = new ArrayList<>();

    public Group(String title,
                 Integer maxUserAge,
                 Integer minUserAge,
                 Gender availableGender,
                 String description,
                 Long pointX,
                 Long pointY,
                 String pointDescription,
                 String representGroupImage) {

        this.title = title;
        isHidden = false;
        this.maxUserAge = maxUserAge;
        this.minUserAge = minUserAge;
        this.availableGender = availableGender;
        this.description = description;
        this.pointX = pointX;
        this.pointY = pointY;
        this.pointDescription = pointDescription;
        this.representGroupImage = representGroupImage;
    }

    public GroupVo toVoForGroupingUser() {
        return GroupVo.builder()
                .id(id)
                .title(title)
                .isHidden(isHidden)
                .maxUserAge(maxUserAge)
                .minUserAge(minUserAge)
                .availableGender(availableGender)
                .description(description)
                .pointX(pointX)
                .pointY(pointY)
                .pointDescription(pointDescription)
                .representGroupImage(Optional.ofNullable(representGroupImage))
                .build();
    }

    public GroupVo toVoForHashtag() {
        return GroupVo.builder()
                .id(id)
                .title(title)
                .isHidden(isHidden)
                .maxUserAge(maxUserAge)
                .minUserAge(minUserAge)
                .availableGender(availableGender)
                .description(description)
                .pointX(pointX)
                .pointY(pointY)
                .pointDescription(pointDescription)
                .representGroupImage(Optional.ofNullable(representGroupImage))
                .build();
    }

    public GroupVo toVo() {
        return GroupVo.builder()
                .id(id)
                .title(title)
                .isHidden(isHidden)
                .maxUserAge(maxUserAge)
                .minUserAge(minUserAge)
                .availableGender(availableGender)
                .description(description)
                .pointX(pointX)
                .pointY(pointY)
                .pointDescription(description)
                .representGroupImage(Optional.ofNullable(representGroupImage))
                .build();
    }

    public List<HashtagVo> getHashtagList() {
        return groupHashtagMappingList.stream()
                .map(groupHashtagMapping ->
                        groupHashtagMapping.getHashtag()
                                .toVoForGroup())
                .collect(Collectors.toList());
    }

    public List<GroupChatRoomVo> toGroupChatRoomList() {
        return groupChatRoomList.stream()
                .map(GroupChatRoom::toVo)
                .collect(Collectors.toList());
    }
}

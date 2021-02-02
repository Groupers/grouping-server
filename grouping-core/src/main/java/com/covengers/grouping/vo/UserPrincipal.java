package com.covengers.grouping.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserPrincipal implements UserDetails {

    private static final long serialVersionUID = -4059892120539008290L;

    private final Long groupingUserId;

    private final String name;

    private final String userId;

    private final String password;


    public static UserPrincipal createBy(GroupingUserVo groupingUser){
       return new UserPrincipal(
               groupingUser.getGroupingUserId(),
               groupingUser.getName(),
               groupingUser.getUserId(),
               groupingUser.getPassword());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    public Long getGroupingUserId(){
        return groupingUserId;
    }


    public String getUserId(){
        return userId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

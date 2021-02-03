package com.covengers.grouping.vo;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserPrincipal implements UserDetails {

    private static final long serialVersionUID = -4059892120539008290L;

    private final Long groupingUserId;

    private final String name;

    private final String password;

    public static UserPrincipal createBy(GroupingUserVo groupingUser) {
        return new UserPrincipal(
                groupingUser.getGroupingUserId(),
                groupingUser.getName(),
                groupingUser.getPassword());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public Long getGroupingUserId() {
        return groupingUserId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(groupingUserId, that.groupingUserId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(groupingUserId);
    }
}

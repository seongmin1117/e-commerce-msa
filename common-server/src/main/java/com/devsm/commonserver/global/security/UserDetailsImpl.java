package com.devsm.commonserver.global.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    private final String uuid;
    private final String role;

    public UserDetailsImpl(String uuid, String role) {
        this.uuid = uuid;
        this.role = role;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return uuid;
    }

    @Override
    public boolean isAccountNonExpired() {
        //계정 유효기간
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠금여부
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 자격 증명 유효기간
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 계정 활성화 여부
        return true;
    }
}

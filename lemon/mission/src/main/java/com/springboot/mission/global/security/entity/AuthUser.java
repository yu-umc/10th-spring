package com.springboot.mission.global.security.entity;

import com.springboot.mission.domain.user.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

@Getter
public class AuthUser implements UserDetails {
    private final User user; // 우리 프로젝트의 User 엔티티

    public AuthUser(User user) {
        this.user = user;
    }

    /**
     * 유저의 식별자(PK) 등을 꺼내 쓰기 위한 엔티티 반환 메서드
     */
    public User getUser() {
        return this.user;
    }

    /**
     * 해당 유저의 권한 목록을 리턴합니다.
     * 지금은 기본적으로 일반 유저 권한("ROLE_USER")을 부여하도록 설정했습니다.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    /**
     * 사용자의 암호화된 비밀번호를 반환합니다.
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * 사용자의 로그인 아이디(여기서는 이메일)를 반환합니다.
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    /**
     * 계정 만료 여부 (true: 만료 안됨)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 계정 잠금 여부 (true: 잠금 안됨)
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 비밀번호 만료 여부 (true: 만료 안됨)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 계정 활성화 여부
     * 우리 엔티티의 탈퇴 여부(isDeleted) 필드와 연동하여, 탈퇴 상태가 아닐 때만 true를 반환합니다.
     */
    @Override
    public boolean isEnabled() {
        return !user.getIsDeleted();
    }
}

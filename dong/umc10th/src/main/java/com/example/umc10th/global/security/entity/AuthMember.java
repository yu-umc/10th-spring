package com.example.umc10th.global.security.entity;

import com.example.umc10th.domain.user.entity.User;
import io.micrometer.common.lang.Nullable;
import io.micrometer.observation.annotation.Observed;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class AuthMember implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return  user.getPassword();
    }

    @Override
    public String getUsername(){
        return user.getEmail();
    }
}

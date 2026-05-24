package com.springboot.mission.global.security.service;

import com.springboot.mission.domain.user.entity.User;
import com.springboot.mission.domain.user.repository.UserRepository;
import com.springboot.mission.global.security.entity.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * 로그인 요청 시 들어온 이메일(username)을 통해 DB에서 유저를 찾아
     * 시큐리티 규격인 UserDetails(CustomUserDetails)로 포장하여 리턴합니다.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("해당 이메일을 가진 사용자를 찾을 수 없습니다: " + email));

        return new AuthUser(user);
    }
}

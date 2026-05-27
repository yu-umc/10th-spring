package com.springboot.mission.global.config;

import com.springboot.mission.global.security.service.CustomUserDetailsService;
import com.springboot.mission.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    // 인증 없이 통과할 URL 목록 정리 (Swagger, 웹 자원, 회원가입, 로그인 등)
    private final String[] allowUris = {
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/webjars/**",
            "/user/join",
            "/user/login" // 🌟 시큐리티 가로채기를 방지하기 위해 로그인 주소도 패스 목록에 추가합니다.
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 🌟 로그인 컨트롤러에서 아이디/비밀번호 검증을 시큐리티에게 위임하기 위해
     * AuthenticationManager를 빈으로 등록합니다.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. REST API 규격에 맞게 CSRF 및 사용하지 않는 기본 세팅 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable) // 🌟 기존 폼 로그인 완전히 해제
                .httpBasic(AbstractHttpConfigurer::disable)

                // 2. JWT를 사용하므로 세션을 생성하지도 않고, 사용하지도 않겠다고 설정 (STATELESS)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 3. URL별 접근 권한 설정
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(allowUris).permitAll() // 허용 리스트는 전부 프리패스
                        .anyRequest().authenticated()           // 그 외의 모든 API는 권한(JWT) 필요
                );

        // 💡 [참고] 나중에 토큰을 검증하는 JwtAuthenticationFilter를 만드시면
        // 아래와 같이 UsernamePasswordAuthenticationFilter 앞에 추가해 주시면 됩니다.
        // .addFilterBefore(new JwtAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
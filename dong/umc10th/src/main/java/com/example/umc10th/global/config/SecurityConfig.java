package com.example.umc10th.global.config;

import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.global.security.exception.CustomAccessDenied;
import com.example.umc10th.global.security.filter.JwtAuthFilter;
import com.example.umc10th.global.security.service.CustomEntryPoint;
import com.example.umc10th.global.security.service.CustomUserDetailsService;
import com.example.umc10th.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter(jwtUtil, customUserDetailsService);
    }

    private final String[] allowUris = {
            // Swagger 허용
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/api/v1/auth/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                // URI 허용 여부
                .authorizeHttpRequests(requests -> requests
                        // public API 허용
                        .requestMatchers(allowUris).permitAll()
                        // 그 이외 API는 인증 필요
                        .anyRequest().authenticated()
                )
                // 폼 로그인
                .formLogin(AbstractHttpConfigurer::disable)
                // 세션
                .sessionManagement(AbstractHttpConfigurer::disable)
                // JWT 필터
                .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                //예외 상황 핸들러
                .exceptionHandling( exception -> exception
                        .accessDeniedHandler(customAccessDenied())
                        .authenticationEntryPoint(customEntryPoint())
                )
        ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomAccessDenied customAccessDenied(){
        return new CustomAccessDenied();
    }

    @Bean
    public CustomEntryPoint customEntryPoint() {
        return new CustomEntryPoint();
    }
}



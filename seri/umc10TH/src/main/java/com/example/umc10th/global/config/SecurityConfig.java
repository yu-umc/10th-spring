package com.example.umc10th.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor

public class SecurityConfig {

    private fianl JwtUtil jwtUtil;
    private final CUstomUerDetailsService customUserDetailsService;


}

@Bean
public JwtAuthFilter jwtAuthFilter(){
    return new JwtAuthFilter(jwtUtil, customUserDetailsService);
}

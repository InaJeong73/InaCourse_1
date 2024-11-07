package com.ajoufinder.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf().disable() // CSRF 보호 비활성화
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(
                            "/v3/api-docs/**",         // OpenAPI 문서 경로
                            "/swagger-ui/**",          // Swagger UI 경로
                            "/swagger-ui/index.html",  // Swagger UI 인덱스
                            "/webjars/**"              // Swagger UI에 필요한 웹 자원
                    ).permitAll() // 인증 없이 접근 허용
                    .anyRequest().permitAll() // 모든 요청에 대해 접근 허용
            );
    return http.build();
  }
}

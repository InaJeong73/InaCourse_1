package com.ajoufinder.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity()
public class SecurityConfig {
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
//FilterChainProxy는 여러개의 SecurityFilterChain을 가질 수 있다.
// 개발자는 SecurityFilterChain을 정의하여 여러 보안을 구현한다.
//  http.securityMatchers 메소드는 SecurityFilterChain이 담당할 경로를 설정한다.이 설정을 안해주면 모든 경로가 이 SecurityFilterChain에 들어오게 된다.
//  http.authorizeHttpRequests 메소드는 경로에 대한 접근 권한을 설정한다.

  @Bean
  public SecurityFilterChain configure(HttpSecurity http)throws Exception{
    //csrf disable
    http
            .csrf((auth) -> auth.disable());

    //From 로그인 방식 disable
    http
            .formLogin((auth) -> auth.disable());

    //http basic 인증 방식 disable
    http
            .httpBasic((auth) -> auth.disable());

    //경로별 인가 작업
    http.authorizeHttpRequests(auth->auth
            .requestMatchers("/login","/register","/").permitAll()
            .anyRequest().authenticated());

    //세션 설정
    http
            .sessionManagement((session) -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    return http.build();
  }


//  WebSecurityCustomizer는 내부에 필터가 하나도 없는 SecurityFilterChain이 0번 인덱스로 생성된다.
//  WebSecurityCustomizer를 통해 특정 경로에 대해서 어떤 필터도 통과하지 않도록 설정할 수 있다.
    @Bean
  public WebSecurityCustomizer webSecurityCustomizer(){
    return web->web.ignoring().requestMatchers("/v3/api-docs/**",         // OpenAPI 문서 경로
            "/swagger-ui/**",          // Swagger UI 경로
            "/swagger-ui/index.html",  // Swagger UI 인덱스
            "/webjars/**",
            "/index.html"
    );// Swagger UI에 필요한 웹 자원와 어플리케이션 정적 리소스)
  }
}

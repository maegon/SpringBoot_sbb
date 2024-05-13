package com.sbs.sbb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    모든 접근권한을 허용함(코드 작업할때 피곤해질 일을 방지하기 위함)
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers(new AntPathRequestMatcher("/question/list")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/question/detail/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/user/login")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/user/signup")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/style.css")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
                        .anyRequest().authenticated()
                        // 위에 적혀있는 "/~" 가 아닌 다른 녀석들이 들어오면 로그인이 되어야 들어올 수 있도록 설정
                        // .anyRequest().authenticated()를 안써주면 자체적으로 접근 불가 
                )
                .formLogin((formLogin) -> formLogin
                        // GET
                        // 해당 url까지 오면 알아서 login까지 해결(?)해줌
                        // 아무튼 붙히면 좋다
                        // 시큐리티에게 우리가 만든 로그인 페이지 url을 알려줌
                        // 만약 이걸 하지 않으면 로그인 페이지 url은 "/login" 이다.
                        .loginPage("/user/login")
                        // POST
                        // 시큐리티에게 로그인 폼 처리 url을 알려줌
                        .loginProcessingUrl("/user/login")
                        .defaultSuccessUrl("/"))
        ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

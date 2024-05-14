package com.sbs.sbb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

//    모든 접근권한을 허용함(코드 작업할때 피곤해질 일을 방지하기 위함)
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
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
                        .defaultSuccessUrl("/")
                )
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true))
        ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

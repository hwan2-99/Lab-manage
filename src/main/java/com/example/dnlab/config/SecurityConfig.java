package com.example.dnlab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("PROFESSOR") // 교수님만 접근 가능
                .antMatchers("/manager/**").hasAnyRole("PROFESSOR", "MANAGER") // 교수님과 연구실장만 접근 가능
                .antMatchers("/member/**").hasAnyRole("PROFESSOR", "MANAGER", "RESEARCHER") // 교수님, 연구실장, 연구생 접근 가능
                .antMatchers("/**").permitAll() // 모든 사용자 접근 가능
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/user/login") // 수정: 로그인 처리 URL을 "/user/login"으로 변경
                .loginPage("/login") // 로그인 페이지 지정
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

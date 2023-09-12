package com.test.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.security.config.security.VerifyCodeAuthenticationProvider;
import com.test.security.controller.VerifyCodeController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Configuration
public class AuthConfig extends WebSecurityConfigurerAdapter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Bean
    PasswordEncoder passwordEncoder() {
        // 登录密码不加密
        return new BCryptPasswordEncoder();
    }

    @Bean
    VerifyCodeAuthenticationProvider myAuthenticationProvider() {
        VerifyCodeAuthenticationProvider myAuthenticationProvider = new VerifyCodeAuthenticationProvider();
        myAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        myAuthenticationProvider.setUserDetailsService(userDetailsService());
        return myAuthenticationProvider;
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(Collections.singletonList(myAuthenticationProvider()));
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        PasswordEncoder passwordEncoder = passwordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("java")
                .password(passwordEncoder.encode("1234"))
                .roles("admin")
                .build());
        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(VerifyCodeController.PATH_VC).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler((req, resp, auth) -> {
                    resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
                    PrintWriter out = resp.getWriter();
                    out.write(objectMapper.writeValueAsString(auth.getPrincipal()));
                    out.flush();
                    out.close();
                })
                .failureHandler((req, resp, e) -> {
                    resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
                    PrintWriter out = resp.getWriter();
                    out.write(objectMapper.writeValueAsString(e.getMessage()));
                    out.flush();
                    out.close();
                })
                .permitAll()
                .and()
                .csrf().disable()
                // maximumSessions 表示配置最大会话数为 1，这样后面的登录就会自动踢掉前面的登录
                .sessionManagement().maximumSessions(1);
    }
}

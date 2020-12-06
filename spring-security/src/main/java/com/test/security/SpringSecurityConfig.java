package com.test.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                // 使用内存中 InMemoryUserDetailsManager
                .inMemoryAuthentication()
                // 实际项目中，一般使用自定义的UserDetailsService实现类、能够更自由和灵活的实现用户信息的读取
                // userDetailsService
                // 不使用 PasswordEncoder 密码编码器
                // .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("admin").password("$2a$10$DsqkROfVxs/fgdC1jGQFXuoNxgLxPkti7vqi/7NraXfExpqeMe1ai").roles("admin")
                .and().withUser("user").password("$2a$10$7pWaeDWXqssd5PKEVQE3UuAYHIUP8eNa0FgFXjzhFxpjTnzgW0pxu").roles("user");
    }

    /**
     * 主要配置url的访问权限
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/test/echo").permitAll()
                .antMatchers("/test/admin").hasRole("admin")
                .antMatchers("/test/normal").access(("hasAnyRole('user', 'admin')"))
                // 任何请求、访问的用户都需要经过认证
                .anyRequest().authenticated()
                .and()
                // 登录配置
                .formLogin().permitAll()
                .and()
                // 退出配置
                .logout().permitAll();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

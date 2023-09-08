package com.test.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

@Profile({"custom-login"})
@Configuration
public class CustomLoginPageSecurityConfig extends SecurityConfig {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/custom-login")
                .usernameParameter("name")
                .passwordParameter("pass")
                .defaultSuccessUrl("/hello")
                //.successForwardUrl("/hello")
                .permitAll()
                .and()
                .logout()
                // 默认的注销URL，GET请求
                .logoutUrl("/logout")
                // 或者通过下面的方法设置注销URL和请求方式
                //.logoutRequestMatcher(new AntPathRequestMatcher("/logout","POST"))
                .logoutSuccessUrl("/index")
                .deleteCookies()
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .csrf().disable();
    }
}

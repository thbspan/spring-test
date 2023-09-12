package com.test.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Profile({"custom-login"})
@Configuration
public class CustomLoginPageSecurityConfig extends SecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(auth -> auth.anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login.html")
                        .loginProcessingUrl("/custom-login")
                        .usernameParameter("name")
                        .passwordParameter("pass")
                        .defaultSuccessUrl("/hello")
                        .permitAll()
                )
                .logout(logout -> logout.logoutUrl("/logout") // 默认的注销URL，GET请求
                        // 或者通过下面的方法设置注销URL和请求方式
                        //.logoutRequestMatcher(new AntPathRequestMatcher("/logout","POST"))
                        .logoutSuccessUrl("/index")
                        .deleteCookies()
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .permitAll()
                ).csrf(AbstractHttpConfigurer::disable);
    }
}

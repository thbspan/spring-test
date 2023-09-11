package com.test.security.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration(proxyBeanMethods = false)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private DataSource dataSource;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
        manager.setDataSource(dataSource);
        if (!manager.userExists("boy")) {
            manager.createUser(User.withUsername("boy")
                    .password(passwordEncoder.encode("1234"))
                    .roles("admin").build());
        }
        if (!manager.userExists("python")) {
            manager.createUser(User.withUsername("python")
                    .password(passwordEncoder.encode("2234"))
                    .roles("user").build());
        }
        return manager;
    }

    @Profile("default")
    static class Encode {

        @Bean
        @ConditionalOnMissingBean
        PasswordEncoder passwordEncoder() {
            // 登录密码不加密
            return NoOpPasswordEncoder.getInstance();
        }

    }

    @Profile("mysql")
    static class MysqlEncode {

        @Bean
        PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }
}

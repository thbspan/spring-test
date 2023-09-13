package com.test.security.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import java.util.function.Consumer;

public interface HttpSecurityCustomer extends Consumer<HttpSecurity> {

}

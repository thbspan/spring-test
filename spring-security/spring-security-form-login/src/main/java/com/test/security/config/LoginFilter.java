package com.test.security.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginFilter.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String contentType = request.getContentType();

        if (MediaType.APPLICATION_JSON_VALUE.equals(contentType)
                || MediaType.APPLICATION_JSON_UTF8_VALUE.equals(contentType)) {
            Map<String, String> loginData = new HashMap<>();
            try {
                loginData = objectMapper.readValue(request.getInputStream(), new TypeReference<Map<String, String>>() {
                });
            } catch (IOException e) {
                LOGGER.warn("read json exception", e);
            }

            String username = loginData.getOrDefault(getUsernameParameter(), "").trim();
            String password = loginData.getOrDefault(getPasswordParameter(), "").trim();

            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    username, password);
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        } else {
            return super.attemptAuthentication(request, response);
        }
    }
}

package com.test.prop.converter;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.test.prop.entity.Authorization;

@Component
@ConfigurationPropertiesBinding
public class AuthorizationConverter implements Converter<String, Authorization> {
    @Override
    public Authorization convert(String source) {
        String[] strings = source.split(",");
        return new Authorization(strings[0], strings[1]);
    }
}

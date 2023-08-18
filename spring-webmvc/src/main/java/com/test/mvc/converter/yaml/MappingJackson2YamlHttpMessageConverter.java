package com.test.mvc.converter.yaml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.util.Assert;

import java.nio.charset.StandardCharsets;

@Order
public class MappingJackson2YamlHttpMessageConverter extends AbstractJackson2HttpMessageConverter {

    public MappingJackson2YamlHttpMessageConverter() {
        this(new YAMLMapper(new YAMLFactory()));
    }

    public MappingJackson2YamlHttpMessageConverter(ObjectMapper objectMapper) {
        super(objectMapper, new MediaType("application", "yaml", StandardCharsets.UTF_8),
                new MediaType("application", "*+yaml", StandardCharsets.UTF_8));
        Assert.isInstanceOf(YAMLMapper.class, objectMapper, "XmlMapper required");
    }
}

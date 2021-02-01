package com.test.polling.controller;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

/**
 * https://docs.spring.io/spring/docs/5.1.9.RELEASE/spring-framework-reference/web.html#mvc-ann-async-http-streaming
 */
@Controller
@RequestMapping("/streaming")
public class StreamingResponseBodyController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamingResponseBodyController.class);

    @GetMapping(value = "/download", produces = MediaType.TEXT_PLAIN_VALUE)
    public StreamingResponseBody test() {
        return outputStream -> {
            try {
                outputStream.write(Files.readAllBytes(Paths.get(StreamingResponseBodyController.class.getResource("/application.yml").toURI())));
            } catch (Exception e) {
                LOGGER.error("test streaming exception.", e);
            }
        };
    }
}

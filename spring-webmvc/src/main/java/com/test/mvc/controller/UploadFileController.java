package com.test.mvc.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/upload")
public class UploadFileController {

    @PostMapping
    public Object upload(@RequestPart("file") MultipartFile file, @RequestPart("fileName") String fileName) throws IOException {
        file.transferTo(new File(fileName));
        Map<String, String> result = new HashMap<>();
        result.put("fileName", fileName);
        result.put("fileSize", String.valueOf(file.getSize()));
        return result;
    }
}

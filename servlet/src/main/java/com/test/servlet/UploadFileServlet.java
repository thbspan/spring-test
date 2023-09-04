package com.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

@WebServlet("/upload/")
// 参考 https://blog.csdn.net/T_Y_F_/article/details/131148014
@MultipartConfig(fileSizeThreshold = 4 * 1024 * 1024, maxFileSize = 40 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
public class UploadFileServlet extends HttpServlet {
    private final String tmpDir = System.getProperty("java.io.tmpdir");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 文件选择框组件的名称，有多个相同的名称时或文件选择框支持同时选中多个文件时，仅返回一个
        // Part part = req.getPart("file");
        for (Part part : req.getParts()) {
            // 直接获取文件名
            String submittedFileName = part.getSubmittedFileName();
            System.out.println(part.getName() + " : " + tmpDir);
            if (submittedFileName != null) {
                part.write(new File(tmpDir, submittedFileName).getAbsolutePath());
            }
        }

    }
}

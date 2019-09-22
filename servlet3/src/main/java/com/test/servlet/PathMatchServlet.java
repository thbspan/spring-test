package com.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * servlet url 路径匹配
 */
//@WebServlet("/path*") // URL /path*
//@WebServlet("/path/*") // URL /path/ /path /path/a /path/a/b
//@WebServlet("/path") // URL /path
@WebServlet("/path/") // URL /path/
public class PathMatchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter writer = response.getWriter();
        writer.write("path: " + request.getRequestURI());
        writer.write("<br />");
        writer.flush();
    }
}

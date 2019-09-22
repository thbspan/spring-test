package com.test.servlet.error;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/500", asyncSupported = true)
public class InternalErrorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        PrintWriter writer = resp.getWriter();
        writer.write("500 ERROR");
        writer.write("<br />");
        writer.write("status_code:"  + request.getAttribute("javax.servlet.error.status_code"));
        writer.write("<br />");
        writer.write("exception_type:"  + request.getAttribute("javax.servlet.error.exception_type"));
        writer.write("<br />");
        writer.write("message:"  + request.getAttribute("javax.servlet.error.message"));
        writer.write("<br />");
        writer.write("request_uri:"  + request.getAttribute("javax.servlet.error.request_uri"));
        writer.write("<br />");
        writer.write("exception:"  + request.getAttribute("javax.servlet.error.exception"));
        writer.write("<br />");
        writer.write("servlet_name:"  + request.getAttribute("javax.servlet.error.servlet_name"));
        writer.write("<br />");
        writer.flush();
    }
}

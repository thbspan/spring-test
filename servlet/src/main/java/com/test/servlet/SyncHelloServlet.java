package com.test.servlet;

import com.test.service.LongRunningProcess;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;

@WebServlet("/sync/hello")
public class SyncHelloServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = -8330910318566231969L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new LongRunningProcess().run();
        response.getWriter().write("Hello");
    }
}

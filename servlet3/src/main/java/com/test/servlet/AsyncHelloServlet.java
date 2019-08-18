package com.test.servlet;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.service.LongRunningProcess;

@WebServlet(value = "/async/hello", asyncSupported = true)
public class AsyncHelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AsyncContext asyncContext = request.startAsync();

        asyncContext.start(() -> {
            new LongRunningProcess().run();
            try {
                asyncContext.getResponse().getWriter().write("hello");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                asyncContext.complete();
            }

        });
    }
}

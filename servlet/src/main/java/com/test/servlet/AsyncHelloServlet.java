package com.test.servlet;

import com.test.service.LongRunningProcess;
import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;

@WebServlet(value = "/async/hello", asyncSupported = true)
public class AsyncHelloServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = -4798532990398538222L;

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

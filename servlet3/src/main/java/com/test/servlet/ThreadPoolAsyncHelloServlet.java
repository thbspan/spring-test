package com.test.servlet;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.service.LongRunningProcess;

@WebServlet(value = "/async/pool", asyncSupported = true)
public class ThreadPoolAsyncHelloServlet extends HttpServlet {
    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(100, 200, 50000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100));

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AsyncContext asyncContext = request.startAsync();

        EXECUTOR.execute(() -> {
            new LongRunningProcess().run();
            try {
                asyncContext.getResponse().getWriter().write("Hello");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                asyncContext.complete();
            }
        });
    }
}

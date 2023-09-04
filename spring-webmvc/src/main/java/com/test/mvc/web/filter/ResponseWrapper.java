package com.test.mvc.web.filter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class ResponseWrapper extends HttpServletResponseWrapper {

    private final CharArrayWriter charArrayWriter = new CharArrayWriter();
    private PrintWriter writer;
    private final ResponseOutputStream outputStream = new ResponseOutputStream();

    public ResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return writer = new ResponsePrintWriter(super.getWriter());
    }

    @Override
    public ServletOutputStream getOutputStream() {
        return outputStream;
    }

    @Override
    public void flushBuffer() throws IOException {
        if (writer != null) {
            writer.flush();
        }
        outputStream.flush();
        getResponse().flushBuffer();
    }

    public String getCapturedResponseBody() {
        return charArrayWriter.toString();
    }

    private class ResponseOutputStream extends ServletOutputStream {
        private WriteListener listener;

        @Override
        public void write(int b) throws IOException {
            charArrayWriter.write(b);
            getResponse().getOutputStream().write(b);
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {
            this.listener = writeListener;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        // Implement other methods of ServletOutputStream if needed
    }

    private class ResponsePrintWriter extends PrintWriter {

        public ResponsePrintWriter(Writer out) {
            super(out);
        }

        @Override
        public void write(int c) {
            super.write(c);
            charArrayWriter.write(c);
        }

        @Override
        public void write(char[] buf, int off, int len) {
            super.write(buf, off, len);
            charArrayWriter.write(buf, off, len);
        }

        @Override
        public void write(String s, int off, int len) {
            super.write(s, off, len);
            charArrayWriter.write(s, off, len);
        }
    }
}

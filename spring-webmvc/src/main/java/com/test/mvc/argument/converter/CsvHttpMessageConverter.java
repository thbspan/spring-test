package com.test.mvc.argument.converter;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CsvHttpMessageConverter implements HttpMessageConverter<List<?>> {

    public static final MediaType TEXT_CSV = new MediaType("text", "csv");

    public static final String TEXT_CSV_VALUE = "text/csv";

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return List.class.isAssignableFrom(clazz);
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Arrays.asList(TEXT_CSV, MediaType.TEXT_PLAIN);
    }

    @Override
    public List<?> read(Class<? extends List<?>> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        throw new UnsupportedOperationException("Reading CSV is not supported");
    }

    @Override
    public void write(List<?> objects, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        EasyExcel.write(outputMessage.getBody())
                .excelType(ExcelTypeEnum.CSV)
                .sheet()
                .doWrite(objects);
    }
}

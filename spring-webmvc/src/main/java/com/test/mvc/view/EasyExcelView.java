package com.test.mvc.view;

import com.alibaba.excel.EasyExcel;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.Map;

public class EasyExcelView extends AbstractView {

    private final String templatePath;

    private final Locale locale;

    public EasyExcelView(String templatePath) {
        this(templatePath, Locale.getDefault());
    }

    public EasyExcelView(String templatePath, Locale locale) {
        this.templatePath = templatePath;
        this.locale = locale;
        // excel(.xlsx)
        setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }

    @Override
    protected final void renderMergedOutputModel(
            Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // Set the content type.
        response.setContentType(getContentType());

        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream())
                .withTemplate(Thread.currentThread().getContextClassLoader().getResourceAsStream(templatePath))
                .sheet()
                .doFill(model);
    }
}

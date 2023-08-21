package com.test.mvc.view.resolver;

import com.test.mvc.view.EasyExcelView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

public class EasyExcelViewResolver implements ViewResolver {

    private static final String DEFAULT_TEMPLATE_PREFIX = "templates/";

    private static final String DEFAULT_TEMPLATE = "template.xlsx";

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        String templatePath;
        if (Thread.currentThread().getContextClassLoader().getResource(DEFAULT_TEMPLATE_PREFIX + viewName) != null) {
            templatePath = DEFAULT_TEMPLATE_PREFIX + viewName;
        } else {
            templatePath = DEFAULT_TEMPLATE_PREFIX + DEFAULT_TEMPLATE;
        }

        return new EasyExcelView(templatePath, locale);
    }
}

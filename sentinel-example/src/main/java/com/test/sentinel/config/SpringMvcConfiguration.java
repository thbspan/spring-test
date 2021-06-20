package com.test.sentinel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.SentinelWebInterceptor;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.SentinelWebTotalInterceptor;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.config.SentinelWebMvcConfig;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.config.SentinelWebMvcTotalConfig;

@Configuration
public class SpringMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        addSentinelWebInterceptor(registry);
    }

    /**
     * 针对每个URL进行流量控制
     */
    private void addSentinelWebInterceptor(InterceptorRegistry registry) {
        // 创建 SentinelWebMvcConfig 对象，作为 SentinelWebInterceptor 的配置
        SentinelWebMvcConfig config = new SentinelWebMvcConfig();
        // 是否包含请求方法。即基于 URL 创建的资源，是否包含 Method(GET POST)
        // 参考 SentinelWebInterceptor#getResourceName(HttpServletRequest request) 获取资源名称
        config.setHttpMethodSpecify(true);
        // 设置 BlockException 处理器
        // Sentinel 在流量控制时，当请求到达阀值后，会抛出 BlockException 异常。此时，可以通过定义BlockExceptionHandler去处理
        // 这里不适用默认的，采用SpringMVC提供的全局异常处理机制来处理
        // config.setBlockExceptionHandler(new DefaultBlockExceptionHandler());

        // 添加 SentinelWebInterceptor 拦截器
        registry.addInterceptor(new SentinelWebInterceptor(config)).addPathPatterns("/**");
    }

    /**
     * 针对全局 URL 进行流量控制。简单来说，对所有URL合计流量，全局统一控制
     */
    @SuppressWarnings("unused")
    private void addSentinelWebTotalInterceptor(InterceptorRegistry registry) {
        // 创建 SentinelWebMvcTotalConfig 对象
        SentinelWebMvcTotalConfig config = new SentinelWebMvcTotalConfig();

        // 添加 SentinelWebTotalInterceptor 拦截器
        registry.addInterceptor(new SentinelWebTotalInterceptor(config)).addPathPatterns("/**");
    }
}

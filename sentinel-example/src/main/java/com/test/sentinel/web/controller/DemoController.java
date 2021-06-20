package com.test.sentinel.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/echo")
    public String echo() {
        return "echo";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    /**
     * 模拟延迟比较高的接口
     */
    @GetMapping("/sleep")
    public String sleep() throws InterruptedException {
        Thread.sleep(100L);
        return "sleep";
    }

    /**
     * 热点参数限流
     * sentinel-spring-webmvc-adapter 库提供的 SentinelWebInterceptor 和 SentinelWebTotalInterceptor
     * 拦截器在调用 Sentinel 客户端时，并未传入参数，所以无法进行热点参数限流
     * <p>
     * 解决：使用 @SentinelResource 注解，自定义了 demo_product_info_hot 资源。然后，通过 Spring AOP
     * 拦截该方法的调用，实现 Sentinel 的处理逻辑。在本小节中，就是为了热点参数限流
     */
    @GetMapping("/product_info")
    @SentinelResource("demo_product_info_hot")
    public String productInfo(Integer id) {
        return "商品编号：" + id;
    }

    @GetMapping("/entry_demo")
    public String entryDemo() {
        // 1、访问资源
        try (Entry entry = SphU.entry("entry_demo")) {
            // 2、执行业务逻辑
            return "执行成功";
        } catch (BlockException ex) {
            return "被拒绝";
        }
    }

    /**
     * 使用 {@code @SentinelResource }注解配置资源
     */
    @GetMapping("/annotations_demo")
    @SentinelResource(value = "annotations_demo_resource",
            blockHandler = "blockHandler",
            fallback = "fallback")
    public String annotationsDemo(@RequestParam(required = false) Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("id 参数不允许为空");
        }
        return "success...";
    }

    // BlockHandler 处理函数，参数最后多一个 BlockException，其余与原函数一致.
    public String blockHandler(Integer id, BlockException ex) {
        return "block：" + ex.getClass().getSimpleName();
    }

    // Fallback 处理函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
    public String fallback(Integer id, Throwable throwable) {
        return "fallback：" + throwable.getMessage();
    }
}

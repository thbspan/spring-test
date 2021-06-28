package com.test.netty.common.dispatcher;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MessageHandlerContainer implements InitializingBean, ApplicationContextAware {

    private final Map<String, MessageHandler<Message>> handlers = new HashMap<>();

    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() throws Exception {
        applicationContext.getBeansOfType(MessageHandler.class).values()
                .forEach(messageHandler -> handlers.put(messageHandler.getType(), messageHandler));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public MessageHandler<Message> getMessageHandler(String type) {
        MessageHandler<Message> handler = handlers.get(type);
        if (handler == null) {
            throw new IllegalArgumentException(String.format("类型(%s) 找不到匹配的 MessageHandler 处理器", type));
        }
        return handler;
    }

    @SuppressWarnings("unchecked")
    static Class<? extends Message> getMessageClass(MessageHandler<?> handler) {
        // 获取 bean 对应的 class 类名
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(handler);
        Type[] interfaces = targetClass.getGenericInterfaces();
        Class<?> superclass = targetClass.getSuperclass();
        while (0 == interfaces.length && Objects.nonNull(superclass)) {
            interfaces = superclass.getGenericInterfaces();
            superclass = targetClass.getSuperclass();
        }
        if (0 == interfaces.length) {
            throw new IllegalStateException(String.format("类型(%s) 获得不到消息类型", handler));
        }
        for (Type type : interfaces) {
            // 要求 type 是泛型参数
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                // 要求是 MessageHandler 接口
                if (Objects.equals(parameterizedType.getRawType(), MessageHandler.class)) {
                    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                    // 取首个元素
                    if (Objects.nonNull(actualTypeArguments) && actualTypeArguments.length > 0) {
                        return (Class<? extends Message>) actualTypeArguments[0];
                    } else {
                        throw new IllegalStateException(String.format("类型(%s) 获得不到消息类型", handler));
                    }
                }
            }
        }
        return null;
    }
}

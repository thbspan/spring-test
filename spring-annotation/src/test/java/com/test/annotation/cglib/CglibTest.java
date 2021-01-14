package com.test.annotation.cglib;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.asm.Type;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.cglib.core.ClassGenerator;
import org.springframework.cglib.core.Constants;
import org.springframework.cglib.core.DefaultGeneratorStrategy;
import org.springframework.cglib.core.SpringNamingPolicy;
import org.springframework.cglib.proxy.CallbackFilter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.transform.ClassEmitterTransformer;
import org.springframework.cglib.transform.TransformingClassGenerator;
import org.springframework.util.StringUtils;

import com.test.annotation.SpringAnnotationApplication;

public class CglibTest {

    @Test
    public void testConfigurationClassEnhancer() throws Exception {
        //反射出ConfigurationClassEnhancer，并创建实例
        Class<?> configurationClassEnhancerClass = Class.forName("org.springframework.context.annotation.ConfigurationClassEnhancer");
        Constructor<?> constructor = configurationClassEnhancerClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        Object configurationClassEnhancer = constructor.newInstance();

        //获取属性callbackFilter
        Field callbackFilterField = configurationClassEnhancerClass.getDeclaredField("CALLBACK_FILTER");
        callbackFilterField.setAccessible(true);
        CallbackFilter callbackFilter = (CallbackFilter) callbackFilterField.get(configurationClassEnhancer);

        //反射ConditionalCallbackFilter类
        Class<?> ConditionalCallbackFilter = Class.forName("org.springframework.context.annotation.ConfigurationClassEnhancer$ConditionalCallbackFilter");
        //获取getCallbackTypes方法
        Method getCallbackTypesMethod = ConditionalCallbackFilter.getDeclaredMethod("getCallbackTypes");
        getCallbackTypesMethod.setAccessible(true);

        //反射出EnhancedConfiguration接口
        Class<?> enhancedConfiguration = Class.forName("org.springframework.context.annotation.ConfigurationClassEnhancer$EnhancedConfiguration");


        //创建增强类
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SpringAnnotationApplication.class);//设置目标类
        enhancer.setInterfaces(new Class<?>[]{enhancedConfiguration});//设置要实现的接口
        enhancer.setUseFactory(false);
        enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);
        enhancer.setStrategy(new MyBeanFactoryAwareGeneratorStrategy(this.getClass().getClassLoader()));//生成自定义的策略
        //过滤器
        enhancer.setCallbackFilter(callbackFilter);
        //过滤器类型
        enhancer.setCallbackTypes((Class<?>[]) getCallbackTypesMethod.invoke(callbackFilter));
        //创建代理类
        Class<?> proxy = enhancer.createClass();
        System.out.println(proxy);
    }

    static class MyBeanFactoryAwareGeneratorStrategy extends DefaultGeneratorStrategy {
        private final ClassLoader classLoader;

        public MyBeanFactoryAwareGeneratorStrategy(ClassLoader classLoader) {
            this.classLoader = classLoader;
        }

        @Override
        protected ClassGenerator transform(ClassGenerator cg) throws Exception {
            ClassEmitterTransformer transformer = new ClassEmitterTransformer() {
                @Override
                public void end_class() {
                    declare_field(Constants.ACC_PUBLIC, "CUSTOM_BEAN_FACTORY", Type.getType(BeanFactory.class), null);
                    super.end_class();
                }
            };
            return new TransformingClassGenerator(cg, transformer);
        }

        @Override
        public byte[] generate(ClassGenerator cg) throws Exception {
            if (this.classLoader == null) {
                return super.generate(cg);
            }

            Thread currentThread = Thread.currentThread();
            ClassLoader threadContextClassLoader;
            try {
                threadContextClassLoader = currentThread.getContextClassLoader();
            } catch (Throwable ex) {
                // Cannot access thread context ClassLoader - falling back...
                return super.generate(cg);
            }

            boolean overrideClassLoader = !this.classLoader.equals(threadContextClassLoader);
            if (overrideClassLoader) {
                currentThread.setContextClassLoader(this.classLoader);
            }
            try {
                //生成的字节码保存到本地
                byte[] generate = super.generate(cg);
                try (FileOutputStream fileOutputStream = new FileOutputStream("./target/SpringAnnotationApplication" + StringUtils.replace(UUID.randomUUID().toString(), "-", "") + ".class")) {
                    fileOutputStream.write(generate);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return generate;
            } finally {
                if (overrideClassLoader) {
                    // Reset original thread context ClassLoader.
                    currentThread.setContextClassLoader(threadContextClassLoader);
                }
            }
        }
    }
}

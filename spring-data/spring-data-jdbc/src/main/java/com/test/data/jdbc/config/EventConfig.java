package com.test.data.jdbc.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.event.AbstractRelationalEventListener;
import org.springframework.data.relational.core.mapping.event.AfterSaveEvent;
import org.springframework.data.relational.core.mapping.event.BeforeSaveEvent;

@Configuration
public class EventConfig {

    /**
     * before save事件。
     * <b>
     * 注意：不要使用{@code BeforeSaveEvent<User>} 会导致接收不到事件。如果要使用特定类型的，可以实现{@link AbstractRelationalEventListener}抽象类。
     * <p>
     * 参考：https://docs.spring.io/spring-data/jdbc/docs/current/reference/html/#jdbc.events
     * </p>
     * </b>
     */
    @Bean
    ApplicationListener<BeforeSaveEvent<Object>> loggingBeforeSave() {
        return event -> {
            Object user = event.getEntity();
            System.out.printf("%s is getting saved.\n", user);
        };
    }

    /**
     * after save事件。
     * <b>
     * 注意：不要使用{@code AfterSaveEvent<User>} 会导致接收不到事件。如果要使用特定类型的，可以实现{@link AbstractRelationalEventListener}抽象类。
     * </b>
     * <p>
     * 参考：https://docs.spring.io/spring-data/jdbc/docs/current/reference/html/#jdbc.events
     * </p>
     */
    @Bean
    ApplicationListener<AfterSaveEvent<Object>> loggingSaves() {
        return event -> {
            Object user = event.getEntity();
            System.out.printf("%s is saved.\n", user);
        };
    }
}

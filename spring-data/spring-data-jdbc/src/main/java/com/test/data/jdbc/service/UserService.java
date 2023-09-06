package com.test.data.jdbc.service;

import com.test.data.jdbc.dao.UserRepository;
import com.test.data.jdbc.entity.User;
import com.test.data.jdbc.entity.event.UserSaveOrDeleteEvent;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.data.relational.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserRepository userRepository;

    /**
     * 接受User发出的类型为UserSaveEvent的DomainEvents事件
     * <p>
     * <br/>
     * 参考 https://blog.csdn.net/f4761/article/details/84622317
     *
     * @param event UserSaveOrDeleteEvent 事件
     */
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void event(UserSaveOrDeleteEvent event) {
        LOGGER.info("catch user save or delete event {}", event);
        userRepository.findById(event.getId()).ifPresent(user -> {
            LOGGER.info("user save detail. user: {}", user);
        });
    }

    /**
     * 事件监听方法。可以加上 @{@link org.springframework.scheduling.annotation.Async }注解，用来支持异步
     *
     * @param user user事件实体
     */
    @EventListener
    public void event(User user) {
        LOGGER.info("catch user event. user = {}", user);
    }

    @EventListener
    public void handleAfterSave(AfterSaveEvent<User> event) {
        User user = event.getEntity();
        // 执行自定义的保存后逻辑
        LOGGER.info("catch user save event. {}", user);
    }
}

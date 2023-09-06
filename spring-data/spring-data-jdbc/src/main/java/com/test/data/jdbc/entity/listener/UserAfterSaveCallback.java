package com.test.data.jdbc.entity.listener;

import com.test.data.jdbc.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.relational.core.mapping.event.AfterSaveCallback;
import org.springframework.stereotype.Component;

@Component
public class UserAfterSaveCallback implements AfterSaveCallback<User> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAfterSaveCallback.class);

    @Override
    public User onAfterSave(User user) {
        LOGGER.info("user save callback. {}", user);
        return user;
    }
}

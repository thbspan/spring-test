package com.test.data.jdbc.entity;

import com.test.data.jdbc.entity.event.UserSaveOrDeleteEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
public class User implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(User.class);
    @Serial
    private static final long serialVersionUID = 5232680023674552378L;
    @Id
    @org.springframework.data.annotation.Id
    private long id;
    private String firstName;
    private String lastName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * 该方法会在userRepository.save() delete调用时被触发调用
     * <p>
     * <br />
     * 参考 https://blog.csdn.net/f4761/article/details/84622317
     * <p>
     * https://docs.spring.io/spring-data/jdbc/docs/current/reference/html/#core.domain-events
     */
    @Transient
    @DomainEvents
    protected Collection<UserSaveOrDeleteEvent> domainEvents() {
        return List.of(new UserSaveOrDeleteEvent(this.id));
    }

    @AfterDomainEventPublication
    protected void clearDomainEvent() {
        // 一般用来清空@DomainEvents设置的集合数据
        LOGGER.info("clear domain event example");
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

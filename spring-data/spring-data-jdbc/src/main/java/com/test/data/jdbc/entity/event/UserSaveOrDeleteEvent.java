package com.test.data.jdbc.entity.event;

public class UserSaveOrDeleteEvent {
    private Long id;

    public UserSaveOrDeleteEvent(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserSaveOrDeleteEvent{" +
                "id=" + id +
                '}';
    }
}

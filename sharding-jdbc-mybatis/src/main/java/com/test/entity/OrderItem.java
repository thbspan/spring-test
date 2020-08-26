package com.test.entity;

import java.io.Serializable;

public class OrderItem implements Serializable {

    private static final long serialVersionUID = -3261221655431618329L;
    private long orderItemId;
    private long orderId;
    private int userId;
    private String status;

    public long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("order_item_id:%s, order_id: %s, user_id: %s, status: %s", orderItemId, orderId, userId, status);
    }
}

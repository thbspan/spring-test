package com.test.entity;

import java.io.Serializable;

public class Address implements Serializable {
    private static final long serialVersionUID = -5080908885846785110L;

    private Long addressId;
    private String addressName;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    @Override
    public String toString() {
        return String.format("address addressId: %d, addressName: %s", addressId, addressName);
    }
}

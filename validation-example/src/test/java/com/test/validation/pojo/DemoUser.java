package com.test.validation.pojo;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Range;

public class DemoUser {
    @NotBlank(message = "用户名不能为空")
    @Range(min = 5, message = "用户名长度至少5位")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1([34578])\\d{9}$", message = "手机号格式错误")
    private String mobile;
    @NotBlank(message = "性别不能为空")
    private String gender;
    @Range(min = 5, max = 90, message = "年龄必须在5-90之间")
    private int age;
    @NotEmpty(message = "地址不能为空")
    private List<Address> addressList;

    public static class Address {
        @NotBlank(message = "省份不能为空")
        private String province;
        @NotBlank(message = "地市不能为空")
        private String city;
        @NotBlank(message = "区域不能为空")
        private String region;

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
}

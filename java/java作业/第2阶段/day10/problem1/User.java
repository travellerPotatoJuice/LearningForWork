package com.chenhuiying.hello;

public class User {
    private String name;
    private String sex;
    private String address;
    private String password;

    public User(String name, String sex, String address, String password) {
        this.name = name;
        this.sex = sex;
        this.address = address;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

package com.cheng.networkframestudy.okhttp.sample;

/**
 * 李旺成
 * 2016年2月20日08:32:25
 */
public class User {

    public String username;
    public String password;

    public User() {
        // TODO Auto-generated constructor stub
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

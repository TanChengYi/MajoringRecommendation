package com.example.majoringrecommendation;

import java.io.Serializable;

public class User implements Serializable {
    private String email,username,password,userid;

    User(){}

    User(String email,String username){
        this.email=email;
        this.username=username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

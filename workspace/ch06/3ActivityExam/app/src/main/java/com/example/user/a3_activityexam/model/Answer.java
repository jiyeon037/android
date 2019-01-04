package com.example.user.a3_activityexam.model;

import android.accessibilityservice.GestureDescription;

import java.io.Serializable;

public class Answer implements Serializable { // 직렬화
    private String id;
    private String passwd;
    private String email;

    public Answer(){

    }

    public Answer(String id, String passwd, String email) {
        this.id = id;
        this.passwd = passwd;
        this.email = email;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

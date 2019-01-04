package com.example.user.jsonimagelisttest.model;

import java.io.Serializable;

public class Job implements Serializable {      // intent에 담는 클래스는 반드시 직렬화 시켜줘야 함 (byte배열 형태로 만들어주는 것)
    private int num;
    private String img;
    private String subject;
    private String content;

    public Job() {

    }

    public Job(int num, String img, String subject, String content) {
        this.num = num;
        this.img = img;
        this.subject = subject;
        this.content = content;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

package com.example.user.a1_jsonimagelistitem.model;

public class Column {       // 여러 개의 데이터를 묶어서 관리하는 칼럼 클래스
    private int num;
    private String img;
    private String subject;
    private String content;

    public Column() {
    }

    public Column(int num, String img, String subject, String content) {
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


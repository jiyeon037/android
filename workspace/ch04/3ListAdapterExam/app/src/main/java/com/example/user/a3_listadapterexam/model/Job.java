package com.example.user.a3_listadapterexam.model;

public class Job {
    private String title;        // 직업명
    private String description; // 직업 설명
    private int image;           // 그림 파일의 id
    // 변수 이름만 치고 나머지 자동완성 이용하기

    public Job() {
    }

    public Job(String title, String description, int image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

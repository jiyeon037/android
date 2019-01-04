package com.example.user.a4_listitemcheckexam.model;

public class Flower {
    private String title;
    private String description;
    private int image;
    private boolean check;

    public Flower() {
    }

    public Flower(String title, String description, int image, boolean check) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.check = check;
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

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}

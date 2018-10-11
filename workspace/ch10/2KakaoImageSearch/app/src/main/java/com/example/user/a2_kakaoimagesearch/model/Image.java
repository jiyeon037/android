package com.example.user.a2_kakaoimagesearch.model;

public class Image {
    public static boolean is_end; // static : 전역변수로 막 쓰겠다
    public static int total_count;  // 카카오 JSON  네임과 똑같이 선언
    public static int pageable_count;
    private String collection;
    private String datetime;
    private int height;
    private int width;
    private String thumbnail_url;
    private String image_url;
    private String display_sitename;
    private String doc_url;

    // 이번 예제는 변수가 많기 때문에 생성자 만들면 오히려 불편함. 안 만듬

    public static boolean isIs_end() {
        return is_end;
    }

    public static void setIs_end(boolean is_end) {
        Image.is_end = is_end;
    }

    public static int getTotal_count() {
        return total_count;
    }

    public static void setTotal_count(int total_count) {
        Image.total_count = total_count;
    }

    public static int getPageable_count() {
        return pageable_count;
    }

    public static void setPageable_count(int pageable_count) {
        Image.pageable_count = pageable_count;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDisplay_sitename() {
        return display_sitename;
    }

    public void setDisplay_sitename(String display_sitename) {
        this.display_sitename = display_sitename;
    }

    public String getDoc_url() {
        return doc_url;
    }

    public void setDoc_url(String doc_url) {
        this.doc_url = doc_url;
    }
}

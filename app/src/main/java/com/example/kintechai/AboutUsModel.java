package com.example.kintechai;

public class AboutUsModel {

    private String title1;
    private String title2;
    private String title3;
    private String id1;
    private String id2;
    private String id3;

    public AboutUsModel(String title1, String title2, String title3, String id1, String id2, String id3) {
        this.title1 = title1;
        this.title2 = title2;
        this.title3 = title3;
        this.id1 = id1;
        this.id2 = id2;
        this.id3 = id3;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getTitle3() {
        return title3;
    }

    public void setTitle3(String title3) {
        this.title3 = title3;
    }

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String getId3() {
        return id3;
    }

    public void setId3(String id3) {
        this.id3 = id3;
    }
}

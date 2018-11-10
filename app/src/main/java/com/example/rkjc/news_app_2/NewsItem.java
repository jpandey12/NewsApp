package com.example.rkjc.news_app_2;

public class NewsItem {
    private String url;
    private String author;
    private String title;
    private String desc;
    private String date;

    public NewsItem(String author, String title, String description, String url, String date) {
        this.author = author;
        this.desc = "Description:"+description;
        this.title ="Title:"+title;
        this.url = url;
        this.date = "Date:"+date;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return desc;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String desc) {
        this.desc = desc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

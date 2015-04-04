package com.zjut.zjuthelp.Bean;

public class News {

    private String newsTitle;   //资讯标题
    private String newsURL;     //资讯链接
    private String newsOutline; //资讯概要
    private String imageURL;    //图片地址

    public void setNewsTitle(String title) {
        newsTitle = title;
    }

    public void setNewsURL(String url) {
        newsURL = url;
    }

    public void setNewsOutline(String outline) {
        newsOutline = outline;
    }

    public void setImageURL(String imgUrl) {
        imageURL = imgUrl;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public String getNewsURL() {
        return newsURL;
    }

    public String getNewsOutline() {
        return newsOutline;
    }

    public String getImageURL() {
        return imageURL;
    }
}

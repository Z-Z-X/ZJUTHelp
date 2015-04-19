package com.zjut.zjuthelp.Bean;

public class News {

    private String newsTitle;
    private String newsURL;
    private String newsOutline;
    private String imageURL;

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

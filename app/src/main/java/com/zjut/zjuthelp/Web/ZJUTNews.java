package com.zjut.zjuthelp.Web;

import com.zjut.zjuthelp.Container.News;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

public class ZJUTNews {

    //精弘资讯网页地址
    public static final String URL_NOTIFICATION = "http://news.zjut.com/category/xiaoneitongzhi";
    public static final String URL_ZJUTER = "http://news.zjut.com/category/gongdaren";
    public static final String URL_FOOD_TRAVEL = "http://news.zjut.com/category/chizailushang";

    //当前解析网页地址
    private String URL;

    public ZJUTNews(String url) {
        URL = url;
    }

    public List<News> getNews() {
        ArrayList<News> news = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(URL).get();
            Element mainLeft = doc.select("div.main_left").first();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }

}

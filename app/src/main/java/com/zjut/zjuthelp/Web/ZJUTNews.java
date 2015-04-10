package com.zjut.zjuthelp.Web;

import com.zjut.zjuthelp.Bean.News;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZJUTNews {

    // 精弘资讯网页地址
    public static final String URL_NOTIFICATION = "http://news.zjut.com/category/xiaoneitongzhi";
    public static final String URL_ZJUTER = "http://news.zjut.com/category/gongdaren";
    public static final String URL_FOOD_TRAVEL = "http://news.zjut.com/category/chizailushang";

    // 当前解析网页地址
    private String URL;

    public ZJUTNews(String url) {
        URL = url;
    }

    public List<News> getNewsList() {
        return parser(URL);
    }

    public List<News> getNextNewsList(int page) {
        return parser(URL + "/page/" + String.valueOf(page));
    }

    private List<News> parser(String url) {
        ArrayList<News> newsList = new ArrayList<>();

        /*测试代码
        News testA = new News();
        testA.setNewsTitle("浙江各大高校大片即视感刷爆朋友圈！");
        testA.setNewsOutline("来自小和山其他学校的桃花景致~话说咱们工大有桃花吗……");
        testA.setImageURL("http://news.zjut.com/wp-content/themes/evmito/timthumb.php?src=http://news.zjut.com/wp-content/uploads/2014/04/wKgB4lNKK8iALR7RAASNC8gMxo026_groupinfo_w600.jpg&w=130&h=160&zc=1");
        newsList.add(testA);*/

        try {
            Document doc = Jsoup.connect(url).get();
            Elements lis = doc.select("div.main_left > ul > li");
            for (Element li: lis) {
                News news = new News();
                // Get outline
                Element a1 = li.select("div > a").first();
                String outline = a1.attr("title");
                // Delete space, enter and tab
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(outline);
                news.setNewsOutline(m.replaceAll(""));
                // Get link
                news.setNewsURL(a1.attr("href"));
                // Get image
                Element img = a1.select("img").first();
                news.setImageURL(img.attr("src"));
                // Get title
                Element a2 = li.select("h3 > a").first();
                news.setNewsTitle(a2.attr("title"));
                newsList.add(news);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newsList;
    }

}

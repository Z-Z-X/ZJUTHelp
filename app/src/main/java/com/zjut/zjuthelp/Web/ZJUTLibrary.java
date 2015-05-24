package com.zjut.zjuthelp.Web;

import com.zjut.zjuthelp.Bean.Book;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZJUTLibrary {

    private static String LOGIN_URL = "http://210.32.205.60/OutLogin.aspx";
    private static String BORROWING_URL = "http://210.32.205.60/Borrowing.aspx";
    private static String BORROW_HISTORY_URL = "http://210.32.205.60/BorrowHistory.aspx";

    // Student ID
    private String studentID;
    // Password
    private String password;
    private String msg;
    // Response
    private Connection.Response response;
    // Inputs
    private Map<String, String> map = new HashMap<>();
    // Have next page
    private boolean haveNextPage = false;
    // Document
    private Document doc;

    public ZJUTLibrary(String id, String pw) {
        studentID = id;
        password = pw;
    }

    public List<Book> getBorrowingList() {
        List<Book> list = new ArrayList<>();
        try {
            // Login
            msg = login();
            if (!msg.equals("OK")) {
                return null;
            }
            // Connection
            doc = Jsoup.connect(BORROWING_URL)
                    .cookies(response.cookies())
                    .get();
            // Get first page
            list = parseBorrowing(doc);

            // Get inputs
            map.put("__VIEWSTATE", doc.select("input[name=__VIEWSTATE]").first().attr("value"));
            map.put("__EVENTVALIDATION", doc.select("input[name=__EVENTVALIDATION]").first().attr("value"));

            // Get next page
            Elements nextButton = doc.select("input[alt=>]");
            if (nextButton.size() == 1) {
                // Edit prams
                Map<String, String> prams = new HashMap<>();
                prams.put("ctl00$ScriptManager1", "ctl00$ContentPlaceHolder1$UpdatePanel1|ctl00$ContentPlaceHolder1$GridView1");
                prams.put("__EVENTTARGET", "ctl00$ContentPlaceHolder1$GridView1");
                prams.put("__EVENTARGUMENT", "Page$Next");
                prams.put("ctl00_TreeView1_ExpandState", "eennnnnnennnnnnnennnnnennnenn");
                prams.put("ctl00_TreeView1_SelectedNode", "");
                prams.put("ctl00_TreeView1_PopulateLog", "");
                prams.put("__LASTFOCUS", "");
                prams.put("__VIEWSTATE", map.get("__VIEWSTATE"));
                prams.put("__VIEWSTATEGENERATOR", "997452BE");
                prams.put("__VIEWSTATEENCRYPTED", "");
                prams.put("__EVENTVALIDATION", map.get("__EVENTVALIDATION"));
                prams.put("__ASYNCPOST", "true");
                prams.put("", "");
                byte[] data = WebTools.getRequestData(prams, "utf-8").toString().getBytes();
                // Post request
                URL url = new URL(BORROWING_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.152 Safari/537.36");
                connection.setRequestProperty("Cookie", "ASP.NET_SessionId=" + response.cookie("ASP.NET_SessionId"));
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                connection.setRequestProperty("Content-Length", String.valueOf(data.length));
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(data);
                // Get Response
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    String html = WebTools.dealResponseResult(inputStream);
                    list.addAll(parseBorrowing(Jsoup.parse(html)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Book> getNextBorrowingList() {
        return null;
    }

    private List<Book> parseBorrowing(Document doc) {
        ArrayList<Book> list = new ArrayList<>();
        Element table = doc.select("table[id~=ctl00_ContentPlaceHolder1_GridView.]").first();
        Elements tabs = table.select("table[style~=^border-style]");
        for (Element tab : tabs) {
            Book book = new Book();
            book.setBookName(tab.select("a[id~=ctl00_ContentPlaceHolder1_GridView1_ct..._HyperLink1]").first().text());
            book.setBookBorrowtime(tab.select("span[id~=ctl00_ContentPlaceHolder1_GridView1_ct..._Label8]").first().text());
            book.setBookReturntime(tab.select("span[id~=ctl00_ContentPlaceHolder1_GridView1_ct..._Label9]").first().text());
            list.add(book);
        }
        /* Code for test *
        Book book1 = new Book();
        book1.setBookName("郭沫若经典作品");
        book1.setBookBorrowtime("2015-03-13 21:23:58");
        book1.setBookReturntime("2015-03-13 21:23:59");
        list.add(book1);*/
        return list;
    }

    public List<Book> getBorrowHistoryList() {
        List<Book> list = new ArrayList<>();
        String str = "NO";
        try {
            // Login
            msg = login();
            if (!msg.equals("OK")) {
                return null;
            }
            // Connection
            doc = Jsoup.connect(BORROW_HISTORY_URL)
                    .cookies(response.cookies())
                    .get();
            // Get inputs
            map.put("__VIEWSTATE", doc.select("input[name=__VIEWSTATE]").first().attr("value"));
            map.put("__EVENTVALIDATION", doc.select("input[name=__EVENTVALIDATION]").first().attr("value"));
            // Get next page
            Elements nextButton = doc.select("input[alt=>]");
            haveNextPage = nextButton.size() == 1;
            // Parse
            return parseBorrowHistory(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Book> getNextBorrowingHistoryList() {
        List<Book> list = new ArrayList<>();
        try {
            // Edit parms
            Map<String, String> prams = new HashMap<>();
            prams.put("__EVENTTARGET", "ctl00$ContentPlaceHolder1$GridView1");
            prams.put("__EVENTARGUMENT", "Page$Next");
            prams.put("ctl00_TreeView1_ExpandState", "eennnnnnennnnnnnennnnnennnenn");
            prams.put("ctl00_TreeView1_SelectedNode", "");
            prams.put("ctl00_TreeView1_PopulateLog", "");
            prams.put("__VIEWSTATE", map.get("__VIEWSTATE"));
            prams.put("__VIEWSTATEGENERATOR", "BD03F43E");
            prams.put("__EVENTVALIDATION", map.get("__EVENTVALIDATION"));
            // Connection
            doc = Jsoup.connect(BORROW_HISTORY_URL)
                    .data(prams)
                    .cookies(response.cookies())
                    .post();
            // Get next page
            Elements nextButton = doc.select("input[alt=>]");
            haveNextPage = nextButton.size() == 1;
            // Get inputs
            map.put("__VIEWSTATE", doc.select("input[name=__VIEWSTATE]").first().attr("value"));
            map.put("__EVENTVALIDATION", doc.select("input[name=__EVENTVALIDATION]").first().attr("value"));
            return parseBorrowHistory(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Book> parseBorrowHistory(Document doc) {
        ArrayList<Book> list = new ArrayList<>();
        Element table = doc.select("table[id=ctl00_ContentPlaceHolder1_GridView1]").first();
        Elements trs = table.select("tr[onmouseover]");
        for (Element tr: trs) {
            Elements tds = tr.select("td");
            Book book = new Book();
            book.setBookName(tds.get(0).text());
            book.setBookBorrowtime(tds.get(2).text());
            book.setBookReturntime(tds.get(3).text());
            list.add(book);
        }
        /* Code for test
        Book book1 = new Book();
        book1.setBookName("郭沫若经典作品");
        book1.setBookBorrowtime("2015-03-13 21:23:58");
        book1.setBookReturntime("2015-03-13 21:23:59");
        list.add(book1);*/
        return list;
    }

    // Is have next page
    public boolean isHaveNextPage() {
        return haveNextPage;
    }

    public String getMsg() {
        return msg;
    }

    // Login
    public String login() {
        try {
            // Login
            String login = LOGIN_URL + "?kind=1&userid=" + studentID + "&password=" + password + "&submit=%B5%C7%C2%BD";
            response = Jsoup.connect(login)
                    .execute();
            // Get msg
            Document doc = response.parse();
            Elements elements = doc.select("span[id=Label1]");
            if (elements.size() == 1) {
                return "用户名或密码错误";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "OK";
    }
}
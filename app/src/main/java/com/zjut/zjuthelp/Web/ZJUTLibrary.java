package com.zjut.zjuthelp.Web;


import com.zjut.zjuthelp.Bean.Book;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class ZJUTLibrary {

    private static String LOGIN_URL = "http://210.32.205.60/OutLogin.aspx";
    private static String BORROWING_URL = "http://210.32.205.60/Borrowing.aspx";
    private static String BORROW_HISTORY_URLL = "http://210.32.205.60/BorrowHistory.aspx";

    // Student ID
    private String studentID;
    // Password
    private String password;
    // Response
    private Connection.Response response;

    public ZJUTLibrary(String id, String pw) {
        studentID = id;
        password = pw;
    }

    public List<Book> getBorrowingList() {
        try {
            // Login
            String login = LOGIN_URL + "?kind=1&userid=" + studentID + "&password=" + password + "&submit=%B5%C7%C2%BD";
            response = Jsoup.connect(login)
                    .execute();
            Document doc = Jsoup.connect(BORROWING_URL)
                    .cookies(response.cookies())
                    .get();
           return parseBorrowing(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Book> getNextBorrowingList() {
        return null;
    }

    private List<Book> parseBorrowing(Document doc) {
        ArrayList<Book> list = new ArrayList<>();
        Element table = doc.select("table[id=ctl00_ContentPlaceHolder1_GridView1]").first();
        Elements tabs = table.select("table[style~=^border-style]");
        for (Element tab : tabs) {
            Book book = new Book();
            book.setBookName(tab.select("a[id~=ctl00_ContentPlaceHolder1_GridView1_ct..._HyperLink1]").first().text());
            book.setBookBorrowtime(tab.select("span[id~=ctl00_ContentPlaceHolder1_GridView1_ct..._Label8]").first().text());
            book.setBookReturntime(tab.select("span[id~=ctl00_ContentPlaceHolder1_GridView1_ct..._Label9]").first().text());
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

    public List<Book> getBorrowHistoryList() {
        try {
            // Login
            String login = LOGIN_URL + "?kind=1&userid=" + studentID + "&password=" + password + "&submit=%B5%C7%C2%BD";
            response = Jsoup.connect(login)
                    .execute();
            Document doc = Jsoup.connect(BORROW_HISTORY_URLL)
                    .cookies(response.cookies())
                    .get();
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

}

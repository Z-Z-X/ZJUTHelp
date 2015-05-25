package com.zjut.zjuthelp.Bean;

/*
Date:       2015-4-14
Author:     Xavier
Function:   Book object
*/

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Book {
    
    private String bookName;
    private String bookURL;
    private String bookImage;
    private String bookNumber;
    private String bookBorrowtime;
    private String bookReturntime;
    private boolean isOverdue;
    private String timenow;


    public void setBookName(String name) {
        bookName = name;
    }

    public void setBookURL(String URL) {
        bookURL = URL;
    }

    public void setBookNumber(String number) {
        bookNumber = number;
    }

    public void setBookBorrowtime(String borrowtime) {
        bookBorrowtime = borrowtime;
    }

    public void setBookReturntime(String returntime) {
        bookReturntime = returntime;
    }

    public void setOverdue(boolean overdue) {
        isOverdue = overdue;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookURL() {
        return bookURL;
    }

    public String getBookNumber() {
        return bookNumber;
    }

    public String getBookBorrowtime() {
        return bookBorrowtime;
    }

    public String getBookReturntime() {
        return bookReturntime;
    }

    public boolean getOverdue() {
        return isOverdue;
    }


    public static int daysBetween(String smdate,String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    public int getTotalDay() {
        return 40;
    }

    public int getRestDay() {
        String timenow;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        Date now = new Date();
        timenow = sdf.format( now );
        //timenow=(sdf.format(new Date()));// new Date()为获取当前系统时间;
        int rest=20;
        try
        {
            rest=daysBetween(timenow,bookReturntime);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return rest;
    }


    public int getProgress() {
        return 360*getRestDay()/getTotalDay();
    }
}

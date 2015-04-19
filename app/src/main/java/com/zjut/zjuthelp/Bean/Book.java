package com.zjut.zjuthelp.Bean;

/*
Date:       2015-4-14
Author:     Xavier
Function:   Book object
*/

public class Book {
    
    private String bookName;
    private String bookURL;
    private String bookNumber;
    private String bookBorrowtime;
    private String bookReturntime;
    private boolean isOverdue;

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
}

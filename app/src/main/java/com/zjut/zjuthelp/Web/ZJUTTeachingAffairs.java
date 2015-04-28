package com.zjut.zjuthelp.Web;

import com.zjut.zjuthelp.Bean.Subject;

import java.util.ArrayList;
import java.util.List;

public class ZJUTTeachingAffairs {

    private static String LOGIN_URL = "http://www.ycjw.zjut.edu.cn/logon.aspx";
    private static String GRADE_QUERY_URL = "http://www.ycjw.zjut.edu.cn//stdgl/cxxt/cjcx/Cjcx_Xsgrcj.aspx";
    private static String ROOM_FREE_QUERY_URL = "http://www.ycjw.zjut.edu.cn//pubgl/Web_RoomFree.aspx";

    private String studentID;
    private String password;

    public ZJUTTeachingAffairs(String id, String pw) {
        studentID = id;
        password = pw;
    }

    public List<Subject> getGrades() {
        ArrayList<Subject> list = new ArrayList<>();
        Subject subject = new Subject();
        subject.setSubjectTime("64");
        subject.setSubjectScore("4");
        subject.setSubjectGrade("100");
        subject.setSubjectTerm("2014/2015(1)");
        subject.setSubjectName("程序设计（C++）Ⅰ");
        subject.setSubjectType("普通专业");
        list.add(subject);
        return list;
    }

    public static void roomFreeQuery() {

    }
}

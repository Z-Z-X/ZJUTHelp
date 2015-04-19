package com.zjut.zjuthelp.Web;

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

    public static void RoomFreeQuery() {

    }
}

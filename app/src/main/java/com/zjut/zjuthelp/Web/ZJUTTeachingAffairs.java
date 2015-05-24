package com.zjut.zjuthelp.Web;

import com.zjut.zjuthelp.Bean.Subject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ZJUTTeachingAffairs {

    public static String GRADE_QUERY_URL = "http://api.zjut.com/student/scores.php";
    public static String ROOM_FREE_QUERY_URL = "http://help.izjut.com/plugs/classroom/classroom.php";

    private String studentID;
    private String password;
    private String msg;

    public ZJUTTeachingAffairs(String id, String pw) {
        studentID = id;
        password = pw;
    }

    public List<Subject> getGrades() {
        ArrayList<Subject> list = new ArrayList<>();
        try {
            // Get JSON
            URL url = new URL(GRADE_QUERY_URL + "?username=" + studentID + "&password=" + password + "&term=2014%2F2015(1)");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStreamReader in =  new InputStreamReader(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(in);
            StringBuilder strBuffer = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                strBuffer.append(line);
            }
            String json = strBuffer.toString();
            // Parse JSON
            JSONObject jo = new JSONObject(json);
            // Get msg
            String status = jo.getString("status");
            if (status.equals("error")) {
                msg = jo.getString("msg");
                return null;
            } else {
                msg = "OK";
            }
            // Get subjects
            JSONArray ja = jo.getJSONArray("msg");
            for (int i = 0; i < ja.length(); ++i) {
                JSONObject obj = ja.getJSONObject(i);
                Subject subject = new Subject();
                subject.setSubjectTerm(obj.getString("term"));
                subject.setSubjectName(obj.getString("name"));
                subject.setSubjectType(obj.getString("classprop"));
                subject.setSubjectGrade(obj.getString("classscore"));
                subject.setSubjectTime(obj.getString("classhuor"));
                subject.setSubjectScore(obj.getString("classcredit"));
                list.add(subject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getMsg() {
        return msg;
    }

    public String login() {
        getGrades();
        if (msg.equals("用户名或密码错误")) {
            return msg;
        }
        return "OK";
    }
}

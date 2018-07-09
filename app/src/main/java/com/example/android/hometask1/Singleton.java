package com.example.android.hometask1;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;


public class Singleton {
    public static final Singleton INSTANCE = new Singleton();
    private ArrayList<Student> students;
    private String url = "http://kiparo.ru/t/test.json";
    private ArrayList<String> links = new ArrayList<>(
            Arrays.asList("https://mirpozitiva.ru/uploads/posts/2016-09/1474011210_15.jpg",
                    "http://bipbap.ru/wp-content/uploads/2017/05/VOLKI-krasivye-i-ochen-umnye-zhivotnye.jpg",
                    "http://bipbap.ru/wp-content/uploads/2017/10/0_8eb56_842bba74_XL-640x400.jpg",
                    "http://s1.1zoom.me/big0/930/Coast_Sunrises_and_sunsets_Waves_USA_Ocean_Kaneohe_521540_1280x775.jpg",
                    "https://pp.userapi.com/c9410/g20099/a_a0f275d0.jpg?ava=1",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcScFORdDaLnx9tMS04LOH-o5YDW9996UMKRaLNYzMkvvDf_drbq"));

    private Singleton() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                downloadStudents();
            }
        }).start();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    private void downloadStudents() {
        HttpURLConnection connection = null;
        try {
            URL u = new URL(url);
            connection = (HttpURLConnection) u.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int status = connection.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    br.close();

                    JsonObject object = (JsonObject) new JsonParser().parse(sb.toString());
                    JsonArray array = object.getAsJsonArray("people");
                    students = new ArrayList<>();
                    for (int i = 0; i < array.size(); i++) {
                        JsonObject student = array.get(i).getAsJsonObject();
                        student.remove("id");
                        student.remove("isDegree");
                        student.addProperty("URL", links.get(i));
                        students.add(new Gson().fromJson(student, Student.class));
                    }
            }
        } catch (IOException ex) {
            Log.d("FFF", ex.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.disconnect();
                } catch (Exception ex) {
                    Log.d("FFF", ex.getMessage());
                }
            }
        }
    }
}

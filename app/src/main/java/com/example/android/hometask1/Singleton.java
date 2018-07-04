package com.example.android.hometask1;

import android.util.JsonReader;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Singleton {
    public static final Singleton INSTANCE = new Singleton();
    private ArrayList<Student> students;
    private String url = "http://kiparo.ru/t/test.json";

    private Singleton() {
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudentsData(ArrayList<Student> students){
        this.students = students;
    }

}

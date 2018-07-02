package com.example.android.hometask1;

import android.support.annotation.NonNull;

public class Student implements Comparable<Student> {
    private String name;
    private String surname;
    private int age;
    private String URL;

    public Student(String name, String surname, int age, String url) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        URL = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String url) {
        this.URL = url;
    }

    @Override
    public int compareTo(@NonNull Student student) {
        return surname.equals(student.surname)? name.compareTo(student.name) : surname.compareTo(student.surname);
    }
}

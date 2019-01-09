package com.naqibhatti.mod.madfinal;

import android.app.Application;

import java.util.ArrayList;

public class Student {
    private String studentId;
    private String Name;
    private String age;
    private String password;
    private String regNo;
    private String classID;
    public Student(){}

    public Student(String studentId,String Name, String age, String password,String regNo,String classID) {
        this.Name = Name;
        this.age = age;
        this.password = password;
        this.regNo = regNo;
        this.classID = classID;
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }
}

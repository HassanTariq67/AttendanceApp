package com.naqibhatti.mod.madfinal;

import java.util.ArrayList;

public class Courses {
    private String courseId;
    private String cName;
    private int enrolled;
    private String discipline;

    ArrayList<Student> enrolledStudents = new ArrayList<Student>();

    Teacher teacher;
    public Courses(){

    }

    public Courses(String courseId , String cName, int enrolled, String discipline) {
        this.courseId = courseId;
        this.cName = cName;
        this.enrolled = enrolled;
        this.discipline = discipline;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public int getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(int enrolled) {
        this.enrolled = enrolled;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}

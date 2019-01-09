package com.naqibhatti.mod.madfinal;

public class Teacher {
    private String id;
    private String teacherId;
    private String Name;
    private String password;
    private String qualify;
    private String experience;
    private boolean automaticAttendance;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Teacher(String id, String teacherId , String Name, String qualify, String experience, String password,boolean automaticAttendance) {
        this.teacherId = teacherId;
        this.id = id;
        this.Name = Name;
        this.qualify = qualify;
        this.experience = experience;
        this.password = password;
        this.automaticAttendance = automaticAttendance;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getQualify() {
        return qualify;
    }

    public void setQualify(String qualify) {
        this.qualify = qualify;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public boolean getAutomaticAttendance() {
        return automaticAttendance;
    }

    public void setAutomaticAttendance(boolean automaticAttendance) {
        this.automaticAttendance = automaticAttendance;
    }

    public Teacher(){
    }
}

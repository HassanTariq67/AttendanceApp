package com.naqibhatti.mod.madfinal;

public class AssignedCourses {
    private String id;
    private String teacherId;
    private String courseId;

    public AssignedCourses(){
    }
    public AssignedCourses(String id, String teacherId, String courseId) {
        this.id = id;
        this.teacherId = teacherId;
        this.courseId = courseId;
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

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}

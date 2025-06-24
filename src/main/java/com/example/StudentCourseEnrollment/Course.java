package com.example.StudentCourseEnrollment;

import jakarta.persistence.*;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private int creditHour;
    private String courseId;

    public Course(){
    }

    public Course(Long id, String name, int creditHour, String courseId) {
        this.id = id;
        this.name = name;
        this.creditHour = creditHour;
        this.courseId = courseId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreditHour() {
        return creditHour;
    }

    public void setCreditHour(int creditHour) {
        this.creditHour = creditHour;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}

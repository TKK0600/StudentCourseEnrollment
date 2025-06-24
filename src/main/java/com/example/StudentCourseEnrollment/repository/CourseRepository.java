package com.example.StudentCourseEnrollment.repository;

import com.example.StudentCourseEnrollment.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}

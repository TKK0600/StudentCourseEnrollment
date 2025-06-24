package com.example.StudentCourseEnrollment.repository;

import com.example.StudentCourseEnrollment.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}

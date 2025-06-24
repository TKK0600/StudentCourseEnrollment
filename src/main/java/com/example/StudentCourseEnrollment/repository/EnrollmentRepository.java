package com.example.StudentCourseEnrollment.repository;

import com.example.StudentCourseEnrollment.Model.Enrollment;
import com.example.StudentCourseEnrollment.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    Optional<Enrollment> findByStudentIdAndCourseId(Long studentId, Long courseId);

    @Query("SELECT e.student FROM Enrollment e WHERE e.course.id = :courseId")
    List<Student> findStudentsByCourseId(Long courseId);

    long countByCourseId(Long courseId);
}

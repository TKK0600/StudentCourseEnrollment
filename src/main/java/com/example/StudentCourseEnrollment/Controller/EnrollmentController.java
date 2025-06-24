package com.example.StudentCourseEnrollment.Controller;

import com.example.StudentCourseEnrollment.Model.Student;
import com.example.StudentCourseEnrollment.Model.Course;
import com.example.StudentCourseEnrollment.Model.Enrollment;
import com.example.StudentCourseEnrollment.repository.CourseRepository;
import com.example.StudentCourseEnrollment.repository.EnrollmentRepository;
import com.example.StudentCourseEnrollment.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentRepository enrollmentRepo;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private CourseRepository courseRepo;

    // 1. Assign student to course
    @PostMapping("/assign")
    public String assignStudentToCourse(@RequestParam Long studentId, @RequestParam Long courseId) {
        Optional<Student> studentOpt = studentRepo.findById(studentId);
        Optional<Course> courseOpt = courseRepo.findById(courseId);

        if (studentOpt.isEmpty() || courseOpt.isEmpty()) {
            return "Student or Course not found.";
        }

        // Prevent duplicate
        Optional<Enrollment> existing = enrollmentRepo.findByStudentIdAndCourseId(studentId, courseId);
        if (existing.isPresent()) {
            return "Student already enrolled in this course.";
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(studentOpt.get());
        enrollment.setCourse(courseOpt.get());
        enrollmentRepo.save(enrollment);
        return "Enrollment successful.";
    }

    // 2. List all students in a course
    @GetMapping("/students-in-course/{courseId}")
    public List<Student> getStudentsInCourse(@PathVariable Long courseId) {
        return enrollmentRepo.findStudentsByCourseId(courseId);
    }

    // 3. Reassign student to different course
    @PutMapping("/reassign")
    public String reassignStudent(@RequestParam Long studentId, @RequestParam Long oldCourseId, @RequestParam Long newCourseId) {
        Optional<Enrollment> existing = enrollmentRepo.findByStudentIdAndCourseId(studentId, oldCourseId);
        Optional<Course> newCourseOpt = courseRepo.findById(newCourseId);

        if (existing.isEmpty()) return "Enrollment not found.";
        if (newCourseOpt.isEmpty()) return "New course not found.";

        Enrollment enrollment = existing.get();
        enrollment.setCourse(newCourseOpt.get());
        enrollmentRepo.save(enrollment);

        return "Student reassigned successfully.";
    }

    // 4. Delete (withdraw) student from course
    @DeleteMapping("/withdraw")
    public String withdrawStudent(@RequestParam Long studentId, @RequestParam Long courseId) {
        Optional<Enrollment> enrollment = enrollmentRepo.findByStudentIdAndCourseId(studentId, courseId);
        if (enrollment.isEmpty()) return "Enrollment not found.";

        enrollmentRepo.delete(enrollment.get());
        return "Student withdrawn successfully.";
    }

    // 5. Reporting: List students in same course (same as #2)
    // Already covered by /students-in-course/{courseId}

    // 6. Reporting: Percentage of total students per course
    @GetMapping("/percentage-per-course")
    public Map<String, Double> percentagePerCourse() {
        long totalStudents = studentRepo.count();
        Map<String, Double> result = new HashMap<>();

        List<Course> courses = courseRepo.findAll();
        for (Course course : courses) {
            long count = enrollmentRepo.countByCourseId(course.getId());
            double percentage = totalStudents == 0 ? 0 : ((double) count / totalStudents) * 100;
            result.put(course.getName(), percentage);
        }

        return result;
    }
}

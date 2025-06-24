package com.example.StudentCourseEnrollment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentCourseEnrollmentApplication {

	public static void main(String[] args) {
		DatabaseInitializer.createDatabaseIfNotExists();
		SpringApplication.run(StudentCourseEnrollmentApplication.class, args);
	}

}

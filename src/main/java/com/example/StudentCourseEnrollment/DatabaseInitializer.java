package com.example.StudentCourseEnrollment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void createDatabaseIfNotExists() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/";
        String username = "root";
        String password = "1700819819"; // replace this

        try {
            // Connect to MySQL without specifying a database
            Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
            Statement stmt = conn.createStatement();

            // Create the database if it doesn't exist
            String sql = "CREATE DATABASE IF NOT EXISTS student_course_enrollment";
            stmt.executeUpdate(sql);

            System.out.println("✅ Database checked/created successfully.");
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("❌ Failed to create database: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

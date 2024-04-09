package com.users.userservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserServiceApplicationTests {

    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String DB_USER = "SYSTEM";
    private static final String DB_PASSWORD = "SYSTEM";

    public static void main(String[] args) {
        createStudent(1, "John", "Doe", 20, 3.75, "Computer Science");
        readStudents();
        updateStudent(1, "Jane", "Smith", 21, 3.80, "Engineering");
        deleteStudent(1);
    }

    // Create operation
    public static void createStudent(int studentId, String firstName, String lastName,
                                     int age, double gpa, String major) {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(
                     "INSERT INTO Students (StudentID, FirstName, LastName, Age, GPA, Major) " +
                             "VALUES (?, ?, ?, ?, ?, ?)")) {

            pstmt.setInt(1, studentId);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setInt(4, age);
            pstmt.setDouble(5, gpa);
            pstmt.setString(6, major);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student created successfully.");
            } else {
                System.out.println("Failed to create student.");
            }
        } catch (SQLException e) {
            System.out.println("Error creating student: " + e.getMessage());
        }
    }

    // Read operation
    public static void readStudents() {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Students");
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int studentId = rs.getInt("StudentID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                int age = rs.getInt("Age");
                double gpa = rs.getDouble("GPA");
                String major = rs.getString("Major");

                System.out.println(studentId + " " + firstName + " " + lastName + " " +
                        age + " " + gpa + " " + major);
            }
        } catch (SQLException e) {
            System.out.println("Error reading students: " + e.getMessage());
        }
    }

    // Update operation
    public static void updateStudent(int studentId, String firstName, String lastName,
                                     int age, double gpa, String major) {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(
                     "UPDATE Students SET FirstName = ?, LastName = ?, Age = ?, GPA = ?, Major = ? " +
                             "WHERE StudentID = ?")) {

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setInt(3, age);
            pstmt.setDouble(4, gpa);
            pstmt.setString(5, major);
            pstmt.setInt(6, studentId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("Failed to update student.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }

    // Delete operation
    public static void deleteStudent(int studentId) {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement("DELETE FROM Students WHERE StudentID = ?")) {

            pstmt.setInt(1, studentId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Failed to delete student.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }
}

package Controller.db_Connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.Doctor;

public class DoctorDAO {

    // Add a doctor
    public void addDoctor(Doctor doctor) throws SQLException {

        String sql = "INSERT INTO doctors (name, specialty, CRM, phone, email) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = db_Connection.getConnection();

            stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, doctor.getName());
            stmt.setString(2, doctor.getSpecialty());
            stmt.setString(3, doctor.getCrm());
            stmt.setString(4, doctor.getPhone());
            stmt.setString(5, doctor.getEmail());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                doctor.setId(generatedKeys.getInt(1));
            }

            System.out.println("Doctor added successfully!");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error adding doctor: " + e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    // Update a doctor
    public void updateDoctor(Doctor doctor) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = db_Connection.getConnection();

            String sql = "UPDATE doctors SET name = ?, specialty = ?, CRM = ?, phone = ?, email = ? WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, doctor.getName());
            stmt.setString(2, doctor.getSpecialty());
            stmt.setString(3, doctor.getCrm());
            stmt.setString(4, doctor.getPhone());
            stmt.setString(5, doctor.getEmail());
            stmt.setInt(6, doctor.getId());

            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error updating doctor: " + e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    // List doctors data
    public List<Doctor> listDoctorDataByName(String name) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Doctor> doctors = new ArrayList<>();

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = db_Connection.getConnection();

            String sql = "SELECT * FROM doctors WHERE name LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + name + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {
                Doctor doctor = new Doctor(
                        rs.getString("name"),
                        rs.getString("specialty"),
                        rs.getString("CRM"),
                        rs.getString("phone"),
                        rs.getString("email"));
                doctor.setId(rs.getInt("id"));
                doctors.add(doctor);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error listing doctors: " + e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return doctors;
    }

    // Find a doctor by name
    public Doctor findDoctorByName(String name) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Doctor doctor = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = db_Connection.getConnection();

            String sql = "SELECT * FROM doctors WHERE name = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);

            rs = stmt.executeQuery();

            if (rs.next()) {
                doctor = new Doctor(
                    rs.getString("name"),
                        rs.getString("specialty"),
                        rs.getString("CRM"),
                        rs.getString("phone"),
                        rs.getString("email")
                );
                doctor.setId(rs.getInt("id"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error finding doctor: " + e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return doctor;
    }

    // List all doctors
    public List<Doctor> listAllDoctors() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Doctor> doctors = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = db_Connection.getConnection();

            String sql = "SELECT * FROM doctors";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Doctor doctor = new Doctor(
                    rs.getString("name"),
                    rs.getString("specialty"),
                    rs.getString("crm"),
                    rs.getString("email"),
                    rs.getString("phone")
                );
                doctor.setId(rs.getInt("id"));
                doctors.add(doctor);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error listing doctors: " + e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return doctors;
    }

    // Method to delete a doctor
    public void deleteDoctor(int doctorId) throws SQLException {
        String deleteDoctorSql = "DELETE FROM hospital_system.doctors WHERE id = ?";

        Connection conn = null;
        PreparedStatement deleteDoctorStmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = db_Connection.getConnection();

            deleteDoctorStmt = conn.prepareStatement(deleteDoctorSql);
            deleteDoctorStmt.setInt(1, doctorId);
            deleteDoctorStmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error deleting doctor: " + e.getMessage());
        } finally {
            if (deleteDoctorStmt != null) deleteDoctorStmt.close();
            if (conn != null) conn.close();
        }
    }
}

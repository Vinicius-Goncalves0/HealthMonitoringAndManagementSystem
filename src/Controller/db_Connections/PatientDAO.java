package Controller.db_Connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Patient;

public class PatientDAO {

    // add a patient to the database
    public void addPatient(Patient patient) throws SQLException {

        String sql = "INSERT INTO patients (name, cpf, birth_date, address, phone, email) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            conn = db_Connection.getConnection();

            stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, patient.getName());
            stmt.setString(2, patient.getCPF());
            stmt.setString(3, patient.getBirthDate());
            stmt.setString(4, patient.getAddress());
            stmt.setString(5, patient.getPhone());
            stmt.setString(6, patient.getEmail());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                patient.setId(generatedKeys.getInt(1));
            }

            System.out.println("Patient added successfully!");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error adding patient: " + e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    // Method to update a patient
    public void updatePatient(Patient patient) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            conn = db_Connection.getConnection();

            // Prepare the SQL query
            String sql = "UPDATE patients SET name = ?, cpf = ?, birth_date = ?, address = ?, phone = ?, email = ? WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, patient.getName());
            stmt.setString(2, patient.getCPF());
            stmt.setString(3, patient.getBirthDate());
            stmt.setString(4, patient.getAddress());
            stmt.setString(5, patient.getPhone());
            stmt.setString(6, patient.getEmail());
            stmt.setInt(7, patient.getId());

            // Execute the query
            stmt.executeUpdate();

            System.out.println("Patient updated successfully!");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error updating patient: " + e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    // Method to find a patient by name
    public Patient findPatientByName(String name) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Patient patient = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            conn = db_Connection.getConnection();

            // Prepare the SQL query
            String sql = "SELECT * FROM patients WHERE name = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);

            // Execute the query
            rs = stmt.executeQuery();

            // Checks if it has found a patient
            if (rs.next()) {
                patient = new Patient(
                    rs.getString("name"),
                    rs.getString("cpf"),
                    rs.getString("birth_date"),
                    rs.getString("address"),
                    rs.getString("phone"),
                    rs.getString("email")
                );
                patient.setId(rs.getInt("id"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error finding patient: " + e.getMessage());
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

        return patient;
    }
}

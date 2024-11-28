package Controller.db_Connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Model.Patient;

public class PatientDAO {

    // add a patient to the database
    public void addPatient(Patient patient) throws SQLException {

        String sql = "INSERT INTO patients (name, cpf, birth_date, address, phone, email, histories) VALUES (?, ?, ?, ?, ?, ?, ?)";
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
            stmt.setString(7, patient.getHistories());
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
            String sql = "UPDATE patients SET name = ?, cpf = ?, birth_date = ?, address = ?, phone = ?, email = ?, histories = ? WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, patient.getName());
            stmt.setString(2, patient.getCPF());
            stmt.setString(3, patient.getBirthDate());
            stmt.setString(4, patient.getAddress());
            stmt.setString(5, patient.getPhone());
            stmt.setString(6, patient.getEmail());
            stmt.setString(7, patient.getHistories());
            stmt.setInt(8, patient.getId());

            // Execute the query
            stmt.executeUpdate();

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

    // Method to list patient data by name
    public List<Patient> listPatientsByName(String name) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Patient> patients = new ArrayList<>();

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            conn = db_Connection.getConnection();

            // Prepare the SQL query
            String sql = "SELECT * FROM patients WHERE name LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + name + "%");

            // Execute the query
            rs = stmt.executeQuery();

            // Iterate through the result set and create Patient objects
            while (rs.next()) {
                Patient patient = new Patient(
                        rs.getString("name"),
                        rs.getString("cpf"),
                        rs.getString("birth_date"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("histories"));
                patient.setId(rs.getInt("id"));
                patients.add(patient);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error listing patients: " + e.getMessage());
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

        return patients;
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
                        rs.getString("email"),
                        rs.getString("histories"));
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

    // Method to list all patients
    public List<Patient> listAllPatients() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Patient> patients = new ArrayList<>();

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            conn = db_Connection.getConnection();

            // Prepare the SQL query
            String sql = "SELECT * FROM patients";
            stmt = conn.prepareStatement(sql);

            // Execute the query
            rs = stmt.executeQuery();

            // Iterate through the result set and create Patient objects
            while (rs.next()) {
                Patient patient = new Patient(
                        rs.getString("name"),
                        rs.getString("cpf"),
                        rs.getString("birth_date"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("histories"));
                patient.setId(rs.getInt("id"));
                patients.add(patient);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error listing patients: " + e.getMessage());
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

        return patients;
    }

    // Method to delete a patient
    public void deletePatient(int patientId) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Carrega o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Estabelece a conexão
            conn = db_Connection.getConnection();
            conn.setAutoCommit(false);

            // Obter os IDs das consultas associadas ao paciente
            String getAppointmentIdsSql = "SELECT appointment_id FROM hospital_system.patient_appointments WHERE patient_id = ?";
            stmt = conn.prepareStatement(getAppointmentIdsSql);
            stmt.setInt(1, patientId);
            rs = stmt.executeQuery();

            List<Integer> appointmentIds = new ArrayList<>();
            while (rs.next()) {
                appointmentIds.add(rs.getInt("appointment_id"));
            }
            rs.close();
            stmt.close();

            // Obter os IDs dos medicamentos associados ao paciente
            String getPatientMedicationIdsSql = "SELECT medication_id FROM hospital_system.patient_medications WHERE patient_id = ?";
            stmt = conn.prepareStatement(getPatientMedicationIdsSql);
            stmt.setInt(1, patientId);
            rs = stmt.executeQuery();

            Set<Integer> medicationIds = new HashSet<>();
            while (rs.next()) {
                medicationIds.add(rs.getInt("medication_id"));
            }
            rs.close();
            stmt.close();

            // Obter os IDs dos medicamentos associados às consultas do paciente
            if (!appointmentIds.isEmpty()) {
                StringBuilder appointmentIdPlaceholders = new StringBuilder();
                for (int i = 0; i < appointmentIds.size(); i++) {
                    appointmentIdPlaceholders.append("?");
                    if (i < appointmentIds.size() - 1) {
                        appointmentIdPlaceholders.append(",");
                    }
                }

                String getAppointmentMedicationIdsSql = "SELECT medication_id FROM hospital_system.appointment_medications WHERE appointment_id IN ("
                        + appointmentIdPlaceholders.toString() + ")";
                stmt = conn.prepareStatement(getAppointmentMedicationIdsSql);
                for (int i = 0; i < appointmentIds.size(); i++) {
                    stmt.setInt(i + 1, appointmentIds.get(i));
                }
                rs = stmt.executeQuery();

                while (rs.next()) {
                    medicationIds.add(rs.getInt("medication_id"));
                }
                rs.close();
                stmt.close();
            }

            // Deletar de appointment_medications
            if (!appointmentIds.isEmpty()) {
                StringBuilder appointmentIdPlaceholders = new StringBuilder();
                for (int i = 0; i < appointmentIds.size(); i++) {
                    appointmentIdPlaceholders.append("?");
                    if (i < appointmentIds.size() - 1) {
                        appointmentIdPlaceholders.append(",");
                    }
                }

                String deleteAppointmentMedicationsSql = "DELETE FROM hospital_system.appointment_medications WHERE appointment_id IN ("
                        + appointmentIdPlaceholders.toString() + ")";
                stmt = conn.prepareStatement(deleteAppointmentMedicationsSql);
                for (int i = 0; i < appointmentIds.size(); i++) {
                    stmt.setInt(i + 1, appointmentIds.get(i));
                }
                stmt.executeUpdate();
                stmt.close();
            }

            // Deletar de patient_medications
            String deletePatientMedicationsSql = "DELETE FROM hospital_system.patient_medications WHERE patient_id = ?";
            stmt = conn.prepareStatement(deletePatientMedicationsSql);
            stmt.setInt(1, patientId);
            stmt.executeUpdate();
            stmt.close();

            // Deletar de patient_appointments
            String deletePatientAppointmentsSql = "DELETE FROM hospital_system.patient_appointments WHERE patient_id = ?";
            stmt = conn.prepareStatement(deletePatientAppointmentsSql);
            stmt.setInt(1, patientId);
            stmt.executeUpdate();
            stmt.close();

            // Deletar medicamentos
            if (!medicationIds.isEmpty()) {
                StringBuilder medicationIdPlaceholders = new StringBuilder();
                for (int i = 0; i < medicationIds.size(); i++) {
                    medicationIdPlaceholders.append("?");
                    if (i < medicationIds.size() - 1) {
                        medicationIdPlaceholders.append(",");
                    }
                }

                String deleteMedicationsSql = "DELETE FROM hospital_system.medications WHERE id IN ("
                        + medicationIdPlaceholders.toString() + ")";
                stmt = conn.prepareStatement(deleteMedicationsSql);
                int index = 1;
                for (Integer medId : medicationIds) {
                    stmt.setInt(index++, medId);
                }
                stmt.executeUpdate();
                stmt.close();
            }

            // Deletar consultas
            if (!appointmentIds.isEmpty()) {
                StringBuilder appointmentIdPlaceholders = new StringBuilder();
                for (int i = 0; i < appointmentIds.size(); i++) {
                    appointmentIdPlaceholders.append("?");
                    if (i < appointmentIds.size() - 1) {
                        appointmentIdPlaceholders.append(",");
                    }
                }

                String deleteAppointmentsSql = "DELETE FROM hospital_system.appointments WHERE id IN ("
                        + appointmentIdPlaceholders.toString() + ")";
                stmt = conn.prepareStatement(deleteAppointmentsSql);
                for (int i = 0; i < appointmentIds.size(); i++) {
                    stmt.setInt(i + 1, appointmentIds.get(i));
                }
                stmt.executeUpdate();
                stmt.close();
            }

            // Deletar paciente
            String deletePatientSql = "DELETE FROM hospital_system.patients WHERE id = ?";
            stmt = conn.prepareStatement(deletePatientSql);
            stmt.setInt(1, patientId);
            stmt.executeUpdate();
            stmt.close();

            conn.commit();

        } catch (SQLException | ClassNotFoundException e) {
            if (conn != null) {
                conn.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Erro ao deletar o paciente: " + e.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        }
    }

    // Method to find a patient's name by ID
    public String findPatientNameByID(int patientId) throws SQLException {
        String patientName = null;
        String sql = "SELECT name FROM patients WHERE id = ?";

        try (Connection conn = db_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    patientName = rs.getString("name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error finding patient name: " + e.getMessage());
        }

        return patientName;
    }

    // Method to find a patient by ID
    public Patient findPatientById(int id) throws SQLException {
        String sql = "SELECT * FROM patients WHERE id = ?";
        Patient patient = null;

        try (Connection conn = db_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    patient = new Patient(
                            rs.getString("name"),
                            rs.getString("cpf"),
                            rs.getString("birth_date"),
                            rs.getString("address"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getString("histories")
                    );
                    patient.setId(rs.getInt("id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao encontrar paciente pelo ID: " + e.getMessage());
        }

        return patient;
    }
}

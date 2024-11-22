package Controller.db_Connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Appointment;
import Model.Patient;

// Class to connect to the database
public class AppointmentDAO {

    // add appointment to a patient in the database
    public void addAppointmentToPatient(Appointment appointment, Patient patient) {
        String appointmentSql = "INSERT INTO appointments (appointment_date_time, doctor, diagnosis) VALUES (?, ?, ?)";
        String patientToAppointmentSql = "INSERT INTO patient_appointments (patient_id, appointment_id) VALUES (?, ?)";

        try (Connection conn = db_Connection.getConnection()) {
            // Disables auto commit for manual transaction control
            conn.setAutoCommit(false);

            // Salva o medicamento
            try (PreparedStatement appointmentStmt = conn.prepareStatement(appointmentSql,
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                appointmentStmt.setString(1, appointment.getAppointmentDateTime());
                appointmentStmt.setString(2, appointment.getDoctor());
                appointmentStmt.setString(3, appointment.getDiagnosis());

                appointmentStmt.executeUpdate();

                // Pega o ID gerado automaticamente para o medicamento
                ResultSet generatedKeys = appointmentStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    appointment.setId(generatedKeys.getInt(1));
                }
            }

            // Cria a relação entre paciente e medicamento
            try (PreparedStatement relationStmt = conn.prepareStatement(patientToAppointmentSql)) {
                relationStmt.setInt(1, patient.getId());
                relationStmt.setInt(2, appointment.getId());

                relationStmt.executeUpdate();
            }

            // Confirma a transação
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // List all medications from a patient
    public List<Appointment> listAppointmentByPatientName(String patientName) throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT a.id, a.appointment_date_time, a.doctor, a.diagnosis "
                +
                "FROM hospital_system.patients p " +
                "JOIN hospital_system.patient_appointments pa ON p.id = pa.patient_id " +
                "JOIN hospital_system.appointments a ON pa.appointment_id = a.id " +
                "WHERE p.name = ?";

        try (Connection conn = db_Connection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patientName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Appointment appointment = new Appointment(
                            rs.getInt("id"),
                            rs.getString("appointment_date_time"),
                            rs.getString("doctor"),
                            rs.getString("diagnosis"));
                    appointments.add(appointment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error listing appointments: " + e.getMessage());
        }

        return appointments;
    }

    // Method to check if an appointment belongs to a patient
    public boolean isAppointmentOwnedByPatient(String patientName, int appointmentId) throws SQLException {
        String sql = "SELECT COUNT(*) AS count " +
                     "FROM hospital_system.patients p " +
                     "JOIN hospital_system.appointments a ON p.id = a.patient_id " +
                     "WHERE p.name = ? AND a.id = ?";

        try (Connection conn = db_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patientName);
            stmt.setInt(2, appointmentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("count") > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error checking appointment ownership: " + e.getMessage());
        }

        return false;
    }

    // Modified delete appointment from a patient method
    public void deletePatientAppointmentById(String patientName, int appointmentId) throws SQLException {
        if (!isAppointmentOwnedByPatient(patientName, appointmentId)) {
            System.out.println("Appointment does not belong to the patient!");
            return;
        }

        String getPatientIdSql = "SELECT id FROM hospital_system.patients WHERE name = ?";
        String deleteAppointmentSql = "DELETE FROM hospital_system.appointments WHERE id = ? AND patient_id = ?";

        Connection conn = null;
        PreparedStatement getPatientIdStmt = null;
        PreparedStatement deleteAppointmentStmt = null;
        ResultSet rs = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            conn = db_Connection.getConnection();

            // Get patient ID
            getPatientIdStmt = conn.prepareStatement(getPatientIdSql);
            getPatientIdStmt.setString(1, patientName);
            rs = getPatientIdStmt.executeQuery();

            if (rs.next()) {
                int patientId = rs.getInt("id");

                // Delete appointment
                deleteAppointmentStmt = conn.prepareStatement(deleteAppointmentSql);
                deleteAppointmentStmt.setInt(1, appointmentId);
                deleteAppointmentStmt.setInt(2, patientId);
                deleteAppointmentStmt.executeUpdate();

                System.out.println("Appointment deleted successfully for patient!");
            } else {
                System.out.println("Patient not found!");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error deleting appointment for patient: " + e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (getPatientIdStmt != null) {
                getPatientIdStmt.close();
            }
            if (deleteAppointmentStmt != null) {
                deleteAppointmentStmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    // Find appointment by ID
    public Appointment findAppointmentByID(int appointmentId) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Appointment appointment = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            conn = db_Connection.getConnection();

            // Prepare the SQL query
            String sql = "SELECT * FROM appointments WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, appointmentId);

            // Execute the query
            rs = stmt.executeQuery();

            // Checks if it has found a patient
            if (rs.next()) {
                appointment = new Appointment(
                    rs.getString("appointment_date_time"),
                    rs.getString("doctor"),
                    rs.getString("diagnosis")
                );
                appointment.setId(rs.getInt("id"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error finding appointment: " + e.getMessage());
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

        return appointment;
    }
}

package Controller.db_Connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Medication;
import Model.Patient;
import Model.Appointment;

// Class to connect to the database
public class MedicationDAO {

    // add medication to a patient in the database
    public void addMedicationToPatient(Medication medication, Patient patient) {
        String medicationSql = "INSERT INTO medications (name, dosage, frequency, description, doctor, prescription_date) VALUES (?, ?, ?, ?, ?, ?)";
        String patientToMedicationSql = "INSERT INTO patient_medications (patient_id, medication_id) VALUES (?, ?)";

        try (Connection conn = db_Connection.getConnection()) {
            // Desativa o auto commit para controle manual da transação
            conn.setAutoCommit(false);

            // Salva o medicamento
            try (PreparedStatement medicationStmt = conn.prepareStatement(medicationSql,
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                medicationStmt.setString(1, medication.getMedicationName());
                medicationStmt.setString(2, medication.getDosage());
                medicationStmt.setString(3, medication.getFrequency());
                medicationStmt.setString(4, medication.getDescription());
                medicationStmt.setString(5, medication.getDoctor());
                medicationStmt.setString(6, medication.getPrescriptionDate());

                medicationStmt.executeUpdate();

                // Pega o ID gerado automaticamente para o medicamento
                ResultSet generatedKeys = medicationStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    medication.setId(generatedKeys.getInt(1));
                }
            }

            // Cria a relação entre paciente e medicamento
            try (PreparedStatement relationStmt = conn.prepareStatement(patientToMedicationSql)) {
                relationStmt.setInt(1, patient.getId());
                relationStmt.setInt(2, medication.getId());

                relationStmt.executeUpdate();
            }

            // Confirma a transação
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // add medication to an appointment in the database
    public void addMedicationToAppointment(Medication medication, Appointment appointment) {
        String medicationSql = "INSERT INTO medications (name, dosage, frequency, description, doctor, prescription_date) VALUES (?, ?, ?, ?, ?, ?)";
        String medicationToAppointmentSql = "INSERT INTO appointment_medications (appointment_id, medication_id) VALUES (?, ?)";

        try (Connection conn = db_Connection.getConnection()) {
            // Desativa o auto commit para controle manual da transação
            conn.setAutoCommit(false);

            // Salva o medicamento
            try (PreparedStatement medicationStmt = conn.prepareStatement(medicationSql,
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                medicationStmt.setString(1, medication.getMedicationName());
                medicationStmt.setString(2, medication.getDosage());
                medicationStmt.setString(3, medication.getFrequency());
                medicationStmt.setString(4, medication.getDescription());
                medicationStmt.setString(5, medication.getDoctor());
                medicationStmt.setString(6, medication.getPrescriptionDate());

                medicationStmt.executeUpdate();

                // Pega o ID gerado automaticamente para o medicamento
                ResultSet generatedKeys = medicationStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    medication.setId(generatedKeys.getInt(1));
                }
            }

            // Cria a relação entre paciente e medicamento
            try (PreparedStatement relationStmt = conn.prepareStatement(medicationToAppointmentSql)) {
                relationStmt.setInt(1, appointment.getId());
                relationStmt.setInt(2, medication.getId());

                relationStmt.executeUpdate();
            }

            // Confirma a transação
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // List all medications from a patient
    public List<Medication> listMedicationsByPatientName(String patientName) throws SQLException {
        List<Medication> medications = new ArrayList<>();
        String sql = "SELECT m.id, m.name AS medication_name, m.dosage, m.frequency, m.description, m.doctor, m.prescription_date "
                +
                "FROM hospital_system.patients p " +
                "JOIN hospital_system.patient_medications pm ON p.id = pm.patient_id " +
                "JOIN hospital_system.medications m ON pm.medication_id = m.id " +
                "WHERE p.name = ?";

        try (Connection conn = db_Connection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patientName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Medication medication = new Medication(
                            rs.getInt("id"),
                            rs.getString("medication_name"),
                            rs.getString("dosage"),
                            rs.getString("frequency"),
                            rs.getString("description"),
                            rs.getString("doctor"),
                            rs.getString("prescription_date"));
                    medications.add(medication);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error listing medications: " + e.getMessage());
        }

        return medications;
    }

    // Delete medication from a patient
    public void deletePatientMedicationByName(String patientName, int medicationId) throws SQLException {
        String getPatientIdSql = "SELECT id FROM hospital_system.patients WHERE name = ?";
        String deleteMedicationSql = "DELETE FROM hospital_system.patient_medications WHERE patient_id = ? AND medication_id = ?";
        Connection conn = null;
        PreparedStatement getPatientIdStmt = null;
        PreparedStatement deleteMedicationStmt = null;
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

                // Delete medication
                deleteMedicationStmt = conn.prepareStatement(deleteMedicationSql);
                deleteMedicationStmt.setInt(1, patientId);
                deleteMedicationStmt.setInt(2, medicationId);
                deleteMedicationStmt.executeUpdate();

                System.out.println("Medication deleted successfully for patient!");
            } else {
                System.out.println("Patient not found!");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error deleting medication for patient: " + e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (getPatientIdStmt != null) {
                getPatientIdStmt.close();
            }
            if (deleteMedicationStmt != null) {
                deleteMedicationStmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

}
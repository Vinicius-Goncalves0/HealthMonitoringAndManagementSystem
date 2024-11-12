package Controller.db_Connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Medication;
import Model.Patient;

public class PatientDAO {

    // add a patient to the database
    public void addPatient(Patient patient) throws SQLException {
        String sql = "INSERT INTO patients (name, cpf, birth_date, address, phone, email) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = db_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patient.getName());
            stmt.setString(2, patient.getCPF());
            stmt.setDate(3, java.sql.Date.valueOf(patient.getBirthDate()));
            stmt.setString(4, patient.getAddress());
            stmt.setString(5, patient.getPhone());
            stmt.setString(6, patient.getEmail());
            stmt.executeUpdate();
        }
    }

    // add a medication to a patient
    public void addMedicationToPatient(int patientId, int medicationId) throws SQLException {
        String sql = "INSERT INTO patient_medications (patient_id, medication_id) VALUES (?, ?)";
        try (Connection conn = db_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            stmt.setInt(2, medicationId);
            stmt.executeUpdate();
        }
    }

    // remove a medication from a patient
    public void removeMedicationFromPatient(int patientId, int medicationId) throws SQLException {
        String sql = "DELETE FROM patient_medications WHERE patient_id = ? AND medication_id = ?";
        try (Connection conn = db_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            stmt.setInt(2, medicationId);
            stmt.executeUpdate();
        }
    }

    // List all medication patients from the database
    public List<Medication> listMedicationsForPatient(int patientId) throws SQLException {
        List<Medication> medications = new ArrayList<>();
        String sql = "SELECT m.id, m.name, m.dosage, m.frequency, m.description, m.doctor, m.prescriptionDate " +
                     "FROM medications m " +
                     "JOIN patient_medications pm ON m.id = pm.medication_id " +
                     "WHERE pm.patient_id = ?";
        try (Connection conn = db_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Medication medication = new Medication(
                        rs.getString("name"),
                        rs.getInt("dosage"),
                        rs.getInt("frequency"),
                        rs.getString("description"),
                        rs.getString("doctor"),
                        rs.getDate("prescriptionDate").toLocalDate()
                    );
                    medications.add(medication);
                }
            }
        }
        return medications;
    }

}

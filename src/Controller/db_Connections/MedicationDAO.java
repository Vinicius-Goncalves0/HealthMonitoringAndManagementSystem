package Controller.db_Connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.List;

import Model.Medication;
import Model.Patient;

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
            try (PreparedStatement medicationStmt = conn.prepareStatement(medicationSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                medicationStmt.setString(1, medication.getMedicationName());
                medicationStmt.setInt(2, medication.getDosage());
                medicationStmt.setInt(3, medication.getFrequency());
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

        } 
            catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
























    
    // // Method to get all medications from the database
    // public void addMedication(Medication medication) {
    //     String sql = "INSERT INTO medication (name, dosage, frequency, description, doctor, prescription_date) VALUES (?, ?, ?, ?, ?, ?)";
    //     try (Connection conn = db_Connection.getConnection();
    //             PreparedStatement stmt = conn.prepareStatement(sql)) {
    //         stmt.setString(1, medication.getMedicationName());
    //         stmt.setInt(2, medication.getDosage());
    //         stmt.setInt(3, medication.getFrequency());
    //         stmt.setString(4, medication.getDescription());
    //         stmt.setString(5, medication.getDoctor());
    //         stmt.setDate(6, java.sql.Date.valueOf(medication.getPrescriptionDate()));
    //         stmt.execute();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

    // // List all medications from the database
    // public List<Medication> ListMedications() throws SQLException {
    //     List<Medication> medications = new ArrayList<>();
    //     String sql = "SELECT * FROM medications";
    //     try (Connection conn = db_Connection.getConnection();
    //          PreparedStatement stmt = conn.prepareStatement(sql);
    //          ResultSet rs = stmt.executeQuery()) {
    //         while (rs.next()) {
    //             Medication medication = new Medication(
    //                 rs.getString("name"),
    //                 rs.getInt("dosage"),
    //                 rs.getInt("frequency"),
    //                 rs.getString("description"),
    //                 rs.getString("doctor"),
    //                 rs.getDate("prescriptionDate").toLocalDate()
    //             );
    //             medications.add(medication);
    //         }
    //     }
    //     return medications;
    // }

    // // Method to remove a medication from the database
    // public void removeMedication(Medication medication) {
    //     String sql = "DELETE FROM medications WHERE name = ?";
    //     try (Connection conn = db_Connection.getConnection();
    //          PreparedStatement stmt = conn.prepareStatement(sql)) {
    //         stmt.setString(1, medication.getMedicationName());
    //         stmt.execute();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }



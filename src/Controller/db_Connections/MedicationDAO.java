package Controller.db_Connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
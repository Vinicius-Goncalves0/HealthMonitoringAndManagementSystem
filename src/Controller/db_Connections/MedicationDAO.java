package Controller.db_Connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Medication;

// Class to connect to the database
public class MedicationDAO {
    
    // Method to get all medications from the database
    public void addMedication(Medication medication) {
        String sql = "INSERT INTO medication (name, dosage, frequency, description, doctor, prescription_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = db_Connection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, medication.getName());
            stmt.setInt(2, medication.getDosage());
            stmt.setInt(3, medication.getFrequency());
            stmt.setString(4, medication.getDescription());
            stmt.setString(5, medication.getDoctor());
            stmt.setDate(6, java.sql.Date.valueOf(medication.getPrescriptionDate()));
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // List all medications from the database
    public List<Medication> ListMedications() throws SQLException {
        List<Medication> medications = new ArrayList<>();
        String sql = "SELECT * FROM medications";
        try (Connection conn = db_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
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
        return medications;
    }

    // Method to remove a medication from the database
    public void removeMedication(Medication medication) {
        String sql = "DELETE FROM medications WHERE name = ?";
        try (Connection conn = db_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, medication.getName());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

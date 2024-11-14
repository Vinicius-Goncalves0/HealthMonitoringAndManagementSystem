package Controller.db_Connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.List;

// import Model.Medication;
import Model.Patient;

public class PatientDAO {

    // add a patient to the database
    public void addPatient(Patient patient) throws SQLException {
        String sql = "INSERT INTO patients (name, cpf, birth_date, address, phone, email) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = db_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

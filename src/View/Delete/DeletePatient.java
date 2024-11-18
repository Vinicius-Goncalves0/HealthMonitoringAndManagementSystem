package View.Delete;

import java.sql.SQLException;
import Controller.db_Connections.PatientDAO;

public class DeletePatient {
    public void deletePatient(int patientId) {
        PatientDAO patientDAO = new PatientDAO();

        try {
            patientDAO.deletePatient(patientId);
            System.out.println("Patient and all associated data successfully deleted.");
        } catch (SQLException e) {
            System.out.println("\n--- Error deleting the patient: " + e.getMessage() + " ---\n");
        }
    }
}
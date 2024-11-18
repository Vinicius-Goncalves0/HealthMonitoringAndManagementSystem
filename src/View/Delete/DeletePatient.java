package View.Delete;

import java.sql.SQLException;
import java.util.Scanner;
import Controller.db_Connections.PatientDAO;

public class DeletePatient {
    @SuppressWarnings("resource")
    public void deletePatient(int patientId) {
        Scanner scan = new Scanner(System.in);
        PatientDAO patientDAO = new PatientDAO();

        try {
            patientDAO.deletePatient(patientId);
            System.out.println("Patient and all associated data successfully deleted.");
        } catch (SQLException e) {
            System.out.println("\n--- Error deleting the patient: " + e.getMessage() + " ---\n");
        }
    }
}
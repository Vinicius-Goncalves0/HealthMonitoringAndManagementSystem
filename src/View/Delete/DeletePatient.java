package View.Delete;

import java.sql.SQLException;
import java.util.Scanner;

import Controller.db_Connections.PatientDAO;

public class DeletePatient {
    public void deletePatient(int patientId) {
        Scanner scan = new Scanner(System.in);
        PatientDAO patientDAO = new PatientDAO();

        System.out.println("You are about to delete a patient from the database. Are you sure you want to proceed?");
        System.out.println("Type 'Yes, I want to delete.' to confirm, or any other key to cancel.");
        String confirmation = scan.nextLine();

        if (!confirmation.equals("Yes, I want to delete.")){
            try {
                patientDAO.deletePatient(patientId);
                System.out.println("Patient and all associated data successfully deleted.");
            } catch (SQLException e) {
                System.out.println("\n--- Error deleting the patient: " + e.getMessage() + " ---\n");
            }
        }
        scan.close();
    }
}
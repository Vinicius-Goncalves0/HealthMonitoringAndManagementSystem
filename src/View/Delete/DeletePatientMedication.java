package View.Delete;

import java.sql.SQLException;
import java.util.Scanner;
import Controller.MedicationController;

public class DeletePatientMedication {

    @SuppressWarnings("resource")
    public void deleteMedication(String patientName) {
        Scanner scan = new Scanner(System.in);
        MedicationController medicationController = new MedicationController();

        System.out.println("\nMedication ID:");
        int medicationId = scan.nextInt();

        try {
            medicationController.deletePatientMedication(patientName, medicationId);
        } catch (SQLException e) {
            System.out.println("\n--- Error deleting medicine: " + e.getMessage() + " ---\n");
        }
    }
}

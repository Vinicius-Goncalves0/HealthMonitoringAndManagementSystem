package View.Delete;

import java.sql.SQLException;
import java.util.Scanner;

import Controller.AlertController;

public class DeleteAlert {
    @SuppressWarnings("resource")
    public void deleteAlert(String patientName) {
        Scanner scan = new Scanner(System.in);
        AlertController alertController = new AlertController();

        System.out.println("\nAlert ID:");
        int alertId = scan.nextInt();

        try {
            alertController.deletePatientAlert(patientName, alertId);
        } catch (SQLException e) {
            System.out.println("\n--- Error deleting alert: " + e.getMessage() + " ---\n");
        }
    }
}

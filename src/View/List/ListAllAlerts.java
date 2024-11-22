package View.List;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import Controller.AlertController;
import Controller.MedicationController;
import Controller.db_Connections.AlertDAO;
import Controller.db_Connections.PatientDAO;
import Model.Alert;
import Model.Medication;

public class ListAllAlerts {
    Scanner scan = new Scanner(System.in);

    private AlertController alertController;
    private PatientDAO patientDAO;
    private AlertDAO alertDAO;

    public ListAllAlerts() {
        this.alertController = new AlertController();
        this.patientDAO = new PatientDAO();
        this.alertDAO = new AlertDAO();
    }

    public void displayAlerts(List<Alert> alerts, String patientName) {
        if (alerts.isEmpty()) {
            System.out.println("No alert found for the given patient.");
        } else {
            for (Alert alert : alerts) {
                System.out.println("");
                System.out.println("|| Patient: " + patientName);
                System.out.println("__________________________________________________________");
                System.out.println("|| Alert ID: " + alert.getId());
                System.out.println("|| Type: " + alert.getType());
                System.out.println("|| Message: " + alert.getMessage());
                System.out.println("|| Doctor: " + alert.getDoctor());
                System.out.println("|| Date: " + alert.getData());
                System.out.println("==========================================================");
            }
        }
    }

    public void listAlertsByPatientId(int patientId) {
        try {
            List<Alert> alerts = alertController.listAlertsByPatientId(patientId);
            displayAlerts(alerts, patientDAO.findPatientNameByID(patientId));
            System.out.println("Press enter to continue...");
            scan.nextLine();
        } catch (SQLException e) {
            System.out.println("\n--- Erro ao listar alertas: " + e.getMessage() + " ---\n");
        }
    }

    public void displayAllAlerts() {
        try {
            List<Alert> alerts = alertController.listarAlertas();
            for (Alert alert : alerts) {
                int patientId = alertDAO.getPatientIdByAlertId(alert.getId());
                String patientName = patientDAO.findPatientNameByID(patientId);
                System.out.println("");
                System.out.println("|| Patient: " + patientName);
                System.out.println("__________________________________________________________");
                System.out.println("|| Alert ID: " + alert.getId());
                System.out.println("|| Type: " + alert.getType());
                System.out.println("|| Message: " + alert.getMessage());
                System.out.println("|| Doctor: " + alert.getDoctor());
                System.out.println("|| Date: " + alert.getData());
                System.out.println("==========================================================");
            }
        } catch (SQLException e) {
            System.out.println("Error displaying alerts: " + e.getMessage());
        }
        System.out.println("Press enter to continue...");
        scan.nextLine();
    }
}

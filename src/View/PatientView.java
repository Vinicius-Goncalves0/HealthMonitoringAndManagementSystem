package View;

import java.sql.SQLException;
import java.util.Scanner;

import Controller.db_Connections.PatientDAO;
import Model.Patient;

public class PatientView {

    public void displayPatientMenu() {
        Scanner scan = new Scanner(System.in);
        CreatePatientMenu createPatientMenu = new CreatePatientMenu();
        PatientDAO patientDAO = new PatientDAO();

        while (true) {
            System.out.println("\nPatient Menu:");
            System.out.println("1. Access Patient");
            System.out.println("2. Create Patient");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            
            if (scan.hasNextInt()) {
                int choice = scan.nextInt();
                scan.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.println("\nPatient's Name:");
                        String patientName = scan.nextLine();

                        try {
                            Patient patient = patientDAO.findPatientByName(patientName);
                            if (patient != null) {
                                patientAccessed(patient, patientName, scan);
                            } else {
                                System.out.println("\n--- Paciente " + patientName + " não encontrado ---\n");
                            }
                        } catch (SQLException e) {
                            System.out.println("\n--- Erro ao acessar o paciente: " + patientName + " " + e.getMessage() + " ---\n");
                        }
                        break;
                    case 2:
                        createPatientMenu.createPatientMenu();
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scan.next(); // Consume the invalid input
            }
        }
    }

    public void patientAccessed(Patient patient, String patientName, Scanner scan) {
        CreateMedicationMenu createMedicationMenu = new CreateMedicationMenu();
        UpdatePatientMenu updatePatientMenu = new UpdatePatientMenu();

        while (true) {
            System.out.println("\nPatient: " + patient.getName());
            System.out.println("1. Consult The History");
            System.out.println("2. Update Data");
            System.out.println("3. Consult Data");
            System.out.println("4. Make an appointment");
            System.out.println("5. Add Medication");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            if (scan.hasNextInt()) {
                int choice = scan.nextInt();
                scan.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        // Code to consult the history
                        System.out.println("Consulting the history...");
                        break;
                    case 2:
                        updatePatientMenu.updatePatientMenu(patientName);
                        System.out.println("Updating data...");
                        break;
                    case 3:
                        // Code to consult data
                        System.out.println("Consulting data...");
                        break;
                    case 4:
                        // Code to make an appointment
                        System.out.println("Making an appointment...");
                        break;
                    case 5:
                        createMedicationMenu.createMedicationMenu(patientName);
                        System.out.println("Adding medication...");
                        System.out.println("Medication added successfully!");
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scan.next(); // Consume the invalid input
            }
        }
    }
}
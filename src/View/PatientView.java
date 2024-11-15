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
                            System.out.println("\n--- Erro ao acessar o paciente: " + patientName + " " + e.getMessage()
                                    + " ---\n");
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
        ListDataPatientMenu listDataPatientMenu = new ListDataPatientMenu();
        ListPatientMedicationMenu listPatientMedicationMenu = new ListPatientMedicationMenu();
        DeletePatientMedication deletePatientMedication = new DeletePatientMedication();

        while (true) {
            System.out.println("\nPatient: " + patient.getName());
            System.out.println("1. Consult The History"); // Done
            System.out.println("2. Update Data"); // Done
            System.out.println("3. Consult Data"); // Done
            System.out.println("4. Make an appointment");
            System.out.println("5. Consult scheduled appointments");
            System.out.println("6. Add Medication"); // Done
            System.out.println("7. Consult Medication");
            System.out.println("8. Delete Medication");
            System.out.println("9. Exit"); // Done
            System.out.print("Enter your choice: ");

            if (scan.hasNextInt()) {
                int choice = scan.nextInt();
                scan.nextLine(); // Consume '\n'

                System.out.print("\n");
                switch (choice) {
                    case 1:
                        System.out.println("Consulting the history...");
                        System.out.println("Histories: " + patient.getHistories());
                        break;
                    case 2:
                        System.out.println("Updating data...");
                        updatePatientMenu.updatePatientMenu(patientName);
                        break;
                    case 3:
                        System.out.println("Consulting data...");
                        listDataPatientMenu.listPatientsByName(patientName);
                        break;
                    case 4:
                        System.out.println("Making an appointment...");
                        break;
                    case 5:
                        System.out.println("Viewing scheduled appointments...");
                        break;
                    case 6:
                        System.out.println("Adding medication...");
                        createMedicationMenu.createMedicationMenu(patientName);
                        break;
                    case 7:
                        System.out.println("Viewing medication...");
                        listPatientMedicationMenu.listMedicationsByPatientName(patientName);
                        break;
                    case 8:
                        System.out.println("Deleting medication...");
                        deletePatientMedication.deleteMedication(patientName);
                        break;
                    case 9:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scan.nextLine(); // Consume invalid input
            }
        }
    }
}
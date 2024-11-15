package View;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import Controller.PatientController;
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
        ListDataPatientMenu listDataPatientMenu = new ListDataPatientMenu();
        PatientController patientController = new PatientController();
    
        while (true) {
            System.out.println("\nPatient: " + patient.getName());
            System.out.println("1. Consult The History");
            System.out.println("2. Update Data"); // Done 
            System.out.println("3. Consult Data");
            System.out.println("4. Make an appointment");
            System.out.println("5. Add Medication"); // Done
            System.out.println("6. Exit"); // Done
            System.out.print("Enter your choice: ");
    
            if (scan.hasNextInt()) {
                int choice = scan.nextInt();
                scan.nextLine(); // Consume '\n'
    
                switch (choice) {
                    case 1:
                        System.out.println("Consulting the history...");
                        break;
                    case 2:
                        System.out.println("Updating data...");
                        updatePatientMenu.updatePatientMenu(patientName);
                        break;
                    case 3:
                        System.out.println("Consulting data...");
                        System.out.println("\nEnter the name to search:");
                        String name = scan.nextLine();
                        try {
                            List<Patient> patients = patientController.listPatientsByName(name);
                            listDataPatientMenu.displayPatients(patients);
                        } catch (SQLException e) {
                            System.out.println("\n--- Erro ao listar pacientes: " + e.getMessage() + " ---\n");
                        }
                        break;
                    case 4:
                        System.out.println("Making an appointment...");
                        break;
                    case 5:
                        System.out.println("Adding medication...");
                        createMedicationMenu.createMedicationMenu(patientName);
                        break;
                    case 6:
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
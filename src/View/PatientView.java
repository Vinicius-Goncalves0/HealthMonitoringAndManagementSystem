package View;

import java.sql.SQLException;
import java.util.Scanner;
import Controller.db_Connections.PatientDAO;
import Model.Patient;
import View.Create.CreateAppointmentMenuByPatient;
import View.Create.CreatePatientMenu;
import View.Delete.DeletePatientAppointment;
import View.List.ListDataPatient;
import View.List.ListPatientAppointmentMenu;
import View.List.ListPatients;
import View.Update.UpdatePatientMenu;

public class PatientView {

    public void displayPatientMenu() {
        Scanner scan = new Scanner(System.in);
        CreatePatientMenu createPatientMenu = new CreatePatientMenu();
        ListPatients listPatients = new ListPatients();
        PatientDAO patientDAO = new PatientDAO();

        while (true) {
            System.out.println("\nPatient Menu:");
            System.out.println("1. List Patients");
            System.out.println("2. Access Patient");
            System.out.println("3. Create Patient");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            if (scan.hasNextInt()) {
                int choice = scan.nextInt();
                scan.nextLine(); // Consume newline

                switch (choice) {
                    case 0:
                        System.out.println("Exiting...");
                        return;
                    case 1:
                        System.out.println("\nListing patients...");
                        listPatients.listAllPatients();
                        break;
                    case 2:
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
                    case 3:
                        createPatientMenu.createPatientMenu();
                        break;
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

        while (true) {
            System.out.println("\nPatient: " + patient.getName());
            System.out.println("1. Patient Data Menu");
            System.out.println("2. Appointment Menu");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            if (scan.hasNextInt()) {
                int choice = scan.nextInt();
                scan.nextLine(); // Consume '\n'

                System.out.print("\n");
                switch (choice) {
                    case 0:
                        System.out.println("Exiting...");
                        return;
                    case 1:
                        System.out.println("Patient Data Menu...");
                        patientDataMenu(patient, patientName, scan);
                        break;
                    case 2:
                        System.out.println("Appointment Menu...");
                        appointmentMenu(patient, patientName, scan);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scan.nextLine(); // Consume invalid input
            }
        }
    }

    public void appointmentMenu(Patient patient, String patientName, Scanner scan) {
        CreateAppointmentMenuByPatient createAppointmentMenuByPatient = new CreateAppointmentMenuByPatient();
        ListPatientAppointmentMenu listPatientAppointmentMenu = new ListPatientAppointmentMenu();
        DeletePatientAppointment deletePatientAppointment = new DeletePatientAppointment();

        while (true) {
            System.out.println("\nPatient: " + patient.getName());
            System.out.println("1. Make an appointment");
            System.out.println("2. Consult appointments");
            System.out.println("3. Delete appointment");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            if (scan.hasNextInt()) {
                int choice = scan.nextInt();
                scan.nextLine(); // Consume '\n'

                System.out.print("\n");
                switch (choice) {
                    case 0:
                        System.out.println("Exiting...");
                        return;
                    case 1:
                        System.out.println("Making an appointment...");
                        createAppointmentMenuByPatient.createAppointmentMenuByPatient(patientName);
                        break;
                    case 2:
                        System.out.println("Viewing appointments...");
                        listPatientAppointmentMenu.listAppointmentsByPatientName(patientName);
                        break;
                    case 3:
                        System.out.println("Deleting appointment...");
                        deletePatientAppointment.deleteAppointment(patientName);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scan.nextLine(); // Consume invalid input
            }
        }
    }

    public void patientDataMenu(Patient patient, String patientName, Scanner scan) {
        UpdatePatientMenu updatePatientMenu = new UpdatePatientMenu();
        ListDataPatient listDataPatientMenu = new ListDataPatient();

        while (true) {
            System.out.println("\nPatient: " + patient.getName());
            System.out.println("1. Consult The History");
            System.out.println("2. Consult Data");
            System.out.println("3. Update Data");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            if (scan.hasNextInt()) {
                int choice = scan.nextInt();
                scan.nextLine(); // Consume '\n'

                System.out.print("\n");
                switch (choice) {
                    case 0:
                        System.out.println("Exiting...");
                        return;
                    case 1:
                        System.out.println("Consulting the history...");
                        System.out.println("Histories: " + patient.getHistories());
                        System.out.println("Press Enter to continue...");
                        scan.nextLine();
                        break;
                    case 2:
                        System.out.println("Consulting data...");
                        listDataPatientMenu.listPatientsByName(patientName);
                        break;
                    case 3:
                        System.out.println("Updating data...");
                        updatePatientMenu.updatePatientMenu(patientName);
                        break;
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

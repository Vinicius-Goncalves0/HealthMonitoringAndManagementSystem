package View;

import java.sql.SQLException;
import java.util.Scanner;
import Controller.db_Connections.PatientDAO;
import Model.Patient;
import View.Create.CreateAppointmentMenu;
import View.Create.CreateMedicationMenu;
import View.Create.CreatePatientMenu;
import View.Delete.DeletePatient;
import View.Delete.DeletePatientAppointment;
import View.Delete.DeletePatientMedication;
import View.List.ListDataPatientMenu;
import View.List.ListPatientAppointmentMenu;
import View.List.ListPatientMedicationMenu;
import View.List.ListPatients;
import View.Update.UpdatePatientMenu;

public class PatientView {

    public void displayPatientMenu() {
        Scanner scan = new Scanner(System.in);
        CreatePatientMenu createPatientMenu = new CreatePatientMenu();
        DeletePatient deletePatient = new DeletePatient();
        ListPatients listPatients = new ListPatients();
        PatientDAO patientDAO = new PatientDAO();

        while (true) {
            System.out.println("\nPatient Menu:");
            System.out.println("1. Access Patient");
            System.out.println("2. List Patients");
            System.out.println("3. Create Patient");
            System.out.println("4. Delete Patient");
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
                        System.out.println("\nListing patients...");
                        listPatients.listAllPatients();
                        break;
                    case 3:
                        createPatientMenu.createPatientMenu();
                        break;
                    case 4:
                        System.out.println("\nPatient's ID to delete:");
                        int patientId = scan.nextInt();
                        scan.nextLine(); // Consume newline
                        System.out.println("\nDeleting patient...");
                        deletePatient.deletePatient(patientId);
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
        MonitoringView monitoringView = new MonitoringView();

        while (true) {
            System.out.println("\nPatient: " + patient.getName());
            System.out.println("1. Patient Data Menu");
            System.out.println("2. Appointment Menu");
            System.out.println("3. Medication Menu");
            System.out.println("4. Monitoring Menu");
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
                    case 3:
                        System.out.println("Medication Menu...");
                        medicationMenu(patient, patientName, scan);
                        break;
                    case 4:
                        System.out.println("Monitoring Menu...");
                        monitoringView.displayMonitoringMenu(patient, patientName, scan);
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
        CreateAppointmentMenu createAppointmentMenu = new CreateAppointmentMenu();
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
                        createAppointmentMenu.createAppointmentMenu(patientName);
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

    public void medicationMenu(Patient patient, String patientName, Scanner scan) {
        CreateMedicationMenu createMedicationMenu = new CreateMedicationMenu();
        ListPatientMedicationMenu listPatientMedicationMenu = new ListPatientMedicationMenu();
        DeletePatientMedication deletePatientMedication = new DeletePatientMedication();

        while (true) {
            System.out.println("\nPatient: " + patient.getName());
            System.out.println("1. Add Medication");
            System.out.println("2. Consult Medication");
            System.out.println("3. Delete Medication");
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
                        System.out.println("Adding medication...");
                        createMedicationMenu.createMedicationMenu(patientName);
                        break;
                    case 2:
                        System.out.println("Viewing medication...");
                        listPatientMedicationMenu.listMedicationsByPatientName(patientName);
                        break;
                    case 3:
                        System.out.println("Deleting medication...");
                        deletePatientMedication.deleteMedication(patientName);
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
        ListDataPatientMenu listDataPatientMenu = new ListDataPatientMenu();

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
package View.DocView;

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
import View.List.ListDataPatient;
import View.List.ListPatientAppointmentMenu;
import View.List.ListPatientMedicationMenu;
import View.List.ListPatients;
import View.Update.UpdatePatientMenu;

public class DocPatientView {

    public void displayPatientMenu() {
        Scanner scan = new Scanner(System.in);
        CreatePatientMenu createPatientMenu = new CreatePatientMenu();
        DeletePatient deletePatient = new DeletePatient();
        ListPatients listPatients = new ListPatients();
        PatientDAO patientDAO = new PatientDAO();

        while (true) {
            System.out.print("\n=== Patient ===\n");
            System.out.println("1. Access Patient");
            System.out.println("2. List Patients");
            System.out.println("3. Create Patient");
            System.out.println("4. Delete Patient");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            if (scan.hasNextInt()) {
                int choice = scan.nextInt();
                scan.nextLine();

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
                                System.out.println("\n--- Patient " + patientName + " not found ---\n");
                            }
                        } catch (SQLException e) {
                            System.out.println("\n--- Error accessing the patient: " + patientName + " " + e.getMessage()
                                    + " ---\n");
                        }
                        break;
                    case 2:
                        listPatients.listAllPatients();
                        break;
                    case 3:
                        createPatientMenu.createPatientMenu();
                        break;
                    case 4:
                        System.out.println("\nPatient's ID to delete:");
                        int patientId = scan.nextInt();
                        scan.nextLine();
                        deletePatient.deletePatient(patientId);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scan.next();
            }
        }
    }

    public void patientAccessed(Patient patient, String patientName, Scanner scan) {
        DocMonitoringView monitoringView = new DocMonitoringView();

        while (true) {
            System.out.print("\n=== Patient: " + patient.getName() + " ===\n");
            System.out.println("1. Patient Data Menu");
            System.out.println("2. Appointment Menu");
            System.out.println("3. Medication Menu");
            System.out.println("4. Monitoring Menu");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            if (scan.hasNextInt()) {
                int choice = scan.nextInt();
                scan.nextLine();

                System.out.print("\n");
                switch (choice) {
                    case 0:
                        System.out.println("Exiting...");
                        return;
                    case 1:
                        patientDataMenu(patient, patientName, scan);
                        break;
                    case 2:
                        appointmentMenu(patient, patientName, scan);
                        break;
                    case 3:
                        medicationMenu(patient, patientName, scan);
                        break;
                    case 4:
                        monitoringView.displayMonitoringMenu(patient, patientName, scan);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scan.nextLine();
            }
        }
    }

    public void appointmentMenu(Patient patient, String patientName, Scanner scan) {
        CreateAppointmentMenu createAppointmentMenu = new CreateAppointmentMenu();
        ListPatientAppointmentMenu listPatientAppointmentMenu = new ListPatientAppointmentMenu();
        DeletePatientAppointment deletePatientAppointment = new DeletePatientAppointment();

        while (true) {
            System.out.print("\n=== Patient: " + patient.getName() + " ===\n");
            System.out.println("1. Make an appointment");
            System.out.println("2. Consult appointments");
            System.out.println("3. Delete appointment");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            if (scan.hasNextInt()) {
                int choice = scan.nextInt();
                scan.nextLine();

                System.out.print("\n");
                switch (choice) {
                    case 0:
                        System.out.println("Exiting...");
                        return;
                    case 1:
                        createAppointmentMenu.createAppointmentMenu(patientName);
                        break;
                    case 2:
                        listPatientAppointmentMenu.listAppointmentsByPatientName(patientName);
                        break;
                    case 3:
                        deletePatientAppointment.deleteAppointment(patientName);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scan.nextLine();
            }
        }
    }

    public void medicationMenu(Patient patient, String patientName, Scanner scan) {
        CreateMedicationMenu createMedicationMenu = new CreateMedicationMenu();
        ListPatientMedicationMenu listPatientMedicationMenu = new ListPatientMedicationMenu();
        DeletePatientMedication deletePatientMedication = new DeletePatientMedication();

        while (true) {
            System.out.print("\n=== Patient: " + patient.getName() + " ===\n");
            System.out.println("1. Add Medication");
            System.out.println("2. Consult Medication");
            System.out.println("3. Delete Medication");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            if (scan.hasNextInt()) {
                int choice = scan.nextInt();
                scan.nextLine();

                System.out.print("\n");
                switch (choice) {
                    case 0:
                        System.out.println("Exiting...");
                        return;
                    case 1:
                        createMedicationMenu.createMedicationMenu(patientName);
                        break;
                    case 2:
                        listPatientMedicationMenu.listMedicationsByPatientName(patientName);
                        break;
                    case 3:
                        deletePatientMedication.deleteMedication(patientName);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scan.nextLine();
            }
        }
    }

    public void patientDataMenu(Patient patient, String patientName, Scanner scan) {
        UpdatePatientMenu updatePatientMenu = new UpdatePatientMenu();
        ListDataPatient listDataPatientMenu = new ListDataPatient();

        while (true) {
            System.out.print("\n=== Patient: " + patient.getName() + " ===\n");
            System.out.println("1. Consult The History");
            System.out.println("2. Consult Data");
            System.out.println("3. Update Data");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            if (scan.hasNextInt()) {
                int choice = scan.nextInt();
                scan.nextLine();

                System.out.print("\n");
                switch (choice) {
                    case 0:
                        System.out.println("Exiting...");
                        return;
                    case 1:
                        System.out.println("\n=== History ===\n");
                        System.out.println("Histories: " + patient.getHistories());
                        System.out.println("Press Enter to continue...");
                        scan.nextLine();
                        break;
                    case 2:
                        listDataPatientMenu.listPatientsByName(patientName);
                        break;
                    case 3:
                        updatePatientMenu.updatePatientMenu(patientName);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scan.nextLine();
            }
        }
    }
}
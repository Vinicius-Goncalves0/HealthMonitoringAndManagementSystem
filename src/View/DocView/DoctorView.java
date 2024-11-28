package View.DocView;

import java.sql.SQLException;
import java.util.Scanner;

import Controller.AlertController;
import Controller.Monitoring;
import Controller.db_Connections.DoctorDAO;
import Model.Doctor;
import View.Create.CreateDoctorMenu;
import View.List.ListAllAlerts;
import View.List.ListDataDoctor;
import View.List.ListDoctor;
import View.Update.UpdateDoctorMenu;

public class DoctorView {

    public void displayDoctorMenu() {
        Scanner scan = new Scanner(System.in);
        CreateDoctorMenu createDoctorMenu = new CreateDoctorMenu();
        ListDoctor listDoctor = new ListDoctor();
        DoctorDAO doctorDAO = new DoctorDAO();

        while (true) {
            System.out.print("\n=== Doctor ===\n");
            System.out.println("1. List Doctors");
            System.out.println("2. Access Doctor");
            System.out.println("3. Create Doctor");
            System.out.println("4. Alerts");
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
                        listDoctor.listAllDoctors();
                        break;
                    case 2:
                        System.out.println("Doctor's Name:");
                        String doctorName = scan.nextLine();

                        try {
                            Doctor doctor = doctorDAO.findDoctorByName(doctorName);
                            if (doctor != null) {
                                doctorAccessed(doctor, doctorName, scan);
                            } else {
                                System.out.println("\n--- Doctor " + doctorName + " not found ---");
                            }
                        } catch (SQLException e) {
                            System.out.println("\n--- Error accessing the doctor: " + doctorName + " " + e.getMessage()
                                    + " ---\n");
                        }
                        break;
                    case 3:
                        createDoctorMenu.createDoctorMenu();
                        break;
                    case 4:
                        alertMenu(scan);
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

    public void doctorAccessed(Doctor doctor, String doctorName, Scanner scan) {
        DocPatientView docPatientView = new DocPatientView();

        while (true) {
            System.out.print("\n=== Doctor: " + doctor.getName() + " ===\n");
            System.out.println("1. Doctor Data Menu");
            System.out.println("2. Patient Secion");
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
                        doctorDataMenu(doctor, doctorName, scan);
                        break;
                    case 2:
                        docPatientView.displayPatientMenu();
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

    public void doctorDataMenu(Doctor doctor, String doctorName, Scanner scan) {
        ListDataDoctor listDataDoctorMenu = new ListDataDoctor();
        UpdateDoctorMenu updateDoctorMenu = new UpdateDoctorMenu();

        while (true) {
            System.out.print("\n=== Doctor: " + doctor.getName() + " ===\n");
            System.out.println("1. Consult Data");
            System.out.println("2. Update Data");
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
                        listDataDoctorMenu.listDoctorsByName(doctorName);
                        break;
                    case 2:
                        updateDoctorMenu.updateDoctorMenu(doctorName);
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

    public void alertMenu(Scanner scan) {
        AlertController alertController = new AlertController();
        Monitoring monitoring = new Monitoring();
        ListAllAlerts listAllAlerts = new ListAllAlerts();

        while (true) {
            System.out.print("\n=== Alert ===\n");
            System.out.println("1. View Alerts");
            System.out.println("2. Delete Alerts");
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
                        monitoring.generatePatientAlert();
                        listAllAlerts.displayAllAlerts();
                        break;
                    case 2:
                        System.out.print("Enter the alert ID to delete: ");
                        int alertId = scan.nextInt();
                        try {
                            alertController.deleteAlertById(alertId);
                        } catch (SQLException e) {
                            System.out.println("Error deleting alert: " + e.getMessage());
                        }
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

package View;

import java.sql.SQLException;
import java.util.Scanner;

import Controller.db_Connections.DoctorDAO;
import Model.Doctor;
import View.Create.CreateDoctorMenu;
import View.Update.UpdateDoctorMenu;
import View.List.ListDataDoctorMenu;

public class DoctorView {

    public void displayDoctorMenu() {
        Scanner scan = new Scanner(System.in);
        CreateDoctorMenu createDoctorMenu = new CreateDoctorMenu();
        DoctorDAO doctorDAO = new DoctorDAO();

        while (true) {
            System.out.println("\nDoctor Menu:");
            System.out.println("1. Access Doctor");
            System.out.println("2. Create Doctor");
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
                            System.out.println("\n--- Error accessing the doctor: " + doctorName + " " + e.getMessage() + " ---\n");
                        }
                        break;
                    case 2:
                        createDoctorMenu.createDoctorMenu();
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

    public void doctorAccessed(Doctor doctor, String doctorName, Scanner scan) {

        while(true) {
            System.out.println("\nDoctor: " + doctor.getName());
            System.out.println("1. Doctor Data Menu");
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
                        System.out.println("Doctor Data Menu...");
                        doctorDataMenu(doctor, doctorName, scan);
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

    public void doctorDataMenu(Doctor doctor, String doctorName, Scanner scan) {
        ListDataDoctorMenu listDataDoctorMenu = new ListDataDoctorMenu();
        UpdateDoctorMenu updateDoctorMenu = new UpdateDoctorMenu();

        while (true) {
            System.out.println("\nDoctor: " + doctor.getName());
            System.out.println("1. Consult Data");
            System.out.println("2. Update Data");
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
                        System.out.println("Consulting data...");
                        listDataDoctorMenu.listDoctorsByName(doctorName);
                        break;
                    case 2:
                        System.out.println("Updating data...");
                        updateDoctorMenu.updateDoctorMenu(doctorName);
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

package View;

import java.util.Scanner;

import View.DocView.DoctorView;

public class View {
    public void displayMainMenu() {
        Scanner scan = new Scanner(System.in);
        PatientView patientView = new PatientView();
        DoctorView doctorView = new DoctorView();

        while (true) {
            System.out.print("\n=== Hospital ===\n");
            System.out.println("1. Patient Menu");
            System.out.println("2. Doctor Menu");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            if (scan.hasNextInt()) {
                int choice = scan.nextInt();
                scan.nextLine();

                switch (choice) {
                    case 0:
                        System.out.println("Exiting...");
                        scan.close();
                        return;
                    case 1:
                        patientView.displayPatientMenu();
                        break;
                    case 2:
                        doctorView.displayDoctorMenu();
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

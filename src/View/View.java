package View;

import java.util.Scanner;

public class View {
    public void loginMenu() {

    }

    public void displayMainMenu() {
        Scanner scan = new Scanner(System.in);
        PatientView patientView = new PatientView();
        DoctorView doctorView = new DoctorView();

        while (true) {
            System.out.println("\nMain Menu: ");
            System.out.println("1. Patient Menu");
            System.out.println("2. Doctor Menu");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scan.nextInt();
            scan.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    patientView.displayPatientMenu();
                    break;
                case 2:
                    doctorView.displayDoctorMenu();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scan.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
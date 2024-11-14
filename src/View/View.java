package View;

import java.util.Scanner;

public class View {
    public void displayMainMenu() {
        Scanner scan = new Scanner(System.in);
        CreatePatientMenu createPatientMenu = new CreatePatientMenu();
        CreateMedicationMenu createMedicationMenu = new CreateMedicationMenu();
        
        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1. Create Patient");
            System.out.println("2. Add Medication");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scan.nextInt();
            scan.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createPatientMenu.createPatientMenu();
                    break;
                case 2:
                    createMedicationMenu.createMedicationMenu();
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

    // private void createMedicationMenu(Scanner scan) {
    //     System.out.println("Enter medication name: ");
    //     String name = scan.nextLine();
    //     System.out.println("Enter medication dosage: ");
    //     String dosage = scan.nextLine();
    //     System.out.println("Enter medication manufacturer: ");
    //     String manufacturer = scan.nextLine();
    //     System.out.println("Medication created successfully.");
    // }
}
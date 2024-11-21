package View;

import java.util.Scanner;

import Model.Patient;
import View.Create.CreateDeviceMenu;
import View.List.ListPatientDevices;

public class MonitoringView {

    public void displayMonitoringMenu(Patient patient, String patientName, Scanner scan) {

        while (true) {
            System.out.println("\nMonitoring Menu:");
            System.out.println("1. Access Devices");
            System.out.println("2. Access Alerts");
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
                        devicesAccessed(patient, patientName, scan);
                        break;
                    case 2:
                        alertsAccessed(patient, patientName, scan);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scan.next(); // Consume the invalid input
            }
        }
    }

    public void devicesAccessed(Patient patient, String patientName, Scanner scan) {
        CreateDeviceMenu createDeviceMenu = new CreateDeviceMenu();
        ListPatientDevices listPatientDevices = new ListPatientDevices();

        while (true) {
            System.out.println("\nDevices menu:");
            System.out.println("1. Access device menu");
            System.out.println("2. Create device menu"); // done
            System.out.println("3. List active devices"); //done
            System.out.println("4. List inactive devices"); //done
            System.out.println("5. Activate device");
            System.out.println("6. Disable device");
            System.out.println("7. Delete device");
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
                        break;
                    case 2:
                        createDeviceMenu.createDeviceMenu(patientName);
                        break;
                    case 3:
                        listPatientDevices.listActiveDevicesByPatientName(patientName);
                        break;
                    case 4:
                        listPatientDevices.listInactiveDevicesByPatientName(patientName);
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
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

    public void alertsAccessed(Patient patient, String patientName, Scanner scan) {

        while (true) {
            System.out.println("\nAlerts Menu:");
            System.out.println("1. Create alert");
            System.out.println("2. View alerts");
            System.out.println("3. Close alerts");
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
                        break;
                    case 2:
                        break;
                    case 3:
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

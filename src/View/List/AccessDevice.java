package View.List;

import java.util.Scanner;

import Model.Device;

public class AccessDevice {
    Scanner scan = new Scanner(System.in);

    public void displayAccessDeviceMenu(Device device) {
        System.out.println("\nDevice:" + device.getType());
        System.out.println("Status: " + device.isActive());

        System.out.println("1. View device details");
        System.out.println("2. Monitoring Alerts");
        System.out.println("3. Reference Values");
        System.out.println("0. Exit");

        if (scan.hasNextInt()) {
            int choice = scan.nextInt();
            scan.nextLine(); // Consume '\n'

            switch (choice) {
                // case 1:
                //     viewDeviceDetails();
                //     break;
                // case 2:
                //     monitoringAlerts();
                //     break;
                // case 3:
                //     referenceValues();
                //     break;
                case 0:
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

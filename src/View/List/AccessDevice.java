package View.List;

import java.util.Scanner;

import Model.Device;
import View.Update.UpdateDeviceValue;

public class AccessDevice {
    Scanner scan = new Scanner(System.in);
    UpdateDeviceValue updateDeviceValue = new UpdateDeviceValue();

    public void displayAccessDeviceMenu(Device device, String patientName, int deviceId) {
        System.out.println("\nDEVICE:" + device.getType());
        System.out.println("STATUS: " + device.isActive());

        System.out.println("\n1. View device details");
        System.out.println("2. Monitoring Alerts");
        System.out.println("3. Reference Values");
        System.out.println("4. Reference Values (For test: Random)");
        System.out.println("0. Exit");

        if (scan.hasNextInt()) {
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1:
                    viewDeviceDetails(device);
                    break;
                case 2:
                    // monitoringAlerts();
                    break;
                case 3:
                    updateDeviceValue.updateDeviceValue(patientName, deviceId);
                    break;
                case 4:
                    updateDeviceValue.updateDeviceValueRandom(patientName, deviceId);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } else {
            System.out.println("Invalid input. Please enter a number.");
            scan.nextLine(); // Consume invalid input
        }
    }

    public void viewDeviceDetails(Device device) {
        System.out.println("\nDevice details:");
        System.out.println("Device ID: " + device.getId());
        System.out.println("Device Type: " + device.getType());
        System.out.println("Device brand: " + device.getBrand());
        System.out.println("Device model: " + device.getModel());
        System.out.println("Device Status: " + device.isActive());
        System.out.println("Device Value: " + device.getValue());
        System.out.println("__________________________________________________");

        System.out.println("\nPress enter to continue...");
        scan.nextLine();
    }
}

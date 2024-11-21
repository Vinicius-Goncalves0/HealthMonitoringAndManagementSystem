package View;

import java.sql.SQLException;
import java.util.Scanner;

import Controller.db_Connections.DeviceDAO;
import Model.Device;
import Model.Patient;
import View.Create.CreateDeviceMenu;
import View.Delete.DeleteDevice;
import View.List.AccessDevice;
import View.List.ListPatientDevices;
import View.Update.UpdateDeviceStatus;

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
        DeleteDevice deleteDevice = new DeleteDevice();
        UpdateDeviceStatus updateDeviceStatus = new UpdateDeviceStatus();
        AccessDevice accessDevice = new AccessDevice();
        DeviceDAO deviceDAO = new DeviceDAO();

        while (true) {
            System.out.println("\nDevices menu:");
            System.out.println("1. Access device menu");
            System.out.println("2. Create device menu");
            System.out.println("3. List active devices");
            System.out.println("4. List inactive devices");
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
                        System.out.println("\nAccessing device menu...");
                        System.out.println("Enter the device ID: ");
                        int deviceId = scan.nextInt();

                        try {
                            Device device = deviceDAO.accessPatientDevice(patientName, deviceId);
                            if (device != null) {
                                accessDevice.displayAccessDeviceMenu(device);
                            } else {
                                System.out.println("\n--- Device not find ---\n");
                            }
                        } catch (SQLException e) {
                            System.out.println("\n--- Error accessing the device" + e.getMessage()
                                    + " ---\n");
                        }
                        break;
                    case 2:
                        System.out.println("\nCreating device...");
                        createDeviceMenu.createDeviceMenu(patientName);
                        break;
                    case 3:
                        System.out.println("\nListing active devices...");
                        listPatientDevices.listActiveDevicesByPatientName(patientName);
                        break;
                    case 4:
                        System.out.println("\nListing inactive devices...");
                        listPatientDevices.listInactiveDevicesByPatientName(patientName);
                        break;
                    case 5:
                        System.out.println("\nActivating device...");
                        updateDeviceStatus.updateDeviceStatusToActive(patientName);
                        break;
                    case 6:
                        System.out.println("\nDisabling device...");
                        updateDeviceStatus.updateDeviceStatusToInactive(patientName);
                        break;
                    case 7:
                        System.out.println("\nDeleting device...");
                        deleteDevice.deleteDevice(patientName);
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

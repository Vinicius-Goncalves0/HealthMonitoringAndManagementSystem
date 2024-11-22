package View;

import java.sql.SQLException;
import java.util.Scanner;

import Controller.db_Connections.DeviceDAO;
import Model.Device;
import Model.Patient;
import View.Create.CreateAlert;
import View.Create.CreateDeviceMenu;
import View.Delete.DeleteDevice;
import View.List.AccessDevice;
import View.List.ListAllAlerts;
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
        DeleteDevice deleteDevice = new DeleteDevice();
        AccessDevice accessDevice = new AccessDevice();
        DeviceDAO deviceDAO = new DeviceDAO();

        while (true) {
            System.out.println("\nDevices menu:");
            System.out.println("1. Access Device Menu");
            System.out.println("2. Create Device Menu");
            System.out.println("3. List Devices Menu");
            System.out.println("4. Activate/Disable Device Menu");
            System.out.println("5. Delete device");
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
                                accessDevice.displayAccessDeviceMenu(device, patientName, deviceId);
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
                        listDevice(patient, patientName, scan);
                        break;
                    case 4:
                        activateDisableDevice(patient, patientName, scan);
                        break;
                    case 5:
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

    public void listDevice(Patient patient, String patientName, Scanner scan) {
        ListPatientDevices listPatientDevices = new ListPatientDevices();

        while (true) {
            System.out.println("\nList Devices menu:");
            System.out.println("1. List active devices");
            System.out.println("2. List inactive devices");
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
                        System.out.println("\nListing active devices...");
                        listPatientDevices.listActiveDevicesByPatientName(patientName);
                        break;
                    case 2:
                        System.out.println("\nListing inactive devices...");
                        listPatientDevices.listInactiveDevicesByPatientName(patientName);
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

    public void activateDisableDevice(Patient patient, String patientName, Scanner scan) {
        UpdateDeviceStatus updateDeviceStatus = new UpdateDeviceStatus();

        while (true) {
            System.out.println("\nActivate/Disable Device menu:");
            System.out.println("1. Activate device");
            System.out.println("2. Disable device");
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
                        System.out.println("\nActivating device...");
                        updateDeviceStatus.updateDeviceStatusToActive(patientName);
                        break;
                    case 2:
                        System.out.println("\nDisabling device...");
                        updateDeviceStatus.updateDeviceStatusToInactive(patientName);
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
        CreateAlert createAlert = new CreateAlert();
        ListAllAlerts listAllAlerts = new ListAllAlerts();

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
                        System.out.println("\nCreating alert...");
                        System.out.print("Digite o ID do dispositivo a ser criado o alerta: ");
                        int deviceId = scan.nextInt();
                        createAlert.createAlert(patientName, deviceId);
                        break;
                    case 2:
                        System.out.println("\nViewing alerts...");
                        listAllAlerts.displayPatientAlerts();
                        break;
                    case 3:
                        // close alerts
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

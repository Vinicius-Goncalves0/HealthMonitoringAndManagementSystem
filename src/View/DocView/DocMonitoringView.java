package View.DocView;

import java.sql.SQLException;
import java.util.Scanner;

import Controller.db_Connections.DeviceDAO;
import Model.Device;
import Model.Patient;
import View.Create.CreateAlert;
import View.Create.CreateDeviceMenu;
import View.Delete.DeleteAlert;
import View.Delete.DeleteDevice;
import View.List.AccessDevice;
import View.List.ListAllAlerts;
import View.List.ListDevicesPatientAndDetails;
import View.Update.UpdateDeviceStatus;

public class DocMonitoringView {

    public void displayMonitoringMenu(Patient patient, String patientName, Scanner scan) {

        while (true) {
            System.out.print("\n=== Monitoring ===\n");
            System.out.println("1. Access Devices");
            System.out.println("2. Access Alerts");
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
                        devicesAccessed(patient, patientName, scan);
                        break;
                    case 2:
                        alertsAccessed(patient, patientName, scan);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scan.next();
            }
        }
    }

    public void devicesAccessed(Patient patient, String patientName, Scanner scan) {

        CreateDeviceMenu createDeviceMenu = new CreateDeviceMenu();
        DeleteDevice deleteDevice = new DeleteDevice();
        AccessDevice accessDevice = new AccessDevice();
        DeviceDAO deviceDAO = new DeviceDAO();

        while (true) {
            System.out.print("\n=== Devices ===\n");
            System.out.println("1. Access Device Menu");
            System.out.println("2. Create Device Menu");
            System.out.println("3. List Devices Menu");
            System.out.println("4. Activate/Disable Device Menu");
            System.out.println("5. Delete device");
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
                        System.out.println("\nEnter the device ID: ");
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
                        createDeviceMenu.createDeviceMenu(patientName);
                        break;
                    case 3:
                        listDevice(patient, patientName, scan);
                        break;
                    case 4:
                        activateDisableDevice(patient, patientName, scan);
                        break;
                    case 5:
                        deleteDevice.deleteDevice(patientName);
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

    public void listDevice(Patient patient, String patientName, Scanner scan) {
        ListDevicesPatientAndDetails listPatientDevices = new ListDevicesPatientAndDetails();

        while (true) {
            System.out.print("\n=== List Devices ===\n");
            System.out.println("1. List active devices");
            System.out.println("2. List inactive devices");
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
                        listPatientDevices.listActiveDevicesByPatientName(patientName);
                        break;
                    case 2:
                        listPatientDevices.listInactiveDevicesByPatientName(patientName);
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

    public void activateDisableDevice(Patient patient, String patientName, Scanner scan) {
        UpdateDeviceStatus updateDeviceStatus = new UpdateDeviceStatus();

        while (true) {
            System.out.print("\n=== Device ===\n");
            System.out.println("1. Activate device");
            System.out.println("2. Disable device");
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
                        updateDeviceStatus.updateDeviceStatusToActive(patientName);
                        break;
                    case 2:
                        updateDeviceStatus.updateDeviceStatusToInactive(patientName);
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

    public void alertsAccessed(Patient patient, String patientName, Scanner scan) {
        CreateAlert createAlert = new CreateAlert();
        ListAllAlerts listAllAlerts = new ListAllAlerts();
        DeleteAlert deleteAlert = new DeleteAlert();
        DeviceDAO deviceDAO = new DeviceDAO();

        while (true) {
            System.out.print("\n=== Alerts Menu ===\n");
            System.out.println("1. Create alert");
            System.out.println("2. View alerts");
            System.out.println("3. Close alerts");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");

            if (scan.hasNextInt()) {
                int choice = scan.nextInt();
                scan.nextLine();

                System.out.print("\n");
                switch (choice) {
                    case 0:
                        System.out.println("Exiting...");
                        return;
                    case 1:
                        System.out.print("\nEnter the ID of the device to create the alert for: ");
                        int deviceId = scan.nextInt();
                        try {
                            if (!deviceDAO.isDeviceOwnedByPatient(patientName, deviceId)) {
                                System.out.println("Device does not belong to the patient!");
                                break;
                            }
                            createAlert.createAlert(patientName, deviceId);
                        } catch (SQLException e) {
                            System.out.println("\n--- Error checking device ownership: " + e.getMessage() + " ---\n");
                        }
                        break;
                    case 2:
                        listAllAlerts.listAlertsByPatientId(patient.getId());
                        break;
                    case 3:
                        deleteAlert.deleteAlert(patientName);
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

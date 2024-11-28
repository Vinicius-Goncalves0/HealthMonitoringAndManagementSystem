package View.List;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import Controller.DeviceController;
import Model.Device;

public class ListDevicesPatientAndDetails {
    Scanner scan = new Scanner(System.in);
          
    public void displayDevices(List<Device> devices) {
        if (devices.isEmpty()) {
            System.out.println("No devices found for the given patient.");
        } else {
            System.out.println("\n === Devices ===");
            for (Device device : devices) {
                System.out.println("\n|| ID: " + device.getId());
                System.out.println("|| Type: " + device.getType());
                System.out.println("|| Brand: " + device.getBrand());
                System.out.println("|| Model: " + device.getModel());
                System.out.println("|| Status: " + device.isActive());
                System.out.println("|| Value: " + device.getValue());
                System.out.println("|| Max Value: " + device.getAlertValueMax() + " and Min Value: " + device.getAlertValueMin());
            }
        }
    }

    public void listActiveDevicesByPatientName(String patientName) {
        ListDevicesPatientAndDetails listPatientDevices = new ListDevicesPatientAndDetails();
        DeviceController deviceController = new DeviceController();

        try {
            List<Device> devices = deviceController.listActiveDevicesByPatientName(patientName);
            listPatientDevices.displayDevices(devices);
            System.out.println("\n Press enter to continue...");
            scan.nextLine();
        } catch (SQLException e) {
            System.out.println("\n--- Error when listing devices: " + e.getMessage() + " ---\n");
        }
    }

    public void listInactiveDevicesByPatientName(String patientName) {
        ListDevicesPatientAndDetails listPatientDevices = new ListDevicesPatientAndDetails();
        DeviceController deviceController = new DeviceController();

        try {
            List<Device> devices = deviceController.listInactiveDevicesByPatientName(patientName);
            listPatientDevices.displayDevices(devices);
            System.out.println("\n Press enter to continue...");
            scan.nextLine();
        } catch (SQLException e) {
            System.out.println("\n--- Error when listing devices: " + e.getMessage() + " ---\n");
        }
    }

    public void listDeviceByID(int deviceId) {
        DeviceController deviceController = new DeviceController();
        ListDevicesPatientAndDetails listDevicesPatientAndDetails = new ListDevicesPatientAndDetails();

        try {
            List<Device> devices = deviceController.listDeviceById(deviceId);
            listDevicesPatientAndDetails.displayDevices(devices);
            System.out.println("\n Press enter to continue...");
            scan.nextLine();
        } catch (SQLException e) {
            System.out.println("\n--- Error when listing device: " + e.getMessage() + " ---\n");
        }
    }
}

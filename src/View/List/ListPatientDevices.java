package View.List;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import Controller.DeviceController;
import Model.Device;

public class ListPatientDevices {
    Scanner scan = new Scanner(System.in);
          
    public void displayDevices(List<Device> devices) {

        if (devices.isEmpty()) {
            System.out.println("No devices found for the given patient.");
        } else {
            for (Device device : devices) {
                System.out.println("\nDevice ID: " + device.getId());
                System.out.println("Type: " + device.getType());
                System.out.println("Brand: " + device.getBrand());
                System.out.println("Model: " + device.getModel());
                System.out.println("Status: " + device.isActive());
                System.out.println("Device Value: " + device.getValue());
                System.out.println("Alert Value Max: " + device.getAlertValueMax());
                System.out.println("Alert Value Min: " + device.getAlertValueMin());
                System.out.println("_____________________________________________________________________________________");
            }
        }
    }

    public void listActiveDevicesByPatientName(String patientName) {
        ListPatientDevices listPatientDevices = new ListPatientDevices();
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
        ListPatientDevices listPatientDevices = new ListPatientDevices();
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
}

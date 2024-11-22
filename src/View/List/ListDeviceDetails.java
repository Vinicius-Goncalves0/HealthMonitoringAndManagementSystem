package View.List;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import Controller.DeviceController;
import Model.Device;

public class ListDeviceDetails {
    Scanner scan = new Scanner(System.in);

    public void displayDevice(List<Device> devices) {
        if (devices.isEmpty()) {
            System.out.println("No doctors found with the given name.");
        } else {
            for (Device device : devices) {
                System.out.println("\nDevice details:");
                System.out.println("Device ID: " + device.getId());
                System.out.println("Device Type: " + device.getType());
                System.out.println("Device brand: " + device.getBrand());
                System.out.println("Device model: " + device.getModel());
                System.out.println("Device Status: " + device.isActive());
                System.out.println("Device Value: " + device.getValue());
                System.out.println("__________________________________________________");
            }
        }
    }

    public void listDeviceByID(int deviceId) {
        DeviceController deviceController = new DeviceController();
        ListDeviceDetails listDeviceDetails = new ListDeviceDetails();

        try {
            List<Device> devices = deviceController.listDeviceById(deviceId);
            listDeviceDetails.displayDevice(devices);
            System.out.println("\n Press enter to continue...");
            scan.nextLine();
        } catch (SQLException e) {
            System.out.println("\n--- Error when listing device: " + e.getMessage() + " ---\n");
        }
    }
}

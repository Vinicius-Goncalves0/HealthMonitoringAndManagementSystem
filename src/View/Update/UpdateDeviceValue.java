package View.Update;

import java.sql.SQLException;
import java.util.Scanner;

import Controller.DeviceController;
import java.util.Random;

public class UpdateDeviceValue {
    Scanner scan = new Scanner(System.in);
    private DeviceController deviceController;

    public UpdateDeviceValue() {
        this.deviceController = new DeviceController();
    }

    public void updateDeviceValue(String patientName, int deviceId) {
        System.out.println("\nEnter the new value: ");
        String value = scan.nextLine();

        try {
            deviceController.updateDeviceValue(patientName, deviceId, value);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("\nFailed to update device value due to a database error.");
        }
    }

    public void updateDeviceValueRandom(String patientName, int deviceId) {
        System.out.println("\n Generating a random value...");
        Random random = new Random();
        String value = random.nextInt(100) < 70 ? "No Warning" : "Warning";
        System.out.println("Generated value: " + value);

        try {
            deviceController.updateDeviceValue(patientName, deviceId, value);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("\nFailed to update device value due to a database error.");
        }
    }
}

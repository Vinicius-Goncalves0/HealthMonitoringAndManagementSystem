package View.List;

import java.util.Scanner;

import Model.Device;
import View.Create.CreateAlert;
import View.Update.UpdateDeviceValue;

public class AccessDevice {
    Scanner scan = new Scanner(System.in);
    UpdateDeviceValue updateDeviceValue = new UpdateDeviceValue();
    CreateAlert createAlert = new CreateAlert();
    ListDeviceDetails listDeviceDetails = new ListDeviceDetails();

    public void displayAccessDeviceMenu(Device device, String patientName, int deviceId) {

        while (true) {
            System.out.println("\nDEVICE:" + device.getType());
            String status = device.isActive() == true ? "Active" : "Disabled";
            System.out.println("STATUS: " + status);

            System.out.println("\n1. View device details");
            System.out.println("2. Monitoring Alerts");
            System.out.println("3. Create Alerts");
            System.out.println("4. Reference Values");
            System.out.println("5. Reference Values (For test: Random)");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            if (scan.hasNextInt()) {
                int choice = scan.nextInt();
                scan.nextLine();

                switch (choice) {
                    case 1:
                        listDeviceDetails.listDeviceByID(deviceId);
                        break;
                    case 2:
                        // mostrar alertas do dispositivo
                        break;
                    case 3:
                        createAlert.createAlert(patientName, deviceId);
                        break;
                    case 4:
                        updateDeviceValue.updateDeviceValue(patientName, deviceId);
                        break;
                    case 5:
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
    }
}

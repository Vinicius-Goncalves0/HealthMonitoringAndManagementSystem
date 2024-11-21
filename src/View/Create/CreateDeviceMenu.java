package View.Create;

import java.sql.SQLException;
import java.util.Scanner;
import Controller.PatientController;
import Controller.DeviceController;
import Model.Patient;
import Model.Device;

public class CreateDeviceMenu {
    Scanner scan = new Scanner(System.in);

    private DeviceController deviceController;
    private PatientController patientController;

    public CreateDeviceMenu() {
        this.deviceController = new DeviceController();
        this.patientController = new PatientController();
    }

    public void createDeviceMenu(String patientName) {
        System.out.println("Enter device type: ");
        String type = scan.nextLine();
        System.out.println("Enter device brand: ");
        String brand = scan.nextLine();
        System.out.println("Enter device model: ");
        String model = scan.nextLine();
        System.out.print("Is the device active? (true/false): ");
        boolean isActive = scan.nextBoolean();
        scan.nextLine(); // Consume newline

        try {
            Patient patient = patientController.findPatientByName(patientName);

            if (patient != null) {
                Device device = new Device(type, brand, model, isActive);
                deviceController.addDeviceToPatient(device, patient);

                System.out.println("Device added successfully.");
            } else {
                System.out.println("Patient not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding device patient: " + e.getMessage());
        }
    }
}

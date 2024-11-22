package View.Create;

import java.sql.SQLException;
import java.util.Scanner;

import Controller.AlertController;
import Controller.PatientController;
import Controller.DeviceController;
import Model.Alert;
import Model.Appointment;
import Model.Device;
import Model.Doctor;
import Model.Medication;
import Model.Patient;
import Controller.MedicationController;
import Controller.AppointmentController;
import Controller.db_Connections.DoctorDAO;
import View.List.ListPatientAppointmentMenu;

public class CreateAlert {
    PatientController patientController = new PatientController();
    DeviceController deviceController = new DeviceController();

    private MedicationController medicationController;
    private AppointmentController appointmentController;
    DoctorDAO doctorDAO = new DoctorDAO();
    ListPatientAppointmentMenu listPatientAppointmentMenu = new ListPatientAppointmentMenu();

    private AlertController alertController;

    public CreateAlert() {
        this.alertController = new AlertController();
    }

    public void createAlert(String patientName, int deviceId) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nCriar alerta");
        System.out.print("Digite o tipo de alerta: ");
        String type = scanner.nextLine();
        System.out.print("Digite a mensagem do alerta: ");
        String message = scanner.nextLine();
        System.out.print("Digite o nome do médico: ");
        String doctorAlert = scanner.nextLine();
        System.out.print("Digite a data do alerta: ");
        String data = scanner.nextLine();

        // Alert alert = new Alert(type, message, doctor, data);
        // try {
        // Device device = deviceController.findDeviceByID(deviceId);
        // Patient patient = patientController.findPatientByName(patientName);

        // alertController.gerarAlerta(alert, device, patient);
        // System.out.println("Alerta criado com sucesso!");
        // } catch (SQLException e) {
        // System.out.println("Erro ao criar alerta: " + e.getMessage());
        // }

        try {
            Doctor doctor = doctorDAO.findDoctorByName(doctorAlert);
            if (doctor != null) {

                try {
                    Patient patient = patientController.findPatientByName(patientName);
                    Device device = deviceController.findDeviceByID(deviceId);

                    if (patient != null) {
                        Alert alert = new Alert(type, message, doctorAlert, data);
                        alertController.gerarAlerta(alert, device, patient);

                        System.out.println("Alert generate successfully.");
                    } else {
                        System.out.println("Patient not found.");
                    }
                } catch (SQLException e) {
                    System.out.println("Error generating alert: " + e.getMessage());
                }

            } else {
                System.out.println("\n--- Doctor " + doctorAlert + " not found in system ---\n");
            }
        } catch (SQLException e) {
            System.out.println("\n--- Error accessing the doctor: " + doctorAlert + " " + e.getMessage() + " ---\n");
        }
    }
}

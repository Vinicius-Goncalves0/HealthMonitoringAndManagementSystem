package View.Create;

import java.sql.SQLException;
import java.util.Scanner;

import Controller.AlertController;
import Controller.PatientController;
import Controller.DeviceController;
import Model.Alert;
import Model.Device;
import Model.Patient;

public class CreateAlert {
    PatientController patientController = new PatientController();
    DeviceController deviceController = new DeviceController();

    private AlertController alertController;

    public CreateAlert() {
        this.alertController = new AlertController();
    }

    public void createAlert(String patientName) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Criar alerta");
        System.out.print("Digite o tipo de alerta: ");
        String type = scanner.nextLine();
        System.out.print("Digite a mensagem do alerta: ");
        String message = scanner.nextLine();
        System.out.print("Digite o nome do médico: ");
        String doctor = scanner.nextLine();
        System.out.print("Digite a data do alerta: ");
        String data = scanner.nextLine();
        System.out.print("Digite o ID do dispositivo: ");
        int deviceId = scanner.nextInt();

        Alert alert = new Alert(type, message, doctor, data);
        try {
            Device device = deviceController.findDeviceByID(deviceId);
            Patient patient = patientController.findPatientByName(patientName);

            alertController.gerarAlerta(alert, device, patient);
            System.out.println("Alerta criado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar alerta: " + e.getMessage());
        }

        scanner.close();
    }
}

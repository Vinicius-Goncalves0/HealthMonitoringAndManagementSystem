package Controller;

import java.sql.SQLException;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Controller.db_Connections.DeviceDAO;
import Controller.db_Connections.PatientDAO;
import Controller.db_Connections.AlertDAO;
import Model.Device;
import Model.Patient;
import Model.Alert;

public class Monitoring {

    private DeviceDAO deviceDAO;
    private PatientDAO patientDAO;
    private AlertDAO alertDAO;

    public Monitoring() {
        this.deviceDAO = new DeviceDAO();
        this.patientDAO = new PatientDAO();
        this.alertDAO = new AlertDAO();
    }

    // Generates an alert based on the value of the device
    public void generatePatientAlert() {

        try {
            List<Device> activeDevices = deviceDAO.listActiveDevices();

            for (Device device : activeDevices) {
                int value = device.getValue();
                int alertValueMax = device.getAlertValueMax();
                int alertValueMin = device.getAlertValueMin();

                if ((value > alertValueMax || value < alertValueMin) && (value != 0)) {
                    int patientId = deviceDAO.getPatientIdByDeviceId(device.getId());
                    Patient patient = patientDAO.findPatientById(patientId);

                    String mensagem = String.format(
                            "Patient %s, has a record of %d which is %s of the recommended %s of %d.",
                            patient.getName(), value,
                            (value > alertValueMax ? "above" : "below"),
                            (value > alertValueMax ? "maximum value" : "minimum value"),
                            (value > alertValueMax ? alertValueMax : alertValueMin));

                    if (!alertDAO.alertExists(mensagem, device.getType())) {
                        LocalDateTime now = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        String formattedDate = now.format(formatter);

                        Alert alert = new Alert(device.getType(), mensagem, "Automatically generated alert", formattedDate);
                        alertDAO.generateAlert(alert, device, patient);

                        System.out.println("Alert generated: " + mensagem);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error checking active devices: " + e.getMessage());
        }
    }
}

package Controller;

import java.sql.SQLException;
import java.util.List;

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

    public void gerarAlertByDeviceValue() {
        try {
            List<Device> dispositivosAtivos = deviceDAO.listarDispositivosAtivos();

            for (Device device : dispositivosAtivos) {
                int value = device.getValue();
                int alertValueMax = device.getAlertValueMax();
                int alertValueMin = device.getAlertValueMin();

                if ((value > alertValueMax || value < alertValueMin) && (value != 0)) {
                    int patientId = deviceDAO.getPatientIdByDeviceId(device.getId());
                    Patient patient = patientDAO.findPatientById(patientId);

                    String mensagem = String.format(
                            "Paciente %s, está com um registro de %d que está %s do %s recomendado de %d.",
                            patient.getName(), value,
                            (value > alertValueMax ? "acima" : "abaixo"),
                            (value > alertValueMax ? "valor máximo" : "valor mínimo"),
                            (value > alertValueMax ? alertValueMax : alertValueMin));

                    Alert alert = new Alert("Alerta de Dispositivo", mensagem, "Sistema", "Data Atual");
                    alertDAO.gerarAlerta(alert, device, patient);

                    System.out.println("Alerta gerado: " + mensagem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao verificar dispositivos ativos: " + e.getMessage());
        }
    }

}

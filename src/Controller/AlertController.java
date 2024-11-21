package Controller;

import java.sql.SQLException;
import java.util.List;

import Controller.db_Connections.AlertDAO;
import Model.Alert;
import Model.Device;
import Model.Patient;

public class AlertController {

    private AlertDAO alertDAO;

    public AlertController() {
        this.alertDAO = new AlertDAO();
    }

    public void gerarAlerta(Alert alert, Device device, Patient patient)  throws SQLException {
        alertDAO.gerarAlerta(alert, device, patient);
    }

    public void encerrarAlerta(int alertId) throws SQLException {
        alertDAO.encerrarAlerta(alertId);
    }

    public List<Alert> listarAlertas() throws SQLException {
        return alertDAO.listarAlertas();
    }

}

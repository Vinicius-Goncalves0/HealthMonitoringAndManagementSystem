package Controller.db_Connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Alert;
import Model.Device;
import Model.Patient;

public class AlertDAO {

    // gerar alerta
    public void gerarAlerta(Alert alert, Device device, Patient patient) throws SQLException {
        String insertAlertSQL = "INSERT INTO alerts (type, message, doctor, data) VALUES (?, ?, ?, ?)";
        String insertDeviceAlertSQL = "INSERT INTO device_alerts (device_id, alert_id) VALUES (?, ?)";
        String insertPatientAlertSQL = "INSERT INTO patient_alerts (patient_id, alert_id) VALUES (?, ?)";

        try (Connection conn = db_Connection.getConnection()) {
            // Disables auto commit for manual transaction control
            conn.setAutoCommit(false);

            try (PreparedStatement alertStmt = conn.prepareStatement(insertAlertSQL,
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                alertStmt.setString(1, alert.getType());
                alertStmt.setString(2, alert.getMessage());
                alertStmt.setString(3, alert.getDoctor());
                alertStmt.setString(4, alert.getData());
                alertStmt.executeUpdate();

                // Pega o ID gerado automaticamente para o medicamento
                ResultSet generatedKeys = alertStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    alert.setId(generatedKeys.getInt(1));
                }
            }

            // Cria a relação entre device e alerta
            try (PreparedStatement relationStmt = conn.prepareStatement(insertDeviceAlertSQL)) {
                relationStmt.setInt(1, device.getId());
                relationStmt.setInt(2, alert.getId());

                relationStmt.executeUpdate();
            }

            // Cria a relação entre paciente e alert
            try (PreparedStatement relationStmt = conn.prepareStatement(insertPatientAlertSQL)) {
                relationStmt.setInt(1, patient.getId());
                relationStmt.setInt(2, alert.getId());

                relationStmt.executeUpdate();
            }

            // Confirma a transação
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // encerrar alerta
    public void encerrarAlerta(int alertId) throws SQLException {
        String deleteDeviceAlertSQL = "DELETE FROM device_alerts WHERE alert_id = ?";
        String deletePatientAlertSQL = "DELETE FROM patient_alerts WHERE alert_id = ?";
        String deleteAlertSQL = "DELETE FROM alerts WHERE id = ?";

        try (Connection conn = db_Connection.getConnection()) {
            // Disables auto commit for manual transaction control
            conn.setAutoCommit(false);

            try (PreparedStatement deviceAlertStmt = conn.prepareStatement(deleteDeviceAlertSQL);
                    PreparedStatement patientAlertStmt = conn.prepareStatement(deletePatientAlertSQL);
                    PreparedStatement alertStmt = conn.prepareStatement(deleteAlertSQL)) {

                // Remove associations with device
                deviceAlertStmt.setInt(1, alertId);
                deviceAlertStmt.executeUpdate();

                // Remove associations with patient
                patientAlertStmt.setInt(1, alertId);
                patientAlertStmt.executeUpdate();

                // Remove alert
                alertStmt.setInt(1, alertId);
                alertStmt.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        }

    }

    // visualizar alertas
    public List<Alert> listarAlertas() throws SQLException {
        String selectAlertsSQL = "SELECT * FROM alerts";
        List<Alert> alertas = new ArrayList<>();

        try (Connection conn = db_Connection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(selectAlertsSQL);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String type = rs.getString("type");
                String message = rs.getString("message");
                String doctor = rs.getString("doctor");
                String data = rs.getString("data");

                Alert alert = new Alert(id, type, message, doctor, data);
                alertas.add(alert);
            }
        }

        return alertas;
    }

    // get patient ID by alert ID
    public int getPatientIdByAlertId(int alertId) throws SQLException {
        String sql = "SELECT patient_id FROM patient_alerts WHERE alert_id = ?";
        int patientId = 0;

        try (Connection conn = db_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, alertId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    patientId = rs.getInt("patient_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error finding patient ID: " + e.getMessage());
        }

        return patientId;
    }

    // list alerts by patient ID
    public List<Alert> listAlertsByPatientId(int patientId) throws SQLException {
        String sql = "SELECT a.* FROM alerts a " +
                     "JOIN patient_alerts pa ON a.id = pa.alert_id " +
                     "WHERE pa.patient_id = ?";
        List<Alert> alertas = new ArrayList<>();
    
        try (Connection conn = db_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String type = rs.getString("type");
                    String message = rs.getString("message");
                    String doctor = rs.getString("doctor");
                    String data = rs.getString("data");
    
                    Alert alert = new Alert(id, type, message, doctor, data);
                    alertas.add(alert);
                }
            }
        }
    
        return alertas;
    }
}

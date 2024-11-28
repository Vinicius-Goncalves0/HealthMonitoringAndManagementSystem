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

    // Generate Alert
    public void generateAlert(Alert alert, Device device, Patient patient) throws SQLException {
        String insertAlertSQL = "INSERT INTO alerts (type, message, doctor, data) VALUES (?, ?, ?, ?)";
        String insertDeviceAlertSQL = "INSERT INTO device_alerts (device_id, alert_id) VALUES (?, ?)";
        String insertPatientAlertSQL = "INSERT INTO patient_alerts (patient_id, alert_id) VALUES (?, ?)";

        try (Connection conn = db_Connection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement alertStmt = conn.prepareStatement(insertAlertSQL,
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                alertStmt.setString(1, alert.getType());
                alertStmt.setString(2, alert.getMessage());
                alertStmt.setString(3, alert.getDoctor());
                alertStmt.setString(4, alert.getDate());
                alertStmt.executeUpdate();

                ResultSet generatedKeys = alertStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    alert.setId(generatedKeys.getInt(1));
                }
            }

            try (PreparedStatement relationStmt = conn.prepareStatement(insertDeviceAlertSQL)) {
                relationStmt.setInt(1, device.getId());
                relationStmt.setInt(2, alert.getId());

                relationStmt.executeUpdate();
            }

            try (PreparedStatement relationStmt = conn.prepareStatement(insertPatientAlertSQL)) {
                relationStmt.setInt(1, patient.getId());
                relationStmt.setInt(2, alert.getId());

                relationStmt.executeUpdate();
            }

            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Check if a alert belongs to a patient
    public boolean isAlertOwnedByPatient(String patientName, int alertId) throws SQLException {
        String sql = "SELECT COUNT(*) AS count " +
                     "FROM hospital_system.patients p " +
                     "JOIN hospital_system.patient_alerts pd ON p.id = pd.patient_id " +
                     "WHERE p.name = ? AND pd.alert_id = ?";

        try (Connection conn = db_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patientName);
            stmt.setInt(2, alertId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("count") > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error checking alert ownership: " + e.getMessage());
        }

        return false;
    }

    // Delete a patient alert
    public void deletePatientAlert(String patientName, int alertId) throws SQLException {
        if (!isAlertOwnedByPatient(patientName, alertId)) {
            System.out.println("Alert does not belong to the patient!");
            return;
        }

        Connection conn = null;
        PreparedStatement getPatientIdStmt = null;
        PreparedStatement deletePatientAlertStmt = null;
        PreparedStatement deleteAlertStmt = null;
        PreparedStatement deleteDeviceAlertStmt = null;
        ResultSet rs = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = db_Connection.getConnection();

            String getPatientIdSql = "SELECT id FROM patients WHERE name = ?";
            getPatientIdStmt = conn.prepareStatement(getPatientIdSql);
            getPatientIdStmt.setString(1, patientName);
            rs = getPatientIdStmt.executeQuery();

            if (rs.next()) {
                int patientId = rs.getInt("id");

                String deletePatientAlertSql = "DELETE FROM patient_alerts WHERE patient_id = ? AND alert_id = ?";
                deletePatientAlertStmt = conn.prepareStatement(deletePatientAlertSql);
                deletePatientAlertStmt.setInt(1, patientId);
                deletePatientAlertStmt.setInt(2, alertId);
                deletePatientAlertStmt.executeUpdate();

                String deleteAlertSql = "DELETE FROM alerts WHERE id = ?";
                deleteAlertStmt = conn.prepareStatement(deleteAlertSql);
                deleteAlertStmt.setInt(1, alertId);
                deleteAlertStmt.executeUpdate();

                String deleteDeviceAlertSql = "DELETE FROM hospital_system.device_alerts WHERE alert_id = ?";
                deleteDeviceAlertStmt = conn.prepareStatement(deleteDeviceAlertSql);
                deleteDeviceAlertStmt.setInt(1, alertId);
                deleteDeviceAlertStmt.executeUpdate();

                System.out.println("Alert deleted successfully for patient!");
            } else {
                System.out.println("Patient not found!");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error deleting alert for patient: " + e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (getPatientIdStmt != null) {
                getPatientIdStmt.close();
            }
            if (deletePatientAlertStmt != null) {
                deletePatientAlertStmt.close();
            }
            if (deleteAlertStmt != null) {
                deleteAlertStmt.close();
            }
            if (deleteDeviceAlertStmt != null) {
                deleteDeviceAlertStmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    // Deleting an alert by ID
    public void deleteAlertById(int alertId) throws SQLException {
        String deleteAlertSQL = "DELETE FROM alerts WHERE id = ?";
        String deleteDeviceAlertSQL = "DELETE FROM device_alerts WHERE alert_id = ?";
        String deletePatientAlertSQL = "DELETE FROM patient_alerts WHERE alert_id = ?";

        try (Connection conn = db_Connection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement deleteDeviceAlertStmt = conn.prepareStatement(deleteDeviceAlertSQL)) {
                deleteDeviceAlertStmt.setInt(1, alertId);
                deleteDeviceAlertStmt.executeUpdate();
            }

            try (PreparedStatement deletePatientAlertStmt = conn.prepareStatement(deletePatientAlertSQL)) {
                deletePatientAlertStmt.setInt(1, alertId);
                deletePatientAlertStmt.executeUpdate();
            }

            try (PreparedStatement deleteAlertStmt = conn.prepareStatement(deleteAlertSQL)) {
                deleteAlertStmt.setInt(1, alertId);
                deleteAlertStmt.executeUpdate();
            }

            conn.commit();
            System.out.println("Alert deleted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error deleting alert: " + e.getMessage());
        }
    }

    // List all alerts
    public List<Alert> listAllAlerts() throws SQLException {
        String selectAlertsSQL = "SELECT * FROM alerts";
        List<Alert> alerts = new ArrayList<>();

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
                alerts.add(alert);
            }
        }

        return alerts;
    }

    // Get patient ID by alert ID
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

    // List alerts by patient ID
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

    // Check if alert exists
    public boolean alertExists(String mensagem, String deviceType) throws SQLException {
        String query = "SELECT COUNT(*) FROM alerts WHERE message = ? AND type = ?";
        try (Connection conn = db_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, mensagem);
            stmt.setString(2, deviceType);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}

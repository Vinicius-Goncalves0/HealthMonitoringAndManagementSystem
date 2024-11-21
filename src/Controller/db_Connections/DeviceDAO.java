package Controller.db_Connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Model.Device;
import Model.Patient;

public class DeviceDAO {
    // add medication to an appointment in the database
    public void addDeviceToPatient(Device device, Patient patient) {
        String deviceSql = "INSERT INTO devices (type, brand, model, activationStatus, value) VALUES (?, ?, ?, ?, ?)";
        String deviceToPatientSql = "INSERT INTO patient_devices (patient_id, device_id) VALUES (?, ?)";
        
        try (Connection conn = db_Connection.getConnection()) {
            // Disables auto commit for manual transaction control
            conn.setAutoCommit(false);

            // Save the device
            try (PreparedStatement deviceStmt = conn.prepareStatement(deviceSql,
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                deviceStmt.setString(1, device.getType());
                deviceStmt.setString(2, device.getBrand());
                deviceStmt.setString(3, device.getModel());
                deviceStmt.setBoolean(4, device.isActive());
                deviceStmt.setString(5, device.getValue());

                deviceStmt.executeUpdate();

                // Takes the automatically generated ID for the device
                ResultSet generatedKeys = deviceStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    device.setId(generatedKeys.getInt(1));
                }
            }

            // Create the relationship between patient and device
            try (PreparedStatement relationStmt = conn.prepareStatement(deviceToPatientSql)) {
                relationStmt.setInt(1, patient.getId());
                relationStmt.setInt(2, device.getId());

                relationStmt.executeUpdate();
            }

            // Confirm the transaction
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // List all devices from a patient
    public List<Device> listDevicesByPatientName(String patientName) throws SQLException {
        List<Device> devices = new ArrayList<>();
        String sql = "SELECT d.id, d.type, d.brand, d.model, d.activationStatus, d.value " +
            "FROM hospital_system.patients p " +
            "JOIN hospital_system.patient_devices pd ON p.id = pd.patient_id " +
            "JOIN hospital_system.devices d ON pd.device_id = d.id " +
            "WHERE p.name = ?";

        try (Connection conn = db_Connection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patientName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Device device = new Device(
                            rs.getInt("id"),
                            rs.getString("type"),
                            rs.getString("brand"),
                            rs.getString("model"),
                            rs.getBoolean("activationStatus"),
                            rs.getString("value"));
                    devices.add(device);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error listing devices: " + e.getMessage());
        }

        return devices;
    }

    // New method for listing active devices
    public List<Device> listActiveDevicesByPatientName(String patientName) throws SQLException {
        return listDevicesByPatientName(patientName).stream()
                .filter(Device::isActive)
                .collect(Collectors.toList());
    }

    // New method for listing inactive devices
    public List<Device> listInactiveDevicesByPatientName(String patientName) throws SQLException {
        return listDevicesByPatientName(patientName).stream()
                .filter(device -> !device.isActive())
                .collect(Collectors.toList());
    }

    // Method to check if a device belongs to a patient
    public boolean isDeviceOwnedByPatient(String patientName, int deviceId) throws SQLException {
        String sql = "SELECT COUNT(*) AS count " +
                     "FROM hospital_system.patients p " +
                     "JOIN hospital_system.patient_devices pd ON p.id = pd.patient_id " +
                     "WHERE p.name = ? AND pd.device_id = ?";

        try (Connection conn = db_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patientName);
            stmt.setInt(2, deviceId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("count") > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error checking device ownership: " + e.getMessage());
        }

        return false;
    }

    // Modified delete device from a patient method
    public void deletePatientDevice(String patientName, int deviceId) throws SQLException {
        if (!isDeviceOwnedByPatient(patientName, deviceId)) {
            System.out.println("Device does not belong to the patient!");
            return;
        }

        String getPatientIdSql = "SELECT id FROM hospital_system.patients WHERE name = ?";
        String deletePatientDeviceSql = "DELETE FROM hospital_system.patient_devices WHERE patient_id = ? AND device_id = ?";
        String deleteDeviceSql = "DELETE FROM hospital_system.devices WHERE id = ?";

        Connection conn = null;
        PreparedStatement getPatientIdStmt = null;
        PreparedStatement deletePatientDeviceStmt = null;
        PreparedStatement deleteDeviceStmt = null;
        ResultSet rs = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            conn = db_Connection.getConnection();

            // Get patient ID
            getPatientIdStmt = conn.prepareStatement(getPatientIdSql);
            getPatientIdStmt.setString(1, patientName);
            rs = getPatientIdStmt.executeQuery();

            if (rs.next()) {
                int patientId = rs.getInt("id");

                // Delete device from patient
                deletePatientDeviceStmt = conn.prepareStatement(deletePatientDeviceSql);
                deletePatientDeviceStmt.setInt(1, patientId);
                deletePatientDeviceStmt.setInt(2, deviceId);
                deletePatientDeviceStmt.executeUpdate();

                // Delete device from devices table
                deleteDeviceStmt = conn.prepareStatement(deleteDeviceSql);
                deleteDeviceStmt.setInt(1, deviceId);
                deleteDeviceStmt.executeUpdate();

                System.out.println("Device deleted successfully for patient!");
            } else {
                System.out.println("Patient not found!");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error deleting device for patient: " + e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (getPatientIdStmt != null) {
                getPatientIdStmt.close();
            }
            if (deletePatientDeviceStmt != null) {
                deletePatientDeviceStmt.close();
            }
            if (deleteDeviceStmt != null) {
                deleteDeviceStmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    // Method to activate a device
    public void activateDevice(String patientName, int deviceId) throws SQLException {
        if (!isDeviceOwnedByPatient(patientName, deviceId)) {
            System.out.println("Device does not belong to the patient!");
            return;
        }

        String sql = "UPDATE hospital_system.devices SET activationStatus = TRUE WHERE id = ?";

        try (Connection conn = db_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, deviceId);
            stmt.executeUpdate();
            System.out.println("Device activated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error activating device: " + e.getMessage());
        }
    }

    // Method to deactivate a device
    public void deactivateDevice(String patientName, int deviceId) throws SQLException {
        if (!isDeviceOwnedByPatient(patientName, deviceId)) {
            System.out.println("Device does not belong to the patient!");
            return;
        }

        String sql = "UPDATE hospital_system.devices SET activationStatus = FALSE WHERE id = ?";

        try (Connection conn = db_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, deviceId);
            stmt.executeUpdate();
            System.out.println("Device deactivated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error deactivating device: " + e.getMessage());
        }
    }

    // Method to access the patient device
    public Device accessPatientDevice(String patientName, int deviceId) throws SQLException {
        if (!isDeviceOwnedByPatient(patientName, deviceId)) {
            System.out.println("Device does not belong to the patient!");
            return null;
        }

        String sql = "SELECT id, type, brand, model, activationStatus " +
                     "FROM hospital_system.devices " +
                     "WHERE id = ?";

        try (Connection conn = db_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, deviceId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Device(
                            rs.getInt("id"),
                            rs.getString("type"),
                            rs.getString("brand"),
                            rs.getString("model"),
                            rs.getBoolean("activationStatus"),
                            rs.getString("value"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error accessing device: " + e.getMessage());
        }

        return null;
    }
    
}
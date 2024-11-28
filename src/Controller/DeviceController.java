package Controller;

import java.sql.SQLException;
import java.util.List;

import Model.Device;
import Model.Patient;
import Controller.db_Connections.DeviceDAO;

public class DeviceController {
    private DeviceDAO deviceDAO;

    public DeviceController() {
        this.deviceDAO = new DeviceDAO();
    }

    public void addDeviceToPatient(Device device, Patient patient) throws SQLException {
        deviceDAO.addDeviceToPatient(device, patient);
    }

    public List<Device> listDevicesByPatientName(String patientName) throws SQLException {
        return deviceDAO.listDevicesByPatientName(patientName);
    }

    public List<Device> listActiveDevicesByPatientName(String patientName) throws SQLException {
        return deviceDAO.listActiveDevicesByPatientName(patientName);
    }

    public List<Device> listInactiveDevicesByPatientName(String patientName) throws SQLException {
        return deviceDAO.listInactiveDevicesByPatientName(patientName);
    }

    public void deletePatientDevice(String patientName, int deviceId) throws SQLException {
        deviceDAO.deletePatientDevice(patientName, deviceId);
    }

    public void activateDevice(String patientName, int deviceId) throws SQLException {
        deviceDAO.activateDevice(patientName, deviceId);
    }

    public void deactivateDevice(String patientName, int deviceId) throws SQLException {
        deviceDAO.deactivateDevice(patientName, deviceId);
    }

    public void accessPatientDevice(String patientName, int deviceId) throws SQLException {
        deviceDAO.accessPatientDevice(patientName, deviceId);
    }

    public void updateDeviceValue(String patientName, int deviceId, int value) throws SQLException {
        deviceDAO.updateDeviceValue(patientName, deviceId, value);
    }

    public Device findDeviceByID(int deviceId) throws SQLException {
        return deviceDAO.findDeviceByID(deviceId);
    }

    public List<Device> listDeviceById(int deviceId) throws SQLException {
        return deviceDAO.listDeviceById(deviceId);
    }
}

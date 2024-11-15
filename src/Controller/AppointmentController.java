package Controller;

import java.sql.SQLException;
import java.util.List;

import Controller.db_Connections.AppointmentDAO;
import Model.Appointment;
import Model.Patient;

public class AppointmentController {

    private AppointmentDAO appointmentDAO;

    public AppointmentController() {
        this.appointmentDAO = new AppointmentDAO();
    }
    
    public void addAppointmentToPatient(Appointment appointment, Patient patient) throws SQLException {
        appointmentDAO.addAppointmentToPatient(appointment, patient);
    }

    public List<Appointment> listAppointmentsByPatientName(String patientName) throws SQLException {
        return appointmentDAO.listAppointmentByPatientName(patientName);
    }

    public void deletePatientAppointmentByName(String patientName, int appointmentId) throws SQLException {
        appointmentDAO.deletePatientAppointmentByName(patientName, appointmentId);
    }

}

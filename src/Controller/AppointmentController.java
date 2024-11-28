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

    public Appointment findAppointmentByID(int appointmentId) throws SQLException {
        return appointmentDAO.findAppointmentByID(appointmentId);
    }

    public List<Appointment> listAppointmentByPatientName(String patientName) throws SQLException {
        return appointmentDAO.listAppointmentByPatientName(patientName);
    }

    public void deletePatientAppointmentByID(String patientName, int appointmentId) throws SQLException {
        appointmentDAO.deletePatientAppointmentById(patientName, appointmentId);
    }

}

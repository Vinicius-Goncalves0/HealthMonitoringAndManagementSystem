package Controller;

import java.sql.SQLException;
import Model.Patient;
import Controller.db_Connections.PatientDAO;

public class PatientController {
    private PatientDAO patientDAO;

    public PatientController() {
        this.patientDAO = new PatientDAO();
    }

    public void addPatient(Patient patient) throws SQLException {
        patientDAO.addPatient(patient);
    }

    public Patient findPatientByName(String name) throws SQLException {
        return patientDAO.findPatientByName(name);
    }

    public void updatePatient(Patient patient) throws SQLException {
        patientDAO.updatePatient(patient);
    }
}
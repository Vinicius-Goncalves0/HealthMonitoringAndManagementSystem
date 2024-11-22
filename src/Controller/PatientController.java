package Controller;

import java.sql.SQLException;
import java.util.List;

import Model.Patient;
import Controller.db_Connections.PatientDAO;

public class PatientController {
    private PatientDAO patientDAO;

    public PatientController() {
        this.patientDAO = new PatientDAO();
    }

    // Add a new patient
    public void addPatient(Patient patient) throws SQLException {
        patientDAO.addPatient(patient);
    }

    // Find a patient by their name
    public Patient findPatientByName(String name) throws SQLException {
        return patientDAO.findPatientByName(name);
    }

    // Update a patient's information
    public void updatePatient(Patient patient) throws SQLException {
        patientDAO.updatePatient(patient);
    }

    // List patients by name
    public List<Patient> listPatientsByName(String name) throws SQLException {
        return patientDAO.listPatientsByName(name);
    }

    // List all patients
    public List<Patient> listAllPatients() throws SQLException {
        return patientDAO.listAllPatients();
    }

    // 
}
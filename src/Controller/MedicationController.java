package Controller;

import java.sql.SQLException;
import java.util.List;

import Model.Appointment;
import Model.Medication;
import Model.Patient;
import Controller.db_Connections.MedicationDAO;

public class MedicationController {
    private MedicationDAO medicationDAO;

    public MedicationController() {
        this.medicationDAO = new MedicationDAO();
    }

    public void addMedicationToPatient(Medication medication, Patient patient) throws SQLException {
        medicationDAO.addMedicationToPatient(medication, patient);
    }

    public void addMedicationToAppointment(Medication medication, Appointment appointment) throws SQLException {
        medicationDAO.addMedicationToAppointment(medication, appointment);
    }

    public List<Medication> listMedicationsByPatientName(String patientName) throws SQLException {
        return medicationDAO.listMedicationsByPatientName(patientName);
    }

    public void deletePatientMedicationByName(String patientName, int medicationId) throws SQLException {
        medicationDAO.deletePatientMedicationByName(patientName, medicationId);
    }
}
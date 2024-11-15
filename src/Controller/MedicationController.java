package Controller;

import java.sql.SQLException;
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
}
package View.List;

import Controller.PatientController;
import Model.Patient;
import java.sql.SQLException;
import java.util.List;

public class ListPatients {
    public void listAllPatients() {
        // Create a new PatientController object
        PatientController patientController = new PatientController();

        // List all patients
        try {
            List<Patient> patients = patientController.listAllPatients();
            for (Patient patient : patients) {
                System.out.println("ID: " + patient.getId() + " // " + "Nome: " + patient.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

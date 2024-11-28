package View.List;

import java.sql.SQLException;
import java.util.List;
import Controller.DoctorController;
import Model.Doctor;

public class ListDoctor {
    public void listAllDoctors() {
        // Create a new PatientController object
        DoctorController doctorController = new DoctorController();

        // List all patients
        try {
            List<Doctor> doctors = doctorController.listAllDoctors();
            System.out.println("\n=== Registered doctors ===\n");
            for (Doctor doctor : doctors) {
                System.out.println("|| ID: " + doctor.getId() + " // " + "Name: " + doctor.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

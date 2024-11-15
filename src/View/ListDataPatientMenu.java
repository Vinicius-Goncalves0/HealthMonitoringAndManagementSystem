package View;

import java.util.List;
import Model.Patient;

public class ListDataPatientMenu {

    public void displayPatients(List<Patient> patients) {
        if (patients.isEmpty()) {
            System.out.println("No patients found with the given name.");
        } else {
            for (Patient patient : patients) {
                System.out.println("ID: " + patient.getId());
                System.out.println("Name: " + patient.getName());
                System.out.println("CPF: " + patient.getCPF());
                System.out.println("Birth Date: " + patient.getBirthDate());
                System.out.println("Address: " + patient.getAddress());
                System.out.println("Phone: " + patient.getPhone());
                System.out.println("Email: " + patient.getEmail());
                System.out.println("-----------------------------");
            }
        }
    }
}

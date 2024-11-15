package View;

import java.util.Scanner;
import java.sql.SQLException;

import Controller.PatientController;
import Model.Patient;

public class UpdatePatientMenu {
    Scanner scan = new Scanner(System.in);
    private PatientController patientController;

    public UpdatePatientMenu() {
        this.patientController = new PatientController();
    }

    public void updatePatientMenu(String patientName) {
        System.out.println("\nEnter patient's new name: ");
        String name = scan.nextLine();
        System.out.println("Enter patient's new CPF: ");
        String cpf = scan.nextLine();
        System.out.println("Enter patient's new birth date: ");
        String birthDate = scan.nextLine();
        System.out.println("Enter patient's new address: ");
        String address = scan.nextLine();
        System.out.println("Enter patient's new phone: ");
        String phone = scan.nextLine();
        System.out.println("Enter patient's new email: ");
        String email = scan.nextLine();

        try {
            System.out.println("\nSearching for patient: " + patientName);
            Patient patient = patientController.findPatientByName(patientName);

            if (patient != null) {
                System.out.println("Patient found: " + patient.getName());
                patient.setName(name);
                patient.setCPF(cpf);
                patient.setBirthDate(birthDate);
                patient.setAddress(address);
                patient.setPhone(phone);
                patient.setEmail(email);

                System.out.println("Updating patient: " + patient.getName());
                patientController.updatePatient(patient);
                System.out.println("\nPatient updated successfully!");
            } else {
                System.out.println("Patient not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating patient: " + e.getMessage());
        }
    }
}

package View;

import java.sql.SQLException;
import java.util.Scanner;
import Controller.MedicationController;
import Controller.PatientController;
import Model.Medication;
import Model.Patient;

public class CreateMedicationMenu {
    Scanner scan = new Scanner(System.in);
    private MedicationController medicationController;
    private PatientController patientController;

    public CreateMedicationMenu() {
        this.medicationController = new MedicationController();
        this.patientController = new PatientController();
    }

    public void createMedicationMenu(String patientName) {
        System.out.println("\nEnter medication name: ");
        String medicationName = scan.nextLine();
        System.out.println("Enter medication dosage: ");
        String dosage = scan.nextLine();
        System.out.println("Enter medication frequency: ");
        String frequency = scan.nextLine();
        System.out.println("Enter medication description: ");
        String description = scan.nextLine();
        System.out.println("Enter doctor name: ");
        String doctor = scan.nextLine();
        System.out.println("Enter prescription date: ");
        String prescriptionDate = scan.nextLine();

        try {
            Patient patient = patientController.findPatientByName(patientName);

            if (patient != null) {
                Medication medication = new Medication(patient.getName(), medicationName, dosage, frequency, description, doctor, prescriptionDate);
                medicationController.addMedicationToPatient(medication, patient);
            } else {
                System.out.println("Patient not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding medication patient: " + e.getMessage());
        }
    }
}
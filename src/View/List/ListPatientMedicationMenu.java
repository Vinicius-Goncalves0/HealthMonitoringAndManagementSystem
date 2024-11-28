package View.List;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import Controller.MedicationController;
import Model.Medication;

public class ListPatientMedicationMenu {
    Scanner scan = new Scanner(System.in);

    public void listMedicationsByAppointmentID(int appointmentId) {
        MedicationController medicationController = new MedicationController();

        try {
            List<Medication> medications = medicationController.listMedicationsByAppointmentId(appointmentId);
            if (medications.isEmpty()) {
                System.out.println("\nNo medications found for the given appointment.");
            }

            System.out.println("\n=== Prescription ===\n");
            for (Medication medication : medications) {
                System.out.println("|| Medication Name: " + medication.getMedicationName());
                System.out.println("|| Dosage: " + medication.getDosage());
                System.out.println("|| Frequency: " + medication.getFrequency());
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("\n--- Error listing medications: " + e.getMessage() + " ---\n");
        }
    }

    public void displayMedications(List<Medication> medications) {
        if (medications.isEmpty()) {
            System.out.println("No medications found for the given patient.");
        } else {
            System.out.println("\n=== Medications ===");
            for (Medication medication : medications) {
                System.out.println("\n|| ID: " + medication.getId());
                System.out.println("|| Name: " + medication.getMedicationName());
                System.out.println("|| Dosage: " + medication.getDosage());
                System.out.println("|| Frequency: " + medication.getFrequency());
                System.out.println("|| Description: " + medication.getDescription());
                System.out.println("|| Doctor: " + medication.getDoctor());
                System.out.println("|| Prescription Date: " + medication.getPrescriptionDate());
            }
        }
    }

    public void listMedicationsByPatientName(String patientName) {
        ListPatientMedicationMenu listPatientMedicationMenu = new ListPatientMedicationMenu();
        MedicationController medicationController = new MedicationController();

        try {
            List<Medication> medications = medicationController.listMedicationsByPatientName(patientName);
            listPatientMedicationMenu.displayMedications(medications);
            System.out.println("Press enter to continue...");
            scan.nextLine();
        } catch (SQLException e) {
            System.out.println("\n--- Error when listing medicines: " + e.getMessage() + " ---\n");
        }
    }
}

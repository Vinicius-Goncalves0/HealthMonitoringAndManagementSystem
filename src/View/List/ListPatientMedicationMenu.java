package View.List;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import Controller.MedicationController;
import Model.Medication;

public class ListPatientMedicationMenu {
    Scanner scan = new Scanner(System.in);

    public void displayMedications(List<Medication> medications) {
        if (medications.isEmpty()) {
            System.out.println("No medications found for the given patient.");
        } else {
            for (Medication medication : medications) {
                System.out.println("Medication ID: " + medication.getId());
                System.out.println("Medication Name: " + medication.getMedicationName());
                System.out.println("Dosage: " + medication.getDosage());
                System.out.println("Frequency: " + medication.getFrequency());
                System.out.println("Description: " + medication.getDescription());
                System.out.println("Doctor: " + medication.getDoctor());
                System.out.println("Prescription Date: " + medication.getPrescriptionDate());
                System.out.println("-----------------------------");
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
            System.out.println("\n--- Erro ao listar medicamentos: " + e.getMessage() + " ---\n");
        }
    }
}

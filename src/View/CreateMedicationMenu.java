package View;

import java.sql.SQLException;
import java.util.Scanner;

import Controller.db_Connections.MedicationDAO;
import Model.Medication;
import Model.Patient;
import Controller.db_Connections.PatientDAO;

public class CreateMedicationMenu {
    Scanner scan = new Scanner(System.in);

    public void createMedicationMenu(String patientName) {
        System.out.println("Enter medication name: ");
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

        // Creating a database connection object
        PatientDAO patientDAO = new PatientDAO();

        try {
            Patient patient = patientDAO.findPatientByName(patientName);

            if (patient != null) {
                // Usa o paciente recuperado para criar o registro do medicamento
                Medication medication = new Medication(patient.getName(), medicationName, dosage, frequency, description, doctor, prescriptionDate);
                MedicationDAO medicationDAO = new MedicationDAO();
                medicationDAO.addMedicationToPatient(medication, patient);
            } else {
                // Lida com o caso em que o paciente não foi encontrado
                System.out.println("Paciente não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
package View.Create;

import java.sql.SQLException;
import java.util.Scanner;
import Controller.db_Connections.PatientDAO;
import Model.Patient;

public class CreatePatientMenu {
    Scanner scan = new Scanner(System.in);

    public void createPatientMenu() {
        System.out.println("\nEnter patient's name: ");
        String name = scan.nextLine();
        System.out.println("Enter patient's CPF: ");
        String cpf = scan.nextLine();
        System.out.println("Enter patient's birth date: ");
        String birthDate = scan.nextLine();
        System.out.println("Enter patient's medical history: ");
        String histories = scan.nextLine();
        System.out.println("Enter patient's address: ");
        String address = scan.nextLine();
        System.out.println("Enter patient's phone: ");
        String phone = scan.nextLine();
        System.out.println("Enter patient's email: ");
        String email = scan.nextLine();

        // Create a new Patient object
        Patient patient = new Patient(name, cpf, birthDate, address, phone, email, histories);

        // Add the patient to the database
        PatientDAO patientDAO = new PatientDAO();
        try {
            patientDAO.addPatient(patient);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
